package br.com.moai.repositorio.consorcio;

import br.com.moai.dominio.consorcio.Consorciado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsorciadoRepository extends CrudRepository<Consorciado, Long>{
}
