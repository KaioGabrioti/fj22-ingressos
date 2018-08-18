package br.com.caelum.ingresso.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class GerenciadorDeSessao {
	private Sala sala;

	private	List<Sessao> sessoesDaSala;

	public	GerenciadorDeSessao(List<Sessao>	sessoesDaSala) {																
		this.sessoesDaSala	=	sessoesDaSala;
	}

	public GerenciadorDeSessao(){

	}

	public boolean cabe(Sessao nova){
		return	sessoesDaSala.stream().noneMatch(existente	->
		horarioIsConflitante(existente,	nova));	
	}
	
	private	boolean	horarioIsConflitante(Sessao	existente,	Sessao	nova) {
		
		LocalDate hoje	= LocalDate.now();
		
		LocalDateTime horarioSessaoExistente = existente.getHorario().atDate(hoje);
		LocalDateTime horarioSessaoNova	= nova.getHorario().atDate(hoje);
		
		boolean	terminaAntes = nova.getHorarioTermino().atDate(hoje).isBefore(horarioSessaoExistente);
		boolean	comecaDepois = existente.getHorarioTermino().atDate(hoje).isBefore(horarioSessaoNova);
		
		if	(terminaAntes || comecaDepois)	{
			return false;
		}
		return true;
	}
}
