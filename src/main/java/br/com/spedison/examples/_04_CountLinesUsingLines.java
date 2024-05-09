package br.com.spedison.examples;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class _04_CountLinesUsingLines {
    public static void main(String[] args) {

        String fileName = args[0];
        File file1 = new File(fileName);
        System.out.println("Opening file: " + file1.getAbsolutePath());
        System.out.println("File size: " + file1.length() + " Bytes");
        System.out.println("File size: " + (file1.length() / (1024 * 1024)) + " MBytes");
        System.out.println("File size: " + (file1.length() / (1024. * 1024. * 1024.)) + " GBytes");

        try (InputStream is = new FileInputStream(file1);
             Scanner scanner = new Scanner(is)) {
            int countEnter = 0;
            String line = null;

            do {
                line = scanner.nextLine();
                countEnter++;
            } while (scanner.hasNextLine() && line != null);

            System.out.println("Number of enter: " + countEnter);
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
