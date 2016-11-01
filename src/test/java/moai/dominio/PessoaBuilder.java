package moai.dominio;

import moai.dominio.Pessoa;

public class PessoaBuilder {

	public static PessoaBuilder novo() {
		return new PessoaBuilder();
	}

	public Pessoa criar() {
		return new Pessoa();
	}

}
