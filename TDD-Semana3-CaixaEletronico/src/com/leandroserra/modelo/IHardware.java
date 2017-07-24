package com.leandroserra.modelo;

public interface IHardware {

	/*
	 * para ler o número da conta do cartão para o login (retorna uma String com
	 * o número da conta)
	 */
	public String pegarNumeroDaContaCartao(String numContaCorrente)
			throws ProblemaEncontradoCCException, HardwareException;

	/*
	 * entrega o dinheiro no caso do saque (retorna void)
	 */
	public void entregarDinheiro() throws HardwareException;

	/*
	 * recebe o envelope com dinheiro na operação de depósito (retorna void)
	 */
	public void lerEnvelope() throws HardwareException;
	
	public IServicoRemoto getServicoRemoto() throws HardwareException;
	
	public void setServicoRemoto(IServicoRemoto servicoRemoto) throws HardwareException;
}
