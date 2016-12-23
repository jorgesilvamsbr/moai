package br.com.moai.aplicacao.consorcio;

import br.com.moai.aplicacao.testebase.TesteBase;
import br.com.moai.dominio.consorcio.Consorciado;
import br.com.moai.dominio.consorcio.ConsorciadoBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class AdicionaConsorciadoTest extends TesteBase{

    @Autowired private AdicionaConsorciado adicionaConsorciado;

    @Test
    public void deve_adicionar_um_consorciado() throws Exception {
        Consorciado consorciado = ConsorciadoBuilder.novo().criar();
        ConsorciadoRequest consorciadoRequest = new ConsorciadoRequest();
        consorciadoRequest.setNome(consorciado.getNome());

        adicionaConsorciado.adicionar(consorciadoRequest);

        assertNotNull(consorciadoRequest.getId());
    }
}