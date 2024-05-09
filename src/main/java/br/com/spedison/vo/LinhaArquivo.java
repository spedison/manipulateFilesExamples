package br.com.spedison.vo;



public record LinhaArquivo(

    String municipio,
    String cargo,
    String partido,
    String nomeCandidato,
    int quantidadeVotos

){
    // Colunas com as informações que queremos ler do Arquivo
    static public final int [] POSICAO_COLUNA = {
            12,17,20,30,31
    };
}
