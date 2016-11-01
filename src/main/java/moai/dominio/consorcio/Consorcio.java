package moai.dominio.consorcio;

import java.math.BigDecimal;
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

	private BigDecimal valorDaParcela;

	public Consorcio(Consorciado gerente, BigDecimal valorDaParcela) throws ExcecaoDeCampoObrigatorio, ParcelaDeveSerMaiorQueZero {
		validarCamposObrigatorios(gerente, valorDaParcela);
		verificaSeAParcelaEhMaiorQueZero(valorDaParcela);
		this.valorDaParcela = valorDaParcela;
		this.gerente = gerente;
	}

	private void verificaSeAParcelaEhMaiorQueZero(BigDecimal valorDaParcela) throws ParcelaDeveSerMaiorQueZero {
		if(BigDecimal.ZERO.compareTo(valorDaParcela) == 0){
			throw new ParcelaDeveSerMaiorQueZero();
		}
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

	public BigDecimal getValorDaParcela() {
		return valorDaParcela;
	}
	
	private void validarSeAListaNaoEstaVazia(List<Consorciado> consorciados) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoListaVazia(consorciados, "Lista de consorciados esta vazia")
		.entaoDispara();
	}
	
	private void validarCamposObrigatorios(Consorciado gerente, BigDecimal valorDaParcela) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoNulo(gerente, "Responsavel pelo consorcio não foi informado")
		.quandoNulo(valorDaParcela, "Parcela não informada")
		.entaoDispara();
	}
}