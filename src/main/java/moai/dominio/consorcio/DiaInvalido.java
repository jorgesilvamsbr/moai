package moai.dominio.consorcio;

import moai.dominio.excecao.ExcecaoDeRegraDeNegocio;

public class DiaInvalido extends ExcecaoDeRegraDeNegocio{
	private static final long serialVersionUID = -212492411158936005L;
	
	public DiaInvalido() {
		super("Dia informado é invalido");
	}
}