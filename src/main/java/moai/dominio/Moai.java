package moai.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Moai {

	@OneToMany
	private List<Pessoa> pessoas;

	public Moai(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}
}
