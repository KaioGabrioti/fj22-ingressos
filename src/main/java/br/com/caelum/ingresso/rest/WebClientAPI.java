package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.Detalhes;
import br.com.caelum.ingresso.model.Filme;

@Component
public class WebClientAPI {
	
	private	Logger logger =	Logger.getLogger(WebClientAPI.class);

	public Optional<Detalhes> request(Filme filme){
		RestTemplate client	= new RestTemplate();
		
		String titulo = filme.getNome().replace("	",	"+");
		String	url	= String.format("https://imdb-fj22.herokuapp.com/imdb?title=%s", titulo);
		try	{
			Detalhes detalhesDoFilme = client.getForObject(url,	Detalhes.class);
			return	Optional.of(detalhesDoFilme);
		}catch	(RestClientException e){
			logger.error(e.getMessage(), e);
			return	Optional.empty();
		}	
	}	
}
