package br.com.moai.comum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtils {
	private DateUtils() {
	}

	public static Date criarDataPara(Integer dia, Mes mes, Integer ano) throws ParseException {
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.MONTH, mes.getValor());
		calendar.set(Calendar.YEAR, ano);

		return removerHoras(formatter.parse(formatter.format(calendar.getTime())));
	}

	public static Integer obterDiaDoMes(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Mes obterMesDa(Date data) throws MesInvalido {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		int valorDoMes = calendar.get(Calendar.MONTH);
		return Mes.obterMesPorValor(valorDoMes);
	}

	public static Integer obterAnoDa(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.YEAR);
	}

	public static Integer obterAnoAtual() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	public static Integer obterUltimoDiaDe(Mes mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, mes.getValor());
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Integer obterPrimeiroDiaDe(Mes mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, mes.getValor());
		return calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
	}

	public static boolean verificarSeEhAnteriorOuIgualADataAtual(Date dataParaComparar) {
		Calendar calendarioParaComparar = Calendar.getInstance();
		calendarioParaComparar.setTime(dataParaComparar);
		Calendar calendarioAtual = Calendar.getInstance();
		return calendarioParaComparar.before(calendarioAtual);
	}

	public static Date hoje() {
		Calendar calendario = Calendar.getInstance();
		return removerHoras(calendario.getTime());
	}

	public static String formatarDiaMesAno(Date data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
		return data != null ? simpleDateFormat.format(data) : "";
	}

	public static String formatarMesAno(Date data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy", new Locale("pt", "BR"));
		return data != null ? simpleDateFormat.format(data) : "";
	}

	public static String formatarMesPorExtensoEAno(Date data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM/yyyy", new Locale("pt", "BR"));
		return data != null ? simpleDateFormat.format(data).toUpperCase() : "";
	}

	public static Date incrementarMesDa(Date data) throws MesInvalido {
		Calendar calendarioAtual = Calendar.getInstance();
		calendarioAtual.setTime(data);
		calendarioAtual.add(Calendar.MONTH, 1);
		return DateUtils.removerHoras(calendarioAtual.getTime());
	}

	public static Date removerHoras(Date data) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.MINUTE, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		return calendario.getTime();
	}

	public static Date criarPrimeiroDiaParaO(Mes mes) throws ParseException {
		return removerHoras(criarDataPara(1, mes, obterAnoAtual()));
	}

	public static Date primeiroDiaPara(Mes mes, int ano) throws ParseException {
		return removerHoras(criarDataPara(1, mes, ano));
	}

	public static Date incrementarAnoDa(Date data) {
		Calendar calendarioAtual = Calendar.getInstance();
		calendarioAtual.setTime(data);
		calendarioAtual.add(Calendar.YEAR, 1);
		return DateUtils.removerHoras(calendarioAtual.getTime());
	}

	public static List<Date> obterMesesDoPeriodo(Date dataInicio, Date dataFinal) throws MesInvalido {
		List<Date> meses = new ArrayList<>();
		Date dateDeIncremento = (Date) dataInicio.clone();
		while (dateDeIncremento.compareTo(dataFinal) <= 0) {
			meses.add(dateDeIncremento);
			dateDeIncremento = DateUtils.incrementarMesDa(dateDeIncremento);
		}
		return meses;
	}

	public static long obterQtdDeDiasDaDataInformadaAteHoje(Date date) {
		long diferenca = DateUtils.hoje().getTime() - date.getTime();
		return TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
	}

	public static long obterQtdDeDiasParaChegarNaDataDo(Date date) {
		long diferenca = date.getTime() - DateUtils.hoje().getTime();
		return TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
	}

}
