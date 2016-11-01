package moai.dominio.consorcio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Consorcio {

	@OneToMany
	private List<Consorciado> consorciados;
	
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

	public void setConsorciados(List<Consorciado> consorciados) throws ExcecaoDeCampoObrigatorio {
		validarSeAListaNaoEstaVazia(consorciados);
		this.consorciados = consorciados;
	}

	public List<Consorciado> getConsorciados() {
		return this.consorciados;
	}
	
	public Consorciado getGerente() {
		return gerente;
	}
	
	private void validarSeAListaNaoEstaVazia(List<Consorciado> consorciados) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoListaVazia(consorciados, "Lista de consorciados esta vazia")
		.entaoDispara();
	}
	
}
