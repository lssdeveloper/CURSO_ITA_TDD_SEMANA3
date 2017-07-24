package com.leandroserra.teste;

import static org.junit.Assert.*;

public class MockObservadorCarrinho implements ObservadorCarrinho {
	
	private String nomeRecebido;
	private int valorRecebido;
	private boolean darErro = false;

	@Override
	public void produtoAdicionado(String nome, int valor) {
		if (darErro) 
			throw new RuntimeException("Problema simulado pelo mock");
		this.nomeRecebido = nome;
		this.valorRecebido = valor;		
	}

	public void verificaRecebimentoProduto(String nomeEsperado, int valorEsperado) {
		assertEquals(nomeEsperado, nomeRecebido);
		assertEquals(valorEsperado, valorRecebido);
		
	}

	public void queroQueDeErroAqui() {
		darErro = true;		
	}

}
