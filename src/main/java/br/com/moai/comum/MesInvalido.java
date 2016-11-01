package br.com.moai.comum;


public class MesInvalido extends Exception {

	private static final long serialVersionUID = 6639552615562248467L;
	
	public MesInvalido() {
		super("O mês informado é inválido.");
	}

}
