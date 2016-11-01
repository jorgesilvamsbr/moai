package moai.dominio.moai;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Moai {

	@OneToMany
	private List<Pessoa> pessoas;

	public Moai() {
	}

	public void setPessoas(List<Pessoa> pessoas) throws ExcecaoDeCampoObrigatorio {
		validarSeAListaNaoEstaVazia(pessoas);
		this.pessoas = pessoas;
	}
	
	private void validarSeAListaNaoEstaVazia(List<Pessoa> pessoas) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoListaVazia(pessoas, "Lista de pessoas esta vazia")
		.entaoDispara();
	}

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}
}
