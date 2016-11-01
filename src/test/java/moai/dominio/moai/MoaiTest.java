package moai.dominio.moai;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;
import moai.dominio.moai.Consorcio;
import moai.dominio.moai.Consorciado;

public class MoaiTest {

	private static final Consorciado CONSORCIADO_NULO = null;

	@Test
	public void um_consorcio_deve_ter_um_gerente() throws Exception {
		String nomeDoGerente = "Jorge Luiz Gomes da Silva";
		Consorciado gerenteDoConsorcio = ConsorciadoBuilder.novo().comNome(nomeDoGerente).criar();
		
		Consorcio consorcio = ConsorcioBuilder.novo().comGerente(gerenteDoConsorcio).criar();
		
		assertEquals(nomeDoGerente, consorcio.getGerente().getNome());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_criar_um_consorcio_sem_gerente() throws Exception {
		ConsorcioBuilder.novo().comGerente(CONSORCIADO_NULO).criar();
	}

	@Test
	public void um_moai_deve_ter_uma_lista_de_consorciados() throws Exception {
		int quantidadeEsperada = 2;
		List<Consorciado> listaDeConsorciados = Arrays.asList(ConsorciadoBuilder.novo().criar(), ConsorciadoBuilder.novo().criar());
		
		Consorcio consorcio = ConsorcioBuilder.novo().comConsorciados(listaDeConsorciados).criar();
		
		assertEquals(quantidadeEsperada, consorcio.getPessoas().size());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_informar_uma_lista_de_consorciados_vazias() throws Exception {
		ConsorcioBuilder.novo().comConsorciados(Arrays.asList()).criar();
	}
}