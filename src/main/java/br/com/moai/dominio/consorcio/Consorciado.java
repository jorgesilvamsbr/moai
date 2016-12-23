package br.com.moai.dominio.consorcio;

import br.com.moai.dominio.Entidade.EntidadeBase;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Consorciado extends EntidadeBase {

	@Column(length = 50)
	private String nome;

	private Consorciado() {
	}

	public Consorciado(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}