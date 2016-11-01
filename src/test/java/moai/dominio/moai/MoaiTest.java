package moai.dominio.moai;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import moai.dominio.excecao.ExcecaoDeCampoObrigatorio;
import moai.dominio.moai.Moai;
import moai.dominio.moai.Pessoa;

public class MoaiTest {

	@Test
	public void um_moai_deve_ter_mais_de_uma_pessoa() throws Exception {
		int quantidadeEsperada = 2;
		List<Pessoa> listaDePessoas = Arrays.asList(PessoaBuilder.novo().criar(), PessoaBuilder.novo().criar());
		
		Moai moai = MoaiBuilder.novo().comPessoas(listaDePessoas).criar();
		
		assertEquals(quantidadeEsperada, moai.getPessoas().size());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_informar_uma_lista_de_pessoas_vazias() throws Exception {
		MoaiBuilder.novo().comPessoas(Arrays.asList()).criar();
	}
}
