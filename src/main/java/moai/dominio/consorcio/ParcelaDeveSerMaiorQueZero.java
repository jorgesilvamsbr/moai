package moai.dominio.consorcio;

import moai.dominio.excecao.ExcecaoDeRegraDeNegocio;

public class ParcelaDeveSerMaiorQueZero extends ExcecaoDeRegraDeNegocio {
	private static final long serialVersionUID = 4297454116218820880L;

	public ParcelaDeveSerMaiorQueZero() {
		super("O valor da parcela deve ser maior que zero");
	}
}
