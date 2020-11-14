package com.example.appcarro;

public class DtoCarro {
    private int id, ano;
    private String Marca, Modelo, Cor;
    private double valor;

    public DtoCarro(int i, String s, String toString, String string, double v) {
        this.ano = ano;
        Marca = Marca;
        Modelo = Modelo;
        Cor = Cor;
        this.valor = valor;
    }

    public DtoCarro() {
    }

    @Override
    public String toString() {
        return Marca + " " + Modelo + " " + Cor + " " + ano + " Valor: R$" +valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getCor() {
        return Cor;
    }

    public void setCor(String cor) {
        Cor = cor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
