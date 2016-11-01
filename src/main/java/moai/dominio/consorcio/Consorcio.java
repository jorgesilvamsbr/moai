package moai.dominio.consorcio;

import java.math.BigDecimal;
import java.util.Date;
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

	private Date dataInicial;

	private Date dataFinal;

	public Consorcio(Consorciado gerente, BigDecimal valorDaParcela, Date dataInicial, Date dataFinal) throws ExcecaoDeCampoObrigatorio, ParcelaDeveSerMaiorQueZero, DatasInvalidas {
		validarCamposObrigatorios(gerente, valorDaParcela, dataInicial, dataFinal);
		verificaSeAParcelaEhMaiorQueZero(valorDaParcela);
		validarDatas(dataInicial, dataFinal);
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
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
	
	public Date getDataFinal() {
		return dataFinal;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}
	
	private void validarSeAListaNaoEstaVazia(List<Consorciado> consorciados) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoListaVazia(consorciados, "Lista de consorciados esta vazia")
		.entaoDispara();
	}
	
	private void validarCamposObrigatorios(Consorciado gerente, BigDecimal valorDaParcela, Date dataInicial, Date dataFinal) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoNulo(gerente, "Responsavel pelo consorcio não foi informado")
		.quandoNulo(valorDaParcela, "Parcela não informada")
		.quandoNulo(dataInicial, "Data inicial não informada")
		.quandoNulo(dataFinal, "Data final não informada")
		.entaoDispara();
	}
	
	private void validarDatas(Date dataInicial, Date dataFinal) throws DatasInvalidas {
		if(dataFinal.compareTo(dataInicial) <= 0){
			throw new DatasInvalidas();
		}
	}
}