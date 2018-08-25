package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public class DescontoEstudante implements Desconto {
	private BigDecimal metade = new BigDecimal(2.0);

	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {

		return precoOriginal.divide(metade);
	}
}
