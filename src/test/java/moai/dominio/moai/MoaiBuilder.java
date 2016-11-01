package moai.dominio.moai;

import java.util.ArrayList;
import java.util.List;

import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;
import moai.dominio.moai.Moai;
import moai.dominio.moai.Pessoa;

public class MoaiBuilder {

	private List<Pessoa> pessoas;
	public MoaiBuilder() {
		this.pessoas = new ArrayList<>();
	}

	public static MoaiBuilder novo() {
		return new MoaiBuilder();
	}

	public MoaiBuilder comPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
		return this;
	}

	public Moai criar() throws ExcecaoDeCampoObrigatorio {
		Moai moai = new Moai();
		moai.setPessoas(pessoas);
		return moai;
	}
}
