package edu.ntnu.iir.bidata.UI;

import java.util.Scanner;

public class Menu {
    public void menu() {
        while (true) {
            System.out.println(
                    "Choose your next action:" +
                            "1. New Diary Entry"
            );
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: return;
                default: return;
            }
        }
    }
}
