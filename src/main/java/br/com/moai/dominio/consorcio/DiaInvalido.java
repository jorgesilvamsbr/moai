package br.com.moai.dominio.consorcio;

import br.com.moai.dominio.excecao.ExcecaoDeRegraDeNegocio;

public class DiaInvalido extends ExcecaoDeRegraDeNegocio{
	private static final long serialVersionUID = -212492411158936005L;
	
	public DiaInvalido() {
		super("Dia informado é invalido");
	}
}