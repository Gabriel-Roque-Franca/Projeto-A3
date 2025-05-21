// src/model/Produto.java
package model;

public class Produto {
    private int id;
    private String nome;
    private String tamanho;
    private double valorUnitario;
    private int estoque; // Opcional, se você adicionou no banco

    // Construtor
    public Produto(int id, String nome, String tamanho, double valorUnitario, int estoque) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.valorUnitario = valorUnitario;
        this.estoque = estoque;
    }

    // --- Getters (métodos para obter os valores) ---
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getTamanho() { return tamanho; }
    public double getValorUnitario() { return valorUnitario; }
    public int getEstoque() { return estoque; }

    // --- Setters (métodos para alterar os valores - nem sempre necessários para todos os campos) ---
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public void setValorUnitario(double valorUnitario) { this.valorUnitario = valorUnitario; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
}