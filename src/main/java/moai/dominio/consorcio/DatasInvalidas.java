package moai.dominio.consorcio;

import moai.dominio.excecao.ExcecaoDeRegraDeNegocio;

public class DatasInvalidas extends ExcecaoDeRegraDeNegocio {
	private static final long serialVersionUID = 5057206576281604808L;
	
	public DatasInvalidas() {
		super("A data de fim do consorcio deve ser maior que a data inicial");
	}
}