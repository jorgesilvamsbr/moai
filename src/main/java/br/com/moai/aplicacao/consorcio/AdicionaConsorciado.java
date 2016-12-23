package br.com.moai.aplicacao.consorcio;

import br.com.moai.dominio.consorcio.Consorciado;
import br.com.moai.repositorio.consorcio.ConsorciadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Properties;

@Service
public class AdicionaConsorciado {

    @Autowired
    private ConsorciadoRepository consorciado;

    @Transactional
    public void adicionar(ConsorciadoRequest consorciadoRequest) {
        Consorciado consorciado = new Consorciado(consorciadoRequest.getNome());
        this.consorciado.save(consorciado);
        consorciadoRequest.setId(consorciado.getId());
    }
}
