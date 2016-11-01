package moai.dominio.consorcio;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import moai.dominio.consorcio.Consorciado;
import moai.dominio.consorcio.Consorcio;
import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class ConsorcioTest {

	private static final Consorciado CONSORCIADO_NULO = null;
	private static final BigDecimal VALOR_DA_PARCELA_NULA = null;
	private static final BigDecimal VALOR_DA_PARCELA_ZERADO = BigDecimal.ZERO;

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
		
		assertEquals(quantidadeEsperada, consorcio.getConsorciados().size());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_informar_uma_lista_de_consorciados_vazias() throws Exception {
		ConsorcioBuilder.novo().comConsorciados(Arrays.asList()).criar();
	}
	
	@Test
	public void um_consorcio_deve_ter_o_valor_da_parcela() throws Exception {
		BigDecimal valorDaParcela = BigDecimal.valueOf(100d);
		
		Consorcio consorcio = ConsorcioBuilder.novo().comValorDaParcela(valorDaParcela).criar();
		
		assertEquals(valorDaParcela, consorcio.getValorDaParcela());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_permitir_criar_um_consorcio_sem_o_valor_da_parcela() throws Exception {
		ConsorcioBuilder.novo().comValorDaParcela(VALOR_DA_PARCELA_NULA).criar();
	}
	
	@Test(expected = ParcelaDeveSerMaiorQueZero.class)
	public void nao_deve_permitir_informar_uma_parcela_com_valor_zerado() throws Exception {
		ConsorcioBuilder.novo().comValorDaParcela(VALOR_DA_PARCELA_ZERADO).criar();
	}
}