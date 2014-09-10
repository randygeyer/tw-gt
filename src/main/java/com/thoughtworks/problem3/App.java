package com.thoughtworks.problem3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.thoughtworks.problem3.model.GalacticTranslator;

/**
 * This is the solution to ThoughtWorks Problem Three: Merchant's Guide To The Galaxy.
 * 
 * This app translates galactic numbers and units used during galactic transactions of 
 * Earth resources.  The translations are accomplished using an external DSL (Domain 
 * Specific Language) for entering translation data and submitting translation queries.
 *   
 * This application accepts an input file of translation queries.  The input file path
 * is provided as the first command line argument.
 * 
 * Run this app from the jar by typing the following at a command prompt:
 * 
 *   java -jar gt.jar [path]<file>
 *   
 * e.g. java -jar gt.jar ./input.txt
 * 
 * @author rgeyer
 *
 */
public class App {

    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            System.out.println();
            System.err.println("Please rerun the application and enter the path to the input file as a program argument, e.g.");
            System.out.println();
            System.out.println("   java -jar gt.jar [path]input.txt");
            System.exit(1);
        }
        
        try {
            GalacticTranslator translator = new GalacticTranslator();
            Path path = Paths.get(args[0]);
            final List<String> lines = Files.readAllLines(path, Charset.forName("utf-8"));
            printInput(lines);
            for (String line : lines) {
                translator.interpret(line);
            }
        } catch (IOException e) {
            System.err.println("Unable to load input file: " + e.getMessage());
            System.err.println();
            System.err.println("  Please check the path and try again. ");
            System.exit(1);
        }
    }

    private static void printInput(List<String> lines) {
        System.out.println();
        System.out.println("Input queries:");
        System.out.println();
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println();
        System.out.println("Results:");
        System.out.println();
    }

}
