package br.com.spedison.examples;

import java.io.*;

public class _03_CountLinesUsingBytes {
    public static void main(String[] args) {

        String fileName = args[0];
        File file1 = new File(fileName);
        System.out.println("Opening file: " + file1.getAbsolutePath());
        System.out.println("File size: " + file1.length() + " Bytes");
        System.out.println("File size: " + (file1.length() / (1024 * 1024)) + " MBytes");
        System.out.println("File size: " + (file1.length() / (1024. * 1024. * 1024.)) + " GBytes");
        try (InputStream is = new FileInputStream(file1)) {
            byte[] buffer = new byte[10240];
            int bytesRead;
            int countEnter = file1.length() > 0 ? 1 : 0;
            do {
                bytesRead = is.read(buffer);
                for (int i = 0; i < bytesRead; i++) {
                    if (buffer[i] == 0x0A)
                        countEnter++;
                }
            } while (bytesRead == buffer.length);
            System.out.println("Number of enter: " + countEnter);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finally called.");
        }
    }
}
