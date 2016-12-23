package br.com.moai.aplicacao.consorcio;

public class ConsorciadoRequest {
    private String nome;
    private Object id;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Object getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }
}