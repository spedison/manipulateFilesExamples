package br.com.spedison.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.DoubleSummaryStatistics;

import br.com.spedison.vo.Person;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class _07_ReadFileUsingStream_V1 {
    public static void main(String[] args) throws IOException {

        // https://www.baeldung.com/java-download-file
        // Exemple for control with downloads files from intenet to localfile.
        // String fileUrl = "https://raw.githubusercontent.com/1920-3ahitm-sew/assigment01-football-league-PhilippEdlinger/master/bundesliga-1819.csv";
        String fileUrl = "https://raw.githubusercontent.com/spedison/FilesToWorkingRemote/main/faker_persons_with_width.csv";
        // Header -> Nome;Idade;Peso;Altura;Cidade
        URL url = null;
        try {
            url = new URL(fileUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        boolean usaPontoEVirgula = true;
        BiFunction<String, Integer, Person> exampleMyLambdaFunction;


        // This if a form to change the behavior of program.
        if (usaPontoEVirgula)
            // Use ";" as separator
            exampleMyLambdaFunction = Person::strToPessoaCSV;
        else
            // User "," as separator
            exampleMyLambdaFunction = Person::strToPessoaSPV;


        Predicate<Person> personIMCVeryHigthFilter = (person) -> {
            return person.IMC() > 26.;
        };


        InputStream fis = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
        final int[] conterAllLines = {0};
        final int[] counterSelectedLines = {0};

        Function<String, Person> integrateCounterLambdaFunction = (s) -> exampleMyLambdaFunction.apply(s, conterAllLines[0]);
        Consumer<Person> incrementCounterLambdaFunction = (p) -> conterAllLines[0]++;
        Instant inicio = Instant.now();

        DoubleSummaryStatistics mediaDeIdade =
                br.
                        // Lines return a Stream of String Objects
                                lines().
                        // Run Stream as parallel mode
                        // parallel().
                        // Run Stram as sequential mode
                                sequential().
                        // Skip first line of file because
                        // first line is not data, is file header.
                                skip(1).
                        // Convert a String in Object Person.
                        // Here you can convert the String in Any Object.
                                map(integrateCounterLambdaFunction).
                        // Peek is ckeck point to execute one process
                        // ... or Show process while is running.
                                peek(incrementCounterLambdaFunction).
                        // I can apply filter for Person Object.
                                filter(personIMCVeryHigthFilter).
                        // Other formm to add Lambda Funtions
                                filter(p -> {
                            if (p.age > 30)
                                return p.name.toLowerCase().startsWith("a");
                            return true;
                        }).
                        // Execute any process. Using Peek.
                                peek(person -> counterSelectedLines[0]++).
                        // Using static methods as Lambda Functions
                        // Get any field of object
                                map(Person::IMC).
                        // Convert in other type of Stream.
                                mapToDouble(Double::doubleValue).
                        // Execute Basics Statistics of field
                        // This is terminator of Stream.
                                summaryStatistics();
        br.close();

        Predicate<Person> delayFilter = (p) -> {try{Thread.sleep(10);}catch(Exception e){}; return true;} ;

        conterAllLines[0] = 0;
        counterSelectedLines[0] = 0;
        // Back to Start File.
        br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        br.
                lines().
                skip(1).
                map(integrateCounterLambdaFunction).
                peek(incrementCounterLambdaFunction).
                parallel().
                filter(personIMCVeryHigthFilter).
                // With this delay is usable parallel processing
                filter(delayFilter).
                // Using other terminator with the same file.
                        limit(1000).
                        forEach(System.out::println);

        // forEach(System.out::println);
        Instant fim = Instant.now();
        var tempo = fim.toEpochMilli() - inicio.toEpochMilli();
        System.out.println("Linhas contadas = " + conterAllLines[0]);
        System.out.println("Linhas saida = " + counterSelectedLines[0]);
        System.out.println("Tempo Gasto = " + tempo);
        System.out.println("Sumário da idade = \n" + mediaDeIdade);
        br.close();
    }
}
