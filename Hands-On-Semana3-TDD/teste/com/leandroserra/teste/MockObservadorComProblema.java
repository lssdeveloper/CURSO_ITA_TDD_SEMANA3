package com.leandroserra.teste;

public class MockObservadorComProblema implements ObservadorCarrinho {

	@Override
	public void produtoAdicionado(String nme, int valor) {
		throw new RuntimeException("Problema simulado pelo mock");
	}

}
