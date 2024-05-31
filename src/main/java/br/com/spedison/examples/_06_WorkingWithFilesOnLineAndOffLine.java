package br.com.spedison.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

class Jogo {
    static public Jogo CriaJogo(String linha) {
        String[] linhaSeparada = linha.trim().split("[;]");
        return new Jogo(linhaSeparada[1], linhaSeparada[2],
                Integer.parseInt(linhaSeparada[3]),
                Integer.parseInt(linhaSeparada[4])
        );
    }

    public Jogo(String nomeP1, String nomeP2, int gols1, int gols2) {
        this.nomeP1 = nomeP1;
        this.nomeP2 = nomeP2;
        this.gols1 = gols1;
        this.gols2 = gols2;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Jogo{");
        sb.append("nomeP1='").append(nomeP1).append('\'');
        sb.append(", nomeP2='").append(nomeP2).append('\'');
        sb.append(", gols1=").append(gols1);
        sb.append(", gols2=").append(gols2);
        sb.append('}');
        return sb.toString();
    }

    String nomeP1;
    String nomeP2;
    int gols1;
    int gols2;
}

public class _06_WorkingWithFilesOnLineAndOffLine {
    public static void main(String[] args) throws URISyntaxException, IOException {

//        String fileUrl = "file:///tmp/bundesliga-1819.csv";
        String fileUrl = "https://raw.githubusercontent.com/1920-3ahitm-sew/assigment01-football-league-PhilippEdlinger/master/bundesliga-1819.csv";
        URL url = null;
        try {
            url = new URL(fileUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        InputStream fis = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
        final int[] contaLinhas = {0};
        br.
                lines().
                skip(1).
                map(Jogo::CriaJogo).
                peek(jogo -> contaLinhas[0]++).
                forEach(System.out::println);
        System.out.println("Linhas contadas = " + contaLinhas[0]);
        br.close();
    }
}