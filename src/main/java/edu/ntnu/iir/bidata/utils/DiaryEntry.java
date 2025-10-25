package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.UI.Output;
import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public DiaryEntry() {
    } // Empty for Gson to load from data.json

    public DiaryEntry(Scanner scanner) {
        this.id = DiaryDatabase.diaryEntries.size();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timeWritten = dtf.format(LocalDateTime.now());
        this.TimeEdited = dtf.format(LocalDateTime.now());

        this.author = setAuthor();
        System.out.print("\nEnter Trip Destination: ");
        this.trip = scanner.nextLine();
        System.out.print("Enter Title: ");
        this.title = scanner.nextLine();
        this.category = setCategory();
        System.out.print("Enter Rating: ");
        this.rating = scanner.nextInt();

        this.text = textInput();
        JsonReaderWriter readerWriter = new JsonReaderWriter();
        readerWriter.writeToFile();
    }

    private String setAuthor() {
        Output.line();
        Output.redln("Select a previous author, or create a new one!");

        ArrayList<String> authors = new ArrayList<>();
        for (int i = 0; i < DiaryDatabase.diaryEntries.size(); i++) {
            authors.add(DiaryDatabase.diaryEntries.get(i).author);
        }
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(authors.get(i) + ": " + i);
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

    private String setCategory() {
        Output out = new Output();
        out.redln("Select a previous category, or create a new one!");
        ArrayList<String> categories = new ArrayList<>();
        for (int i = 0; i < DiaryDatabase.diaryEntries.size(); i++) {
            categories.add(DiaryDatabase.diaryEntries.get(i).category);
        }
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(categories.get(i) + ": " + i);
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

    private String textInput() {
        Scanner input = new Scanner(System.in);
        Output output = new Output();
        StringBuilder text = new StringBuilder();
        Output.clear();
        System.out.println("Write your entry, type \"bye\"  to quit:");
        output.line();
        int line = 0;
        while (true) {
            System.out.print(String.format("%3d", line) + "  ");
            line++;
            String inputext = input.nextLine();
            if (inputext.equals("bye")) {
                break;
            }
            text.append("\n").append(inputext);
        }
        JsonReaderWriter readerWriter = new JsonReaderWriter();
        readerWriter.writeToFile();
        System.out.println(line - 1 + " lines are saved.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        return text.toString();
    }
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
}