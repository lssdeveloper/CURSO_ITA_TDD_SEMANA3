package com.leandroserra.teste;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.leandroserra.mock.MockHardware;
import com.leandroserra.mock.MockServiceRemoto;
import com.leandroserra.modelo.CaixaEletronico;
import com.leandroserra.modelo.ContaCorrente;
import com.leandroserra.modelo.HardwareException;
import com.leandroserra.modelo.ProblemaEncontradoCCException;
import com.leandroserra.modelo.SemSaldoException;

public class TesteCaixaEletronico {
	
	@Test(expected = ProblemaEncontradoCCException.class)
	public void testeUsuarioInvalido() throws ProblemaEncontradoCCException, HardwareException {
		String numContaCorrente = "7";

		MockServiceRemoto servicoRemoto = new MockServiceRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);
		caixa.logar(numContaCorrente);
	}	
	
	@Test
	public void testeUsuarioValido() throws ProblemaEncontradoCCException, HardwareException {
		
		String contaSolicitada = "1";

		MockServiceRemoto servicoRemoto = new MockServiceRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		String retorno = caixa.logar(contaSolicitada);
		assertEquals("Usuário Autenticado", retorno);
		assertEquals(contaSolicitada, caixa.getLogContaCorrente());
	}
	@Test
	public void testeSaldo() throws ProblemaEncontradoCCException, HardwareException {
		String contaSolicitada = "1";
		MockServiceRemoto servicoRemoto = new MockServiceRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		String retorno = caixa.saldo();
		
		assertEquals("O saldo é R$111111", retorno);
	}	
	@Test(expected=SemSaldoException.class)
	public void testeSaqueSemSaldo() throws ProblemaEncontradoCCException, SemSaldoException, HardwareException {
		String contaSolicitada = "2";
		MockServiceRemoto servicoRemoto = new MockServiceRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		caixa.sacar(new BigDecimal(222222));
	}
	
	@Test
	public void testeSaqueComSucesso() throws ProblemaEncontradoCCException, SemSaldoException, HardwareException {
		
		BigDecimal valorSacado = new BigDecimal(2222);
		String contaSolicitada = "2";
		MockServiceRemoto servicoRemoto = new MockServiceRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		String retorno = caixa.sacar(valorSacado);
		
		ContaCorrente contaCorrente = mockHardware.getServicoRemoto().recuperarConta(contaSolicitada);
		
		assertEquals("Retire seu dinheiro", retorno);
		assertEquals(new BigDecimal(220000), contaCorrente.getSaldo());
	}	
	@Test(expected=HardwareException.class)
	public void testeSaqueException() throws ProblemaEncontradoCCException, SemSaldoException, HardwareException {
		
		BigDecimal valorSacado = new BigDecimal(500);
		String contaSolicitada = "2";
		MockServiceRemoto servicoRemoto = new MockServiceRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		mockHardware.setState(false);
		caixa.sacar(valorSacado);
		
	}	
	@Test
	public void testeDeposito() throws ProblemaEncontradoCCException, SemSaldoException, HardwareException {
		
		BigDecimal valorDeposito = new BigDecimal(6667);
		String contaSolicitada = "3";
		MockServiceRemoto servicoRemoto = new MockServiceRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		String retorno = caixa.depositar(valorDeposito);
		
		ContaCorrente contaCorrente = mockHardware.getServicoRemoto().recuperarConta(contaSolicitada);
		
		assertEquals("Depósito recebido com sucesso", retorno);
		assertEquals(new BigDecimal(340000), contaCorrente.getSaldo());
	}	
	
	@Test(expected=HardwareException.class)
	public void testeDepositoException() throws ProblemaEncontradoCCException, SemSaldoException, HardwareException {
		
		BigDecimal valorDeposito = new BigDecimal(500);
		String contaSolicitada = "3";
		MockServiceRemoto servicoRemoto = new MockServiceRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);
		CaixaEletronico caixa = new CaixaEletronico(mockHardware);
		
		caixa.logar(contaSolicitada);
		
		mockHardware.setState(false);
		caixa.depositar(valorDeposito);
		
	}
	@Test(expected=HardwareException.class)
	public void testeHardwareException() throws ProblemaEncontradoCCException, SemSaldoException, HardwareException {
		String contaSolicitada = "1";

		MockServiceRemoto servicoRemoto = new MockServiceRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		mockHardware.setState(false);
		caixa.logar(contaSolicitada);				
		
	}	
}
