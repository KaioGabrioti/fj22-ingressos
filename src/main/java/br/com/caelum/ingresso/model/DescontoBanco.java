package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public class DescontoBanco implements Desconto {

	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {

		return precoOriginal.subtract(trintaPorCentoSobre(precoOriginal));
	}
	
	private	BigDecimal	trintaPorCentoSobre(BigDecimal	precoOriginal)	{
		return	precoOriginal.multiply(new	BigDecimal("0.3"));
	}
}
