package com.leandroserra.mock;

import com.leandroserra.modelo.ContaCorrente;
import com.leandroserra.modelo.HardwareException;
import com.leandroserra.modelo.IHardware;
import com.leandroserra.modelo.IServicoRemoto;
import com.leandroserra.modelo.ProblemaEncontradoCCException;

public class MockHardware implements IHardware{
	
	public IServicoRemoto servicoRemoto;
	
	public Boolean state;
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	
	public MockHardware(MockServiceRemoto serviceRemoto) {
		servicoRemoto = serviceRemoto;
		state = true;
	}

	@Override
	public String pegarNumeroDaContaCartao(String numContaCorrente)
			throws ProblemaEncontradoCCException, HardwareException {
		if(!getState()) throw new HardwareException("Sistema indisponível, ao recuperar número da conta!");
		ContaCorrente cc = servicoRemoto.recuperarConta(numContaCorrente);
		return cc.getNumCC();
	}

	@Override
	public void entregarDinheiro() throws HardwareException {
		if(!getState()) throw new HardwareException("Sistema indisponível, ao entregar dinheiro!");		
	}

	@Override
	public void lerEnvelope() throws HardwareException {
		if(!getState()) throw new HardwareException("Sistema indisponível, ao ler envelope!");		
	}


	public IServicoRemoto getServicoRemoto(){
		return servicoRemoto;
	}

	public void setServicoRemoto(IServicoRemoto servicoRemoto){
		this.servicoRemoto = servicoRemoto;
	}

}
