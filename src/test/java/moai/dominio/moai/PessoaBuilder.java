package moai.dominio.moai;

import moai.dominio.moai.Pessoa;

public class PessoaBuilder {

	public static PessoaBuilder novo() {
		return new PessoaBuilder();
	}

	public Pessoa criar() {
		return new Pessoa();
	}

}
