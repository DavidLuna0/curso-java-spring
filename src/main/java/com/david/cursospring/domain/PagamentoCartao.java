package com.david.cursospring.domain;

import javax.persistence.Entity;

import com.david.cursospring.domain.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
	
	public PagamentoCartao() {
		
	}

	public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
		// TODO Auto-generated constructor stub
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
	
	
}
