package br.com.spedison.vo;


public class Person {
    public String name;
    public int age;
    public float weight;
    public float height;
    public String city;
    public int registerNumber;

    public double IMC() {
        return weight / Math.pow(height, 2.);
    }

    public int getAge(){
        return age;
    }

    public String getCity(){
        return city;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public String getRangeIMC() {
        if (IMC() < 18.50) return "Magreza";
        if( IMC() >= 18.50 && IMC() < 24.99) return "Peso Normal.";
        if( IMC() >= 24.99 && IMC() < 29.99) return "Pré-Obesidade.";
        if( IMC() >= 29.99 && IMC() < 34.99) return "Obesidade Grau I.";
        if( IMC() >= 34.99 && IMC() < 40.00) return "Obesidade Grau II.";
        if( IMC() >= 40.00) return "Obesidade Grau III.";
        throw new IllegalArgumentException("Invalid IMC: " + IMC());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pessoa{");
        sb.append("Numero Registro ='").append(registerNumber).append("\' ");
        sb.append(", nome='").append(name).append('\'');
        sb.append(", idade=").append(age);
        sb.append(", peso=").append(weight);
        sb.append(", altura=").append(height);
        sb.append(", IMC=").append(IMC());
        sb.append(", cidade='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Person strToPessoaCSV(String line, Integer numeroRegistro) {
        String[] campos = line.split(";");
        Person ret = new Person();
        ret.name = campos[0];
        ret.age = Integer.parseInt(campos[1]);
        ret.weight = Float.parseFloat(campos[2]);
        ret.height = Float.parseFloat(campos[3]);
        ret.city = campos[4];
        ret.registerNumber = numeroRegistro;
        return ret;
    }

    public static Person strToPessoaSPV(String line, Integer numeroRegistro) {
        String[] campos = line.split(",");
        Person ret = new Person();
        ret.name = campos[0];
        ret.age = Integer.parseInt(campos[1]);
        ret.weight = Float.parseFloat(campos[2]);
        ret.height = Float.parseFloat(campos[3]);
        ret.city = campos[4];
        ret.registerNumber = numeroRegistro;
        return ret;
    }
}
