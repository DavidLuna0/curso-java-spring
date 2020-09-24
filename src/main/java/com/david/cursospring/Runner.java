package com.david.cursospring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.david.cursospring.domain.Categoria;
import com.david.cursospring.domain.Cidade;
import com.david.cursospring.domain.Cliente;
import com.david.cursospring.domain.Endereco;
import com.david.cursospring.domain.Estado;
import com.david.cursospring.domain.Pagamento;
import com.david.cursospring.domain.PagamentoBoleto;
import com.david.cursospring.domain.PagamentoCartao;
import com.david.cursospring.domain.Pedido;
import com.david.cursospring.domain.Produto;
import com.david.cursospring.domain.enums.EstadoPagamento;
import com.david.cursospring.domain.enums.TipoCliente;
import com.david.cursospring.repositories.CategoriaRepository;
import com.david.cursospring.repositories.CidadeRepository;
import com.david.cursospring.repositories.ClienteRepository;
import com.david.cursospring.repositories.EnderecoRepository;
import com.david.cursospring.repositories.EstadoRepository;
import com.david.cursospring.repositories.ProdutoRepository;

@Component
public class Runner implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(Runner.class);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		logger.info("Saving categorias");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		logger.info("Saving produtos");
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidade().addAll(Arrays.asList(c1));
		est2.getCidade().addAll(Arrays.asList(c2, c3));
		
		logger.info("Saving estados");
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		logger.info("Saving cidades");
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cl1 = new Cliente(null, "David", "email@email.com", "70457837779", TipoCliente.PESSOAFISICA);
		
		cl1.getTelefones().addAll(Arrays.asList("83988888888", "83999999999"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto", "Jardim", "58407878", cl1, c1);
		Endereco e2 = new Endereco(null, "Rua aqui", "21", "teste", "aqui", "58405878", cl1, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(e1, e2));
		logger.info("Saving clientes");
		clienteRepository.saveAll(Arrays.asList(cl1));
		
		logger.info("Saving enderecos");
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Pedido ped1 = new Pedido(null, sdf.parse("24/09/2020 17:37:41"), cl1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("24/09/2020 15:37:41"), cl1, e2);
		
		Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("24/09/2020 15:37:41"), null);
		ped2.setPagamento(pag2);
		
		cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		
		
	}


	
}
