package com.leandroserra.modelo;

import java.math.BigDecimal;

public class ContaCorrente {
	
	private String numCC;
	private BigDecimal saldo;
	
	public ContaCorrente(String numCC, BigDecimal saldo){
		setNumCC(numCC);
		setSaldo(saldo);		
	}
	public void setNumCC(String numCC) {
		this.numCC = numCC;
	}
	public String getNumCC() {
		return numCC;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
}
