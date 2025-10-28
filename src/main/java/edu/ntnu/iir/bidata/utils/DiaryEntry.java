package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.UI.Output;
import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DiaryEntry {
    private int id;
    private String timeWritten;
    private String TimeEdited;
    private String author;
    private String trip;
    private String title;
    private String category;
    private int rating;
    private String text;

    // Empty for Gson to load from data.json
    public DiaryEntry() {}

    // Constructor for creating new entries
    public DiaryEntry(Scanner scanner) {
        this.id = DiaryDatabase.diaryEntries.size();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timeWritten = dtf.format(LocalDateTime.now());
        this.TimeEdited = dtf.format(LocalDateTime.now());

        this.author = setAuthor();

        System.out.print("\nEnter Trip Destination: ");
        trip = scanner.nextLine();

        System.out.print("Enter Title: ");
        this.title = scanner.nextLine();

        this.category = setCategory();

        this.rating = setRating(scanner);

        this.text = textInput();
        JsonReaderWriter.writeToFile();
    }


    // getters
    public String getTitle() {
        return title;
    }
    public int getRating() {
        return rating;
    }
    public String getTime() {
        return timeWritten;
    }
    public String getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }


    // create a new author, or select from list
    private String setAuthor() {
        Output.clear();
        Output.line();
        Output.redln("Select a previous author, or create a new one by typing your name!");

        ArrayList<String> authors = new ArrayList<>();
        for (int i = 0; i < DiaryDatabase.diaryEntries.size(); i++) {
            authors.add(DiaryDatabase.diaryEntries.get(i).author);
        }
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(i + " : " + authors.get(i) );
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");
        String input = scanner.nextLine();
        try {
            int inputInt = Integer.parseInt(input);
            author = authors.get(inputInt);
            System.out.println("\n\nWelcome " + author);
        } catch (NumberFormatException ex) {
            System.out.println("\n\nWelcome " + input);
            author = input;
        }
        return author;
    }

    private int setRating(Scanner scanner) {
        System.out.print("Enter Rating (0 to 100): ");
        try {
            rating = scanner.nextInt();
        } catch (InputMismatchException e) {
            Output.redln("Invalid input! Rating must be a number");
            scanner.nextLine();
            setRating(scanner);
        }
        if (rating < 0 || rating > 100) {
            Output.redln("Invalid input! Rating must be between 0 and 100");
            scanner.nextLine();
            setRating(scanner);
        }
        return rating;
    }

    // create a new category, or select from list
    private String setCategory() {
        Output.clear();
        Output.line();
        Output.redln("Select a previous category, or create a new one by typing in the name of the category.");
        ArrayList<String> categories = new ArrayList<>();
        for (int i = 0; i < DiaryDatabase.diaryEntries.size(); i++) {
            categories.add(DiaryDatabase.diaryEntries.get(i).category);
        }
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(i + " : " + categories.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");
        String input = scanner.nextLine();
        try {
            int inputInt = Integer.parseInt(input);
            category = categories.get(inputInt);
            System.out.println("\n\n" + category + " chosen");
        } catch (NumberFormatException ex) {
            System.out.println("\n\n" + input + " chosen");
            category = input;
        }
        return category;
    }

    // input for the diary entry text
    // while loop to get multiple line input
    private String textInput() {
        Scanner input = new Scanner(System.in);
        StringBuilder text = new StringBuilder();
        Output.clear();
        System.out.println("Write your entry, type \"bye\"  to quit:");
        Output.line();
        int line = 0;
        while (true) {
            System.out.print(String.format("%3d", line) + "  ");
            line++;
            String inputText = input.nextLine();
            if (inputText.equals("bye")) {
                break;
            }
            text.append("\n").append(inputText);
        }
        JsonReaderWriter.writeToFile();
        System.out.println(line - 1 + " lines are saved.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        return text.toString();
    }
}