package com.leandroserra.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.leandroserra.modelo.ContaCorrente;
import com.leandroserra.modelo.IServicoRemoto;
import com.leandroserra.modelo.ProblemaEncontradoCCException;

public class MockServiceRemoto implements IServicoRemoto{
	
	private List<ContaCorrente> contasBancarias;
	
	public List<ContaCorrente> getContasBancarias() {
		return contasBancarias;
	}

	public void setContasBancarias(List<ContaCorrente> contasBancarias) {
		this.contasBancarias = contasBancarias;
	}

	public MockServiceRemoto(){
		contasBancarias = new ArrayList<>();
		ContaCorrente c1 = new ContaCorrente("1", new BigDecimal(111111)); 
		ContaCorrente c2 = new ContaCorrente("2", new BigDecimal(222222)); 
		ContaCorrente c3 = new ContaCorrente("3", new BigDecimal(333333)); 
		contasBancarias.add(c1);
		contasBancarias.add(c2);
		contasBancarias.add(c3);
	}

	@Override
	public ContaCorrente recuperarConta(String numContaCorrente) 
			throws ProblemaEncontradoCCException{
		
		for(ContaCorrente cc : contasBancarias) {
			if(cc.getNumCC().equals(numContaCorrente)) return cc;
		}
		throw new ProblemaEncontradoCCException("Conta não encontrada!");
	}

	@Override
	public void persistirConta(ContaCorrente contaCorrente) {
		for(ContaCorrente cc : contasBancarias)
		{
			if(cc.getNumCC().equals(contaCorrente.getNumCC())) cc.setSaldo(contaCorrente.getSaldo());
		}		
	}
	

}
