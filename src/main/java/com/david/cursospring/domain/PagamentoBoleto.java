package com.david.cursospring.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.david.cursospring.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataPagamento;
	
	public PagamentoBoleto() {
		
	}

	public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		// TODO Auto-generated constructor stub
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	
	
}
