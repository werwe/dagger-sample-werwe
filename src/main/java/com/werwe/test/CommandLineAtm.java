package com.werwe.test;

import dagger.Component;

import java.util.Scanner;

public class CommandLineAtm {

    public static void main(String[] args) {
        CommandProcessor commandProcessor;
        Scanner scanner = new Scanner(System.in);
        CommandProcessorFactory factory = DaggerCommandProcessorFactory.create();
        CommandProcessor processor = factory.processor();
        while (scanner.hasNextLine()) {
            processor.process(scanner.nextLine());
        }
    }

}
