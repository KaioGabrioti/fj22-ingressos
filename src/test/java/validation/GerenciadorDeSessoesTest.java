package validation;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.GerenciadorDeSessao;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessoesTest {
	
	private	Filme	rogueOne;
	private	Sala	sala3D;
	private	Sessao	sessaoDasDez;
	private	Sessao	sessaoDasTreze;
	private	Sessao	sessaoDasDezoito;
	
	@Before
	public	void	preparaSessoes(){
		this.rogueOne =	new	Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI", BigDecimal.ONE);
		this.sala3D	= new Sala("Sala 3D", BigDecimal.TEN);
		this.sessaoDasDez =	new	Sessao(LocalTime.parse("10:00:00"),	rogueOne, sala3D);
		this.sessaoDasTreze	= new Sessao(LocalTime.parse("13:00:00"), rogueOne,	sala3D);
		this.sessaoDasDezoito =	new	Sessao(LocalTime.parse("18:00:00"),	rogueOne, sala3D);
	}
	
	@Test
	public void deveAdicionarSeListaDaSessaoEstiverVazia(){
		List<Sessao>	sessoes	=	Collections.emptyList();
		GerenciadorDeSessao	gerenciador	=	new	GerenciadorDeSessao(sessoes);

		Filme filme	= new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI", BigDecimal.ONE);
		filme.setDuracao(120);
		LocalTime horario =	LocalTime.parse("10:00:00");
		Sala sala =	new	Sala("", BigDecimal.TEN);
		Sessao	sessao	=	new	Sessao(horario,	filme,	sala);
		boolean	cabe =	gerenciador.cabe(sessao);
		
		Assert.assertTrue(cabe);
	}
	@Test
	public	void	garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
		List<Sessao>	sessoes	=	Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao	gerenciador	=	new	GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessaoDasDez));
	}
	@Test
	public	void	garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente()
	{
		List<Sessao>	sessoes	=	Arrays.asList(sessaoDasDez);
		Sessao	sessao	=	new	Sessao(sessaoDasDez.getHorario().minusHours(1),	rogueOne,	sala3D);
		GerenciadorDeSessao	gerenciador	=	new	GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	@Test
	public	void	garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente()
	{
		List<Sessao>	sessoesDaSala	=	Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao	gerenciador	=	new	GerenciadorDeSessao(sessoesDaSala);
		Sessao	sessao	=	new	Sessao(sessaoDasDez.getHorario().plusHours(1),	rogueOne,	sala3D);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	@Test
	public	void	garanteQueDevePermitirUmaInsercaoEntreDoisFilmes(){
		List<Sessao>	sessoes	=	Arrays.asList(sessaoDasDez,	sessaoDasDezoito);
		GerenciadorDeSessao	gerenciador	=	new	GerenciadorDeSessao(sessoes);
		Assert.assertTrue(gerenciador.cabe(sessaoDasTreze));
	}
}
