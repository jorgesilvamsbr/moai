package moai.dominio.consorcio;

import moai.dominio.consorcio.Consorciado;

public class ConsorciadoBuilder {

	private String nome;
	
	public ConsorciadoBuilder() {
		this.nome = "José Aparecido Ferreira";
	}

	public static ConsorciadoBuilder novo() {
		return new ConsorciadoBuilder();
	}
	
	public ConsorciadoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Consorciado criar() {
		return new Consorciado(nome);
	}
}