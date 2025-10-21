
package edu.ntnu.iir.bidata.logic;
import edu.ntnu.iir.bidata.UI.Output;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DiaryEntry {
    int id;
    LocalDateTime timeWritten;
    LocalDateTime TimeEdited;
    String author;
    String trip;
    String title;
    String category;
    int rating;

    public DiaryEntry() {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        this.timeWritten = LocalDateTime.now();
        this.TimeEdited = LocalDateTime.now();

        System.out.print("Enter Author: ");
        this.author = scanner.nextLine();
        System.out.print("Enter Trip: ");
        this.trip = scanner.nextLine();
        System.out.print("Enter Title: ");
        this.title = scanner.nextLine();;
        System.out.print("Enter Category: ");
        this.category = scanner.nextLine();
        System.out.print("Enter Rating: ");
        this.rating = scanner.nextInt();
    }


    public String textInput() {
        Scanner input = new Scanner(System.in);
        Output output = new Output();
        String text = "";
        output.clear();
        System.out.println("Write youre entry, write \"end\" to quit:");
        output.line();
        int line = 0;
        while (true) {
            System.out.print(String.format("%3d", line) + "  ");
            line++;
            String inputtext = input.nextLine();
            if (inputtext.equals("end")) {
                break;
            }
            text += "\n" + inputtext;
        }
        System.out.println(line - 1 + " lines are saved.");
        input.close();
        return text;
    }
}