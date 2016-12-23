package br.com.moai.dominio.excecao;

public abstract class ExcecaoDeRegraDeNegocio extends Exception {
	private static final long serialVersionUID = -473207888436974769L;

	public ExcecaoDeRegraDeNegocio() {
	}

	public ExcecaoDeRegraDeNegocio(String msg) {
		super(msg);
	}

}