package moai.dominio.consorcio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import br.com.moai.comum.DateUtils;
import br.com.moai.comum.MesInvalido;
import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Consorcio {

	private static final int PRIMEIRO = 0;

	@ManyToMany
	private List<Consorciado> consorciados;

	private List<Consorciado> contemplados = new ArrayList<>();

	@OneToOne
	private Consorciado gerente;

	private BigDecimal valorDaParcela;

	private Date dataInicial;

	private Date dataFinal;

	private int diaDeVencimento;

	private int diaDeContemplacao;

	public Consorcio(Consorciado gerente, BigDecimal valorDaParcela, Date dataInicial, int vencimento, int contemplacao)
			throws ExcecaoDeCampoObrigatorio, ParcelaDeveSerMaiorQueZero, DatasInvalidas, DiaInvalido, MesInvalido {
		validarCamposObrigatorios(gerente, valorDaParcela, dataInicial);
		verificaSeAParcelaEhMaiorQueZero(valorDaParcela);
		diaEhZerado(contemplacao, vencimento);
		diaMaiorQueTrintaEUm(contemplacao, vencimento);
		this.diaDeVencimento = vencimento;
		this.diaDeContemplacao = contemplacao;
		this.dataInicial = dataInicial;
		this.dataFinal = DateUtils.incrementarMesDa(dataInicial);
		this.valorDaParcela = valorDaParcela;
		this.gerente = gerente;
	}

	public void setConsorciados(List<Consorciado> consorciados) throws ExcecaoDeCampoObrigatorio, MesInvalido {
		validarSeAListaNaoEstaVazia(consorciados);
		atualizarOMesFinalDoConsorcio(consorciados);
		this.consorciados = consorciados;
	}

	private void atualizarOMesFinalDoConsorcio(List<Consorciado> consorciados) throws MesInvalido {
		int tamanoDaLista = consorciados.size();
		while(tamanoDaLista-- > 0){
			dataFinal = DateUtils.incrementarMesDa(dataFinal);
		}
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

	public int getDiaDeVencimento() {
		return diaDeVencimento;
	}

	public int getDiaDeContemplacao() {
		return diaDeContemplacao;
	}

	public Consorciado obterContemplado() {
		Collections.shuffle(consorciados);
		Consorciado contemplado = consorciados.get(PRIMEIRO);
		consorciados.remove(contemplado);
		contemplados.add(contemplado);
		return contemplado;
	}

	public List<Consorciado> getContemplados() {
		return contemplados;
	}

	private void validarSeAListaNaoEstaVazia(List<Consorciado> consorciados) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio().quandoListaVazia(consorciados, "Lista de consorciados esta vazia")
				.entaoDispara();
	}

	private void validarCamposObrigatorios(Consorciado gerente, BigDecimal valorDaParcela, Date dataInicial)
			throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio().quandoNulo(gerente, "Responsavel pelo consorcio não foi informado")
				.quandoNulo(valorDaParcela, "Parcela não informada")
				.quandoNulo(dataInicial, "Data inicial não informada").entaoDispara();
	}

	private void diaMaiorQueTrintaEUm(int contemplacao, int vencimento) throws DiaInvalido {
		if (contemplacao > 31 || vencimento > 31) {
			throw new DiaInvalido();
		}
	}

	private void diaEhZerado(int contemplacao, int vencimento) throws DiaInvalido {
		if (contemplacao == 0 || vencimento == 0) {
			throw new DiaInvalido();
		}
	}

	private void verificaSeAParcelaEhMaiorQueZero(BigDecimal valorDaParcela) throws ParcelaDeveSerMaiorQueZero {
		if (BigDecimal.ZERO.compareTo(valorDaParcela) == 0) {
			throw new ParcelaDeveSerMaiorQueZero();
		}
	}
}