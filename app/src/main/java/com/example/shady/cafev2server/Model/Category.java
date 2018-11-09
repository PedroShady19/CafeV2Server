package com.example.shady.cafev2server.Model;

public class Category {
    private String Name;
    private String Imagem;

    public Category() {
    }

    public Category(String name, String imagem) {
        Name = name;
        Imagem = imagem;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        Imagem = imagem;
    }
}
