package com.example;

import com.example.myInts.MyModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private List<MyModule> modules;
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        while (true) {
            System.out.println("\n=== File Processor ===");
            File file = selectFile();
            if (file == null) continue;

            processFile(file);

            if (!askForContinue()) break;
        }
        System.out.println("Exiting...");
        System.exit(0);
    }

    private File selectFile() {
        System.out.print("Enter file path (or 'exit' to quit): ");
        String path = scanner.nextLine().trim();

        if (path.equalsIgnoreCase("exit")) System.exit(0);

        File file = new File(path);
        if (!file.exists()) {
            System.err.println("File not found: " + path);
            return null;
        }
        return file;
    }

    private void processFile(File file) {
        List<MyModule> applicable = modules.stream()
                .filter(m -> m.supportsFormat(file))
                .collect(Collectors.toList());

        if (applicable.isEmpty()) {
            System.out.println("No available modules for this file type");
            return;
        }

        System.out.println("\nAvailable functions:");
        for (int i = 0; i < applicable.size(); i++) {
            System.out.printf("%2d. %s%n", i + 1, applicable.get(i).getDescription());
        }
        System.out.println(" 0. Cancel");

        int choice = readChoice(applicable.size());
        if (choice == 0) return;

        applicable.get(choice - 1).process(file);
    }

    private int readChoice(int max) {
        while (true) {
            try {
                System.out.print("Select function [0-" + max + "]: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 0 && choice <= max) return choice;
                System.err.println("Invalid choice");
            } catch (NumberFormatException e) {
                System.err.println("Please enter a number");
            }
        }
    }

    private boolean askForContinue() {
        System.out.print("\nProcess another file? [y/N]: ");
        String answer = scanner.nextLine().trim();
        return answer.equalsIgnoreCase("Y");
    }
}