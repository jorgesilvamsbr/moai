package br.com.moai.dominio.consorcio;

import br.com.moai.dominio.excecao.ExcecaoDeRegraDeNegocio;

public class DatasInvalidas extends ExcecaoDeRegraDeNegocio {
	private static final long serialVersionUID = 5057206576281604808L;
	
	public DatasInvalidas() {
		super("A data de fim do consorcio deve ser maior que a data inicial");
	}
}