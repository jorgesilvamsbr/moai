package br.com.moai.dominio.consorcio;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.moai.comum.Mes;
import br.com.moai.comum.DateUtils;
import br.com.moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

public class ConsorcioTest {

	private static final Consorciado CONSORCIADO_NULO = null;
	private static final BigDecimal VALOR_DA_PARCELA_NULA = null;
	private static final BigDecimal VALOR_DA_PARCELA_ZERADO = BigDecimal.ZERO;
	private static final Date DATA_NULA = null;
	private List<Consorciado> consorciados;
	
	@Before
	public void init(){
		this.consorciados = new ArrayList<>();
		this.consorciados.add(ConsorciadoBuilder.novo().comNome("Jorge").criar());
		this.consorciados.add(ConsorciadoBuilder.novo().comNome("Paulo").criar());
		this.consorciados.add(ConsorciadoBuilder.novo().comNome("Pedro").criar());
		
	}

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
	
	@Test
	public void deve_poder_informar_o_inicio_do_periodo() throws Exception {
		Date dataInicial = DateUtils.criarDataPara(01, Mes.ABRIL, 2016);
		
		Consorcio consorcio = ConsorcioBuilder.novo().comDataInicial(dataInicial).criar();
		
		assertEquals(dataInicial, consorcio.getDataInicial());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_permitir_informar_datas_nulas() throws Exception {
		ConsorcioBuilder.novo().comDataInicial(DATA_NULA).criar();
	}
	
	@Test
	public void deve_ser_possivel_informar_o_dia_de_vencimento() throws Exception {
		int vencimento = 5;
		
		Consorcio consorcio = ConsorcioBuilder.novo().comDiaDeVencimento(vencimento).criar();
		
		assertEquals(vencimento, consorcio.getDiaDeVencimento());
	}
	
	@Test(expected = DiaInvalido.class)
	public void nao_deve_permitir_informar_o_dia_de_vencimento_zerado() throws Exception {
		ConsorcioBuilder.novo().comDiaDeVencimento(0).criar();
	}
	
	@Test(expected = DiaInvalido.class)
	public void nao_deve_permitir_informar_o_dia_de_vencimento_maior_que_trinta_e_um() throws Exception {
		ConsorcioBuilder.novo().comDiaDeVencimento(32).criar();
	}
	
	@Test
	public void deve_ser_possivel_informar_o_dia_de_contemplacao() throws Exception {
		int contemplacao = 10;
		
		Consorcio consorcio = ConsorcioBuilder.novo().comDiaDeContemplacao(contemplacao).criar();
		
		assertEquals(contemplacao, consorcio.getDiaDeVencimento());
	}
	
	@Test(expected = DiaInvalido.class)
	public void nao_deve_permitir_informar_o_dia_de_contemplacao_zerado() throws Exception {
		ConsorcioBuilder.novo().comDiaDeVencimento(0).criar();
	}
	
	@Test(expected = DiaInvalido.class)
	public void nao_deve_permitir_informar_o_dia_de_contemplacao_maior_que_trinta_e_um() throws Exception {
		ConsorcioBuilder.novo().comDiaDeVencimento(32).criar();
	}
	
	@Test
	public void o_periodo_do_consorcio_deve_ser_igual_a_quantidade_de_participantes() throws Exception {
		Date dataInicial = DateUtils.criarDataPara(01, Mes.ABRIL, 2016);

		Consorcio consorcio = ConsorcioBuilder.novo().comDataInicial(dataInicial).comConsorciados(consorciados).criar();
		
		assertEquals(Mes.JULHO, DateUtils.obterMesDa(consorcio.getDataFinal()));
	}

	@Test
	public void deve_sortear_aleatoriamente_um_consorciado() throws Exception {
		Consorcio consorcio = ConsorcioBuilder.novo().comConsorciados(consorciados).criar();

		Consorciado consorciado = consorcio.obterSorteadoDoMes();

		assertNotNull(consorciado.getNome());
	}

	@Test
	public void um_consorciado_sorteado_nao_deve_ser_sorteado_novamente() throws Exception {
		consorciados = new ArrayList<>();
		consorciados.add(ConsorciadoBuilder.novo().comNome("Gustavo").criar());
		consorciados.add(ConsorciadoBuilder.novo().comNome("Patricia").criar());
		Consorcio consorcio = ConsorcioBuilder.novo().comConsorciados(consorciados).criar();

		Consorciado sorteado1 = consorcio.obterSorteadoDoMes();
		Consorciado sorteado2 = consorcio.obterSorteadoDoMes();

		assertNotEquals(sorteado1.getNome(), sorteado2.getNome());
	}

	@Test
	public void o_sorteio_do_primeiro_mes_deve_ser_para_o_gerente() throws Exception {
		Consorcio consorcio = ConsorcioBuilder.novo().comDataInicial(DateUtils.hoje()).comConsorciados(consorciados).criar();

		Consorciado sorteado1 = consorcio.obterSorteadoDoMes();

		assertEquals(consorcio.getGerente().getNome(), sorteado1.getNome());
	}
}