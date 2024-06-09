package br.com.spedison;

import br.com.spedison.examples.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException {

        System.out.println("====>>> Open and Close File _01_OpenAndCloseFile_V1");
        _01_OpenAndCloseFile_V1.main(args);

        System.out.println("====>>> Open and Close File _01_OpenAndCloseFile_V2");
        _02_OpenAndCloseFile_V2.main(args);

        System.out.println("====>>> Count Lines using bytes File _03_CountLinesUsingBytes");
        _03_CountLinesUsingBytes.main(args);

        System.out.println("====>>> Count Lines using Tokens File _04_CountLinesUsingTokens");
        _04_CountLinesUsingLines_V1.main(args);
        _04_CountLinesUsingLines_V2.main(args);
        _04_CountLinesUsingLines_V3.main(args);
        _04_CountLinesUsingLines_V4.main(args);

        _07_ReadFileUsingStream_V1.main(args);

    }
}
