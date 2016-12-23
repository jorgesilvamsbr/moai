package br.com.moai.dominio.consorcio;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SorteioTest {

    private static final int TAMANHO_ESTIPULADO = 30;

    @Test
    public void deve_sortear_um_numero_menor_que_um_numero_estipulado() throws Exception {
        int resultado = Sorteio.sortear(TAMANHO_ESTIPULADO);
        
        assertTrue(resultado > -1);
        assertTrue(resultado <= TAMANHO_ESTIPULADO);
    }
}