package br.com.spedison.examples;

import br.com.spedison.vo.Person;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/***
 * OBS.: 1) This example is based from https://www.baeldung.com/java-groupingby-collector
 *       2) Do not exists Tuple or Pair in simple jdk. So I used ImmutablePair from apache commons.
 *       3) If you do not use this lib, make a simple class with all field or record class.
 */
public class _07_ReadFileUsingStream_V3 {
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
                collect(
                                Collectors.groupingBy(
                                        (p)-> {
                                            return new ImmutablePair(p.getCity(), p.getRangeIMC());
                                        }
                                )).
                forEach(
                        (k, v) -> {
                            System.out.println("City : " + k.getLeft() + " with IMC is <<" + k.getRight() + ">> and with " + v.size() + " people.");
                            final int [] numberOfPeople = {0};
                            v.stream().
                                    map(s->String.format("%04d - %s with %d years old",numberOfPeople[0]++,s.getName(), s.getAge())).
                                    forEach(System.out::println);
                        }
                );
        br.close();
    }
}
