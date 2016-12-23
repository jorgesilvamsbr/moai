package br.com.moai.repositorio.consorcio;

import br.com.moai.dominio.consorcio.Consorcio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsorcioRepository extends CrudRepository<Consorcio, Long>{
}
