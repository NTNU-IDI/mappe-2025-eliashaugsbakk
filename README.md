# mappe-2025-eliashaugsbakk

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NzRaDbQp)
# Portfolio project IDATT1003
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

[//]: # (TODO: Fill inn your name and student ID)

STUDENT NAME = "Elias Haugsbakk"
STUDENT ID = "137525"

## Project description
[//]: # (TODO: Write a short description of your project/product here.)

This project is a travel diary. It aims to help you catalog your travel experiences and to keep track of all the activities you have done while traveling.
Each diary entry has a Destination/Trip, Activity and a rating associated with it. This lets the user view and compare earlier trips and activities.

## Project structure
[//]: # (TODO: Describe the structure of your project here. How have you used packages in your structure. Where are all sourcefiles stored. Where are all JUnit-test classes stored. etc.)
The application is divided into three packages: **model, storage** and **ui**.<br>
- **model** holds the **DiaryEntry**-object class and the **Diary** class. **Diary** holds all the **DiaryEntries** in the program in memory while the application is running.
- **storage** holds **DiaryStorage**.java and a **DTO** class. **DiaryStorage** is responsible for writing and reading entries to and from the disk. This stores the data in register/data.json.
The **Data transfer object** is created as an intermediary to translate between a string representation of all object data.
- **ui** holds the classes the user will interact with more directly. This includes **Formatter** and **Prompter** to format and print output, as well as take user input. The main program loop is also here, in **ui.UI**.
**EntryUI** gives the methods to operate on one diary entry. Like reading, editing and deleting an entry. **CollectionUI** holds the methods to create a collection of entries for the user to interact with. This makes
it easier for the user to find and compare entries.

This project uses **Apache Maven** as the build tool. Maven manages:

- **Compilation** and project structure
- **Dependencies**, including:
    - **JUnit** for unit testing
    - **Gson** for JSON serialization/deserialization
- **Running tests** (`mvn test`)
This program is using maven as the build tool. Handling unit testing through junit and Gson for handling the JSON translation.
```text
travleDiary/
├── pom.xml
├── ProjectStructureDiagram.drawio
├── README.md
├── register/
│   ├── data.json
│   └── test-data.json
└── src/
    ├── main/
    │   └── java/
    │       └── edu/
    │           └── ntnu/
    │               └── iir/
    │                   └── bidata/
    │                       ├── Main.java
    │                       ├── model/
    │                       │   ├── DiaryEntry.java
    │                       │   ├── Diary.java
    │                       │   └── DiaryUtils.java
    │                       ├── storage/
    │                       │   ├── DiaryEntryStorageDto.java
    │                       │   └── DiaryStorage.java
    │                       └── ui/
    │                           ├── CollectionUI.java
    │                           ├── EntryUI.java
    │                           ├── Formatter.java
    │                           ├── Prompter.java
    │                           └── UI.java
    └── test/
        └── java/
            └── edu/
                └── ntnu/
                    └── iir/
                        ├── model/
                        │   ├── DiaryEntryTest.java
                        │   ├── DiaryTest.java
                        │   └── DiaryUtilsTest.java
                        ├── storage/
                        │   ├── DiaryEntryStorageDtoTest.java
                        │   └── DiaryStorageTest.java
                        └── ui/
```


## Link to repository

[//]: # (TODO: Include a link to your GitHub repository here.)

## How to run the project

[//]: # (TODO: Describe how to run your project here. What is the main class? What is the main method?
What is the input and output of the program? What is the expected behaviour of the program?)
The easiest way to run would be to navigate to the project root: mappe-2025-eliashaugsbakk, and run:
```sh
mvn exec:java -Dexec.mainClass="edu.ntnu.iir.bidata.Main"
```

## How to run the tests

[//]: # (TODO: Describe how to run the tests here.)

## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
