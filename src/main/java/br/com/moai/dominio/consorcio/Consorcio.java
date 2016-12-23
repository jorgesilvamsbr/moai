package br.com.moai.dominio.consorcio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import br.com.moai.comum.DateUtils;
import br.com.moai.comum.Mes;
import br.com.moai.comum.MesInvalido;
import br.com.moai.dominio.Entidade.EntidadeBase;
import br.com.moai.dominio.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Consorcio extends EntidadeBase{
    @Transient
    public static final int TRINTA_E_UM = 31;
    @Transient
    public static final int ZERO = 0;

    @ManyToMany
    private List<Consorciado> consorciados;

    @ManyToMany
    private List<Consorciado> contemplados = new ArrayList<>();

    @OneToOne
    private Consorciado gerente;

    private BigDecimal valorDaParcela;

    @Temporal(TemporalType.DATE)
    private Date dataInicial;

    @Temporal(TemporalType.DATE)
    private Date dataFinal;

    private int diaDeVencimento;

    private int diaDeContemplacao;

    private Consorcio() {
    }

    public Consorcio(Consorciado gerente, BigDecimal valorDaParcela, Date dataInicial, int vencimento, int contemplacao)
            throws ExcecaoDeCampoObrigatorio, ParcelaDeveSerMaiorQueZero, DatasInvalidas, DiaInvalido, MesInvalido {
        validarCamposObrigatorios(gerente, valorDaParcela, dataInicial);
        verificaSeAParcelaEhMaiorQueZero(valorDaParcela);
        diaEhZerado(contemplacao, vencimento);
        diaMaiorQueTrintaEUm(contemplacao, vencimento);
        this.diaDeVencimento = vencimento;
        this.diaDeContemplacao = contemplacao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataInicial;
        this.valorDaParcela = valorDaParcela;
        this.gerente = gerente;
    }

    public void setConsorciados(List<Consorciado> consorciados) throws ExcecaoDeCampoObrigatorio, MesInvalido {
        validarSeAListaNaoEstaVazia(consorciados);
        atualizarOMesFinalDoConsorcio(consorciados);
        this.consorciados = consorciados;
    }

    private void atualizarOMesFinalDoConsorcio(List<Consorciado> consorciados) throws MesInvalido {
        int tamanoDaLista = consorciados.size();
        while (tamanoDaLista-- > ZERO) {
            dataFinal = DateUtils.incrementarMesDa(dataFinal);
        }
    }

    public List<Consorciado> getConsorciados() {
        return this.consorciados;
    }

    public Consorciado getGerente() {
        return gerente;
    }

    public BigDecimal getValorDaParcela() {
        return valorDaParcela;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public int getDiaDeVencimento() {
        return diaDeVencimento;
    }

    public int getDiaDeContemplacao() {
        return diaDeContemplacao;
    }

    public List<Consorciado> getContemplados() {
        return contemplados;
    }

    private void validarSeAListaNaoEstaVazia(List<Consorciado> consorciados) throws ExcecaoDeCampoObrigatorio {
        new ExcecaoDeCampoObrigatorio().quandoListaVazia(consorciados, "Lista de consorciados esta vazia")
                .entaoDispara();
    }

    private void validarCamposObrigatorios(Consorciado gerente, BigDecimal valorDaParcela, Date dataInicial)
            throws ExcecaoDeCampoObrigatorio {
        new ExcecaoDeCampoObrigatorio().quandoNulo(gerente, "Responsavel pelo consorcio não foi informado")
                .quandoNulo(valorDaParcela, "Parcela não informada")
                .quandoNulo(dataInicial, "Data inicial não informada").entaoDispara();
    }

    private void diaMaiorQueTrintaEUm(int contemplacao, int vencimento) throws DiaInvalido {
        if (contemplacao > TRINTA_E_UM || vencimento > TRINTA_E_UM) {
            throw new DiaInvalido();
        }
    }

    private void diaEhZerado(int contemplacao, int vencimento) throws DiaInvalido {
        if (contemplacao == ZERO || vencimento == ZERO) {
            throw new DiaInvalido();
        }
    }

    private void verificaSeAParcelaEhMaiorQueZero(BigDecimal valorDaParcela) throws ParcelaDeveSerMaiorQueZero {
        if (BigDecimal.ZERO.compareTo(valorDaParcela) == 0) {
            throw new ParcelaDeveSerMaiorQueZero();
        }
    }

    public Consorciado obterSorteadoDoMes() throws MesInvalido {
        return ehOPrimeiroMes() ? this.gerente : obterDaListaDeConsorciados();
    }

    private boolean ehOPrimeiroMes() throws MesInvalido {
        Mes mesDaDataDeInicioDoConsorcio = DateUtils.obterMesDa(this.dataInicial);
        Mes mesAtual = DateUtils.obterMesDa(DateUtils.hoje());
        return mesDaDataDeInicioDoConsorcio.equals(mesAtual);
    }

    private Consorciado obterDaListaDeConsorciados() {
        Consorciado consorciado = this.consorciados.get(Sorteio.sortear(this.consorciados.size()));
        this.contemplados.add(consorciado);
        this.consorciados.remove(consorciado);
        return consorciado;
    }
}