package moai.dominio.consorcio;

import java.util.Arrays;
import java.util.List;

import moai.dominio.consorcio.Consorciado;
import moai.dominio.consorcio.Consorcio;
import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class ConsorcioBuilder {

	private List<Consorciado> listaDeConsorciados;
	private Consorciado gerenteDoConsorcio;

	public ConsorcioBuilder() {
		this.listaDeConsorciados = Arrays.asList(ConsorciadoBuilder.novo().criar());
		this.gerenteDoConsorcio = ConsorciadoBuilder.novo().comNome("Jorge Gerente da Silva").criar();
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

	public Consorcio criar() throws ExcecaoDeCampoObrigatorio {
		Consorcio moai = new Consorcio(gerenteDoConsorcio);
		moai.setConsorciados(listaDeConsorciados);
		return moai;
	}
}
