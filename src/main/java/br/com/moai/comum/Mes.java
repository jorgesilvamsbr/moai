package br.com.moai.comum;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public enum Mes {
	
	JANEIRO(Calendar.JANUARY, "janeiro"),
	FEVEREIRO(Calendar.FEBRUARY, "fevereiro"),
	MARCO(Calendar.MARCH, "março"),
	ABRIL(Calendar.APRIL, "abril"),
	MAIO(Calendar.MAY, "maio"),
	JUNHO(Calendar.JUNE, "junho"),
	JULHO(Calendar.JULY, "julho"),
	AGOSTO(Calendar.AUGUST, "agosto"),
	SETEMBRO(Calendar.SEPTEMBER, "setembro"),
	OUTUBRO(Calendar.OCTOBER, "outubro"),
	NOVEMBRO(Calendar.NOVEMBER, "novembro"),
	DEZEMBRO(Calendar.DECEMBER, "dezembro");
	
	private int valor;
	private String descricao;
	
	private Mes(Integer valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public Integer getValor() {
		return valor;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public static Mes obterMesPorValor(Integer valorDoMes) throws MesInvalido {
		for (Mes mes : Mes.values()) {
			if (mes.getValor() == valorDoMes) {
				return mes;
			}
		}
		throw new MesInvalido();
	}
	
	public static List<Date> obterLista() throws ParseException{
		return Arrays.asList(
				DateUtils.criarPrimeiroDiaParaO(JANEIRO),
				DateUtils.criarPrimeiroDiaParaO(FEVEREIRO),
				DateUtils.criarPrimeiroDiaParaO(MARCO),
				DateUtils.criarPrimeiroDiaParaO(ABRIL),
				DateUtils.criarPrimeiroDiaParaO(MAIO),
				DateUtils.criarPrimeiroDiaParaO(JUNHO),
				DateUtils.criarPrimeiroDiaParaO(JULHO),
				DateUtils.criarPrimeiroDiaParaO(AGOSTO),
				DateUtils.criarPrimeiroDiaParaO(SETEMBRO),
				DateUtils.criarPrimeiroDiaParaO(OUTUBRO),
				DateUtils.criarPrimeiroDiaParaO(NOVEMBRO),
				DateUtils.criarPrimeiroDiaParaO(DEZEMBRO)
				);
	}

}
