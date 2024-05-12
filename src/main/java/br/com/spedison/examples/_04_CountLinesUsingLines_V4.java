package br.com.spedison.examples;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

public class _04_CountLinesUsingLines_V4 {
    public static void main(String[] args) {

        String fileName = args[0];
        File file1 = new File(fileName);
        System.out.println("Opening file: " + file1.getAbsolutePath());
        System.out.println("File size: " + file1.length() + " Bytes");
        System.out.println("File size: " + (file1.length() / (1024 * 1024)) + " MBytes");
        System.out.println("File size: " + (file1.length() / (1024. * 1024. * 1024.)) + " GBytes");

        try {
            Path p = file1.toPath();

            long countEnter =
                    Files.lines(p, StandardCharsets.ISO_8859_1).count();
            //List<String> lines = Files.readAllLines(p, StandardCharsets.ISO_8859_1);
            System.out.println("Number of enter: " + countEnter);

            System.out.printf("End.... I will wait 1 minute to stop");
            var countTime = 60;
            try {
                while (countTime > 0) {
                    Thread.sleep(1000);
                    System.out.printf("Resting %d seconds%n", countTime--);
                }
            } catch (InterruptedException ie) {

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            System.out.println("Fim do Stream");
        } finally {
            System.out.println("Finally called.");
        }
    }
}