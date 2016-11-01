package moai.dominio.moai;

import java.util.Arrays;
import java.util.List;

import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;
import moai.dominio.moai.Consorcio;
import moai.dominio.moai.Consorciado;

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
		moai.setPessoas(listaDeConsorciados);
		return moai;
	}
}
