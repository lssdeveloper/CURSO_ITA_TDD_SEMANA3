package com.leandroserra.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import com.leandroserra.projeto.CarrinhoCompras;
import com.leandroserra.projeto.Produto;

public class TesteCarrinhoCompras {
	
	@Test
	public void totalCarrinho() {
		CarrinhoCompras c = new CarrinhoCompras();
		c.adicionaProduto(new Produto("tenis", 100));
		c.adicionaProduto(new Produto("camiseta", 50));
		c.adicionaProduto(new Produto("bremuda", 70));
		assertEquals(220, c.total());
	}
	@Test
	public void escutaAdicaodeProduto(){
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock = new MockObservadorCarrinho();
		c.adicionarObservador(mock);
		c.adicionaProduto(new Produto("tenis", 100));
		mock.verificaRecebimentoProduto("tenis", 100);
	}
	@Test
	public void adicionarDoisObservadores(){
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		c.adicionarObservador(mock1);
		MockObservadorCarrinho mock2 = new MockObservadorCarrinho();
		c.adicionarObservador(mock2);		
		c.adicionaProduto(new Produto("tenis", 100));
		mock1.verificaRecebimentoProduto("tenis", 100);
		mock2.verificaRecebimentoProduto("tenis", 100);
	}
	@Test
	public void continuaNotificandoComErroEmObservador(){
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		c.adicionarObservador(mock1);
		ObservadorCarrinho mock2 = new MockObservadorComProblema();
		c.adicionarObservador(mock2);	
		MockObservadorCarrinho mock3 = new MockObservadorCarrinho();
		c.adicionarObservador(mock3);		
		c.adicionaProduto(new Produto("tenis", 100));
		mock1.verificaRecebimentoProduto("tenis", 100);
		mock3.verificaRecebimentoProduto("tenis", 100);		
	}
}
