package moai.dominio.consorcio;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import moai.dominio.consorcio.Consorciado;
import moai.dominio.consorcio.Consorcio;
import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class ConsorcioBuilder {

	private List<Consorciado> listaDeConsorciados;
	private Consorciado gerenteDoConsorcio;
	private BigDecimal valorDaParcela;

	public ConsorcioBuilder() {
		this.listaDeConsorciados = Arrays.asList(ConsorciadoBuilder.novo().criar());
		this.gerenteDoConsorcio = ConsorciadoBuilder.novo().comNome("Jorge Gerente da Silva").criar();
		this.valorDaParcela = BigDecimal.TEN;
	}

	public static ConsorcioBuilder novo() {
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

	public Consorcio criar() throws ExcecaoDeCampoObrigatorio, ParcelaDeveSerMaiorQueZero {
		Consorcio consorcio = new Consorcio(gerenteDoConsorcio, valorDaParcela);
		consorcio.setConsorciados(listaDeConsorciados);
		return consorcio;
	}
}