package br.com.spedison.examples;

import br.com.spedison.vo.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

// This class is a exemple to group data for OneField.
// Its replace by one class with fields to group data.
public class _07_ReadFileUsingStream_V2 {
    public static void main(String[] args) throws IOException {

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
        BiFunction<String, Integer, Person> exampleMyLambdaFunction = Person::strToPessoaCSV;

        InputStream fis = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
        final int[] conterAllLines = {0};
        final int[] counterSelectedLines = {0};

        Function<String, Person> integrateCounterLambdaFunction = (s) -> exampleMyLambdaFunction.apply(s, conterAllLines[0]);
        Consumer<Person> incrementCounterLambdaFunction = (p) -> conterAllLines[0]++;
        Instant inicio = Instant.now();

        // Grouping data by Country

        // Map<String, List<Person>> dataGrouping =
        br.
                lines().
                // parallel().
                        sequential().
                skip(1).
                map(integrateCounterLambdaFunction).
                // Peek is ckeck point to execute one process
                // ... or Show process while is running.
                peek(incrementCounterLambdaFunction).
                // Using static methods as Lambda Functions
                // Get any field of object
                collect(Collectors.groupingBy(Person::getCity)).
                forEach(
                        (k, v) -> {
                            System.out.println("City : " + k + " with " + v.size() + " people.");
                            final int[] numberOfPeople = {0};
                            v.stream().
                                    map(Person::getName).
                                    map(s -> String.format("%04d - %s", numberOfPeople[0]++, s)).
                                    forEach(System.out::println);
                            DoubleSummaryStatistics dssAge = v.stream().map(Person::getAge).mapToDouble(Integer::doubleValue).summaryStatistics();
                            DoubleSummaryStatistics dssIMC = v.stream().map(Person::IMC).mapToDouble(Double::doubleValue).summaryStatistics();
                            System.out.println("Statistics resume of age field :: \n" + dssAge);
                            System.out.println("Statistics resume of IMC field :: \n" + dssIMC);
                        }
                );

        br.close();
    }
}
