package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public interface Desconto {

	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal);
}
