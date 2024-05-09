package br.com.spedison.examples;

import java.io.*;

public class _02_OpenAndCloseFile_V2 {

    public static void main(String[] args) {

        String fileName = args[0];
        File file1 = new File(fileName);
        System.out.println("V2:: Opening file: " + file1.getAbsolutePath());
        System.out.println("V2:: File size: " + file1.length() + " Bytes");
        System.out.println("V2:: File size: " + (file1.length()/(1024*1024))+" MBytes");
        System.out.println("V2:: File size: " + (file1.length()/(1024.*1024.*1024.)) + " GBytes");
        try(InputStream is = new FileInputStream(file1)) {
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("V2:: Finally called.");
        }
        System.out.println("V2:: File was closed: " + file1.getAbsolutePath());
    }

}
