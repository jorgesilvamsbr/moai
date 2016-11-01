package moai.dominio.moai;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Consorcio {

	@OneToMany
	private List<Consorciado> pessoas;
	
	@OneToOne
	private Consorciado gerente;

	public Consorcio(Consorciado gerente) throws ExcecaoDeCampoObrigatorio {
		validarCamposObrigatorios(gerente);
		this.gerente = gerente;
	}

	private void validarCamposObrigatorios(Consorciado gerente) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoNulo(gerente, "Responsavel pelo consorcio não foi informado")
		.entaoDispara();
	}

	public void setPessoas(List<Consorciado> pessoas) throws ExcecaoDeCampoObrigatorio {
		validarSeAListaNaoEstaVazia(pessoas);
		this.pessoas = pessoas;
	}

	public List<Consorciado> getPessoas() {
		return this.pessoas;
	}
	
	public Consorciado getGerente() {
		return gerente;
	}
	
	private void validarSeAListaNaoEstaVazia(List<Consorciado> pessoas) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoListaVazia(pessoas, "Lista de pessoas esta vazia")
		.entaoDispara();
	}
	
}
