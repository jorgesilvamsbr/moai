package moai.dominio.consorcio;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.moai.comum.DateUtils;
import br.com.moai.comum.Mes;
import br.com.moai.comum.MesInvalido;
import moai.dominio.consorcio.Consorciado;
import moai.dominio.consorcio.Consorcio;
import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class ConsorcioBuilder {

	private List<Consorciado> listaDeConsorciados;
	private Consorciado gerenteDoConsorcio;
	private BigDecimal valorDaParcela;
	private Date dataInicial;
	private int vencimento;
	private int contemplacao;

	public ConsorcioBuilder() throws ParseException {
		this.listaDeConsorciados = Arrays.asList(ConsorciadoBuilder.novo().criar());
		this.gerenteDoConsorcio = ConsorciadoBuilder.novo().comNome("Jorge Gerente da Silva").criar();
		this.valorDaParcela = BigDecimal.TEN;
		this.dataInicial = DateUtils.criarDataPara(1, Mes.JANEIRO, 2015);
		this.vencimento = 10;
		this.contemplacao = 15;
	}

	public static ConsorcioBuilder novo() throws ParseException {
		return new ConsorcioBuilder();
	}

	public ConsorcioBuilder comConsorciados(List<Consorciado> pessoas) {
		this.listaDeConsorciados = pessoas;
		return this;
	}
	
	public ConsorcioBuilder comGerente(Consorciado gerenteDoConsorcio) {
		this.gerenteDoConsorcio = gerenteDoConsorcio;
		return this;
	}
	
	public ConsorcioBuilder comValorDaParcela(BigDecimal valorDaParcela) {
		this.valorDaParcela = valorDaParcela;
		return this;
	}
	
	public ConsorcioBuilder comDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
		return this;
	}
	
	public ConsorcioBuilder comDiaDeVencimento(int vencimento) {
		this.vencimento = vencimento;
		return this;
	}

	public ConsorcioBuilder comDiaDeContemplacao(int contemplacao) {
		this.contemplacao = contemplacao;
		return this;
	}

	public Consorcio criar() throws ExcecaoDeCampoObrigatorio, ParcelaDeveSerMaiorQueZero, DatasInvalidas, DiaInvalido, MesInvalido {
		Consorcio consorcio = new Consorcio(gerenteDoConsorcio, valorDaParcela, dataInicial, vencimento, contemplacao);
		consorcio.setConsorciados(listaDeConsorciados);
		return consorcio;
	}
}