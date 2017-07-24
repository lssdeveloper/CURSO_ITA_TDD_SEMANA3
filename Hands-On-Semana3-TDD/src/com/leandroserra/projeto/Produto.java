package com.leandroserra.projeto;

public class Produto {
	private String nome;
	private int valor;
	
	public Produto(String nome, int valor){
		this.nome = nome;
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public int getValor() {
		return valor;
	}
}
