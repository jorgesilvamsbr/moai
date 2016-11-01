package moai.dominio;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import moai.dominio.Pessoa;

public class MoaiTest {

	@Test
	public void um_moai_deve_ter_mais_de_uma_pessoa() throws Exception {
		int quantidadeEsperada = 2;
		List<Pessoa> listaDePessoas = Arrays.asList(PessoaBuilder.novo().criar(), PessoaBuilder.novo().criar());
		
		Moai moai = MoaiBuilder.novo().comPessoas(listaDePessoas).criar();
		
		assertEquals(quantidadeEsperada, moai.getPessoas().size());
	}
}
