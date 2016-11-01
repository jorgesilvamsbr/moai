package moai.dominio;

import java.util.ArrayList;
import java.util.List;

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

	public Moai criar() {
		return new Moai(this.pessoas);
	}
}
