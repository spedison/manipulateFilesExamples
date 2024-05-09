package br.com.spedison.examples;

import java.io.*;

public class _01_OpenAndCloseFile_V1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String fileName = args[0];
        File file1 = new File(fileName);
        System.out.println("Opening file: " + file1.getAbsolutePath());
        System.out.println("File size: " + file1.length() + " Bytes");
        System.out.println("File size: " + (file1.length()/(1024*1024))+" MBytes");
        System.out.println("File size: " + (file1.length()/(1024.*1024.*1024.)) + " GBytes");
        InputStream is = new FileInputStream(file1);
        is.close();
        System.out.println("File was closed: " + file1.getAbsolutePath());
    }
}
