package com.leandroserra.modelo;

public interface IServicoRemoto {
	
	public ContaCorrente recuperarConta(String numContaCorrente) throws ProblemaEncontradoCCException;
	//grava alterações, como uma mudança no saldo devido a um saque ou depósito
	//deve ser chamado somente no caso de ser feito movimenatçãoes com sucesso
	public void persistirConta(ContaCorrente contaCorrente); 

}
