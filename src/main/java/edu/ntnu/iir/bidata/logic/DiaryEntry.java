
package edu.ntnu.iir.bidata.logic;
import edu.ntnu.iir.bidata.UI.Output;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DiaryEntry {
    int id;
    String timeWritten;
    String TimeEdited;
    String author;
    String trip;
    String title;
    String category;
    int rating;
    String text;

    public DiaryEntry() {
        Scanner scanner = new Scanner(System.in);
        int id = 0;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timeWritten = dtf.format(LocalDateTime.now());
        this.TimeEdited =  dtf.format(LocalDateTime.now());

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

        this.text = textInput();
    }


    public String textInput() {
        Scanner input = new Scanner(System.in);
        Output output = new Output();
        String text = "";
        output.clear();
        System.out.println("Write your entry, type \"end\"  to quit:");
        output.line();
        int line = 0;
        while (true) {
            System.out.print(String.format("%3d", line) + "  ");
            line++;
            String inputext = input.nextLine();
            if (inputext.equals("end")) {
                break;
            }
            text += "\n" + inputext;
        }
        System.out.println(line - 1 + " lines are saved.");
        input.close();
        return text;
    }
}