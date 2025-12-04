# TravelDiary Application - Portfolio project IDATT1003

**STUDENT NAME:** Elias Haugsbakk<br>
**STUDENT ID:** 137525

## Project description

This project is a travel diary. It aims to help you catalog your travel experiences and to keep track of all the activities you have done while traveling.
Each diary entry has a Destination/Trip, activity and a rating associated with it. This lets the user view and compare earlier trips and activities.

## Project structure
The application is divided into four packages: **model, storage**, **utils** and **ui**.<br>
- **model** holds the **DiaryEntry**-object class and the **Diary** class. **Diary** holds all the **DiaryEntries** in the program in memory while the application is running.
- **storage** holds **DiaryStorage**.java and a **DTO** class. **DiaryStorage** is responsible for writing and reading entries to and from the disk. This stores the data in register/data.json.
The **Data transfer object** is created as an intermediary to translate between a string representation of all object data.
- **utils** holds the classes to do operations on a collection of diary entries. Mainly sorting and filtering of the entries.
- **ui** holds the classes the user will interact with more directly. This includes **Formatter** and **Prompter** to format and print output, as well as take user input. The main program loop is also here, in **ui.UI**.
**EntryUI** gives the methods to operate on one diary entry. Like reading, editing and deleting an entry. **CollectionUI** holds the methods to create a collection of entries for the user to interact with. This makes
it easier for the user to find and compare entries.

This project uses **Apache Maven v3.9.11** as the build tool. Maven manages:

- **Compilation** of the project.
- **Dependencies**, including:
    - **JUnit v5.13.4** for unit testing
    - **Gson v2.13.1** for JSON serialization/deserialization
- **Running tests** (`mvn test`)<br>

### Directory tree:
```text
travelDiary/
│
├── diagrams
│   ├── ClassDiagram.drawio
│   ├── DomainClassesDiagram.drawio
│   ├── MainAndStorageClassesDiagram.drawio
│   ├── ProgramFlowChart.drawio
│   ├── ProjectStructureDiagram.drawio
│   └── UiClassesDiagram.drawio
├── pom.xml
├── README.md
├── register
│   └── data.json
└── src
    ├── main
    │   └── java
    │       └── edu
    │           └── ntnu
    │               └── iir
    │                   └── bidata
    │                       ├── Main.java
    │                       ├── model
    │                       │   ├── DiaryEntry.java
    │                       │   ├── Diary.java
    │                       │   └── EntryFactory.java
    │                       ├── storage
    │                       │   ├── DiaryEntryStorageDto.java
    │                       │   └── DiaryStorage.java
    │                       ├── ui
    │                       │   ├── CollectionUi.java
    │                       │   ├── EntryUi.java
    │                       │   ├── Formatter.java
    │                       │   ├── Prompter.java
    │                       │   └── Ui.java
    │                       └── utils
    │                           ├── DiaryDistinct.java
    │                           ├── DiaryFilter.java
    │                           └── DiarySort.java
    └── test
        └── java
            └── edu
                └── ntnu
                    └── iir
                        └── bidata
                            ├── model
                            │   ├── DiaryEntryTest.java
                            │   ├── DiaryTest.java
                            │   └── EntryFactoryTest.java
                            ├── storage
                            │   ├── DiaryEntryStorageDtoTest.java
                            │   └── DiaryStorageTest.java
                            ├── ui
                            │   └── FormatterTest.java
                            └── utils
                                ├── DiaryDistinctTest.java
                                ├── DiaryFilterTest.java
                                └── DiarySortTest.java

```


## Link to repository
[Link to the GitHub repository](https://github.com/NTNU-IDI/mappe-2025-eliashaugsbakk)

## How to run the project

The travel diary is a TUI (Text User Interface) application.
To run this program, you must use your favorite terminal emulator, either in an IDE or otherwise.

### Run:
To run this application. You can either download the latest release, which is a .jar file, from GitHub.<br>
Make sure you have Java installed on your system. This program was built and tested with Java 21. <br>
If you encounter issues, please use the tested version.
After this is downloaded. Open your favorite terminal emulator and navigate to the location<br>
of the download .jar. Then, run the application by entering:
```shell
java -jar TravelDiary-v<version>.jar
```
Make sure to swap out `<version>` with the version you downloaded.<br>
Example:
```shell
java -jar TravelDiary-v1.0.1.jar
```

### Build:
You can also **clone** this repository and build the application yourself:<br>
Make sure to have Maven installed on your system.<br>
This project was tested on **Maven version 3.9.11**, and **JDK-21**. <br>
If you encounter issues, please use the tested version.
The easiest way to run the application would be to navigate to <br>
the project root: **mappe-2025-eliashaugsbakk/**, and run the command:
```shell
mvn exec:java -Dexec.mainClass="edu.ntnu.iir.bidata.Main"
```
Alternatively, you could run this project from an IDE with **Maven support**.

## How to run the tests
Make sure to have Maven installed on your system.
This project was tested on **Maven version 3.9.11**, and **JDK-21**<br>
If you encounter issues, please use the tested version.
Run the tests by navigating to the project root: **mappe-2025-eliashaugsbakk/**, and run:
```shell
mvn test
```
You can also run the test from an IDE that supports **Maven**.

## References
- C. S. Horstman, Core Java, Voluem I: Fundamentals, 13th ed. Oracle Press, 2024 
- “Coupling and Cohesion – Software Engeneering”, GeeksforGeeks, [Webpage]. Avalable at:
https://www.geeksforgeeks.org/software-engineering/software-engineering-coupling-and-cohesion/ .
[Visited 24. November 2025] 
- “Single Responsability in SOLID Design Priniple”, GeeksforGeeks, [Webpage]. Avalable at: 
https://www.geeksforgeeks.org/system-design/single-responsibility-in-solid-design-principle/ .
[Visited 30. November 2025] 
