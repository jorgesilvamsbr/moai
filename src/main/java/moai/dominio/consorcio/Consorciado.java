package moai.dominio.consorcio;

import javax.persistence.Entity;

@Entity
public class Consorciado {

	private String nome;

	public Consorciado(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
