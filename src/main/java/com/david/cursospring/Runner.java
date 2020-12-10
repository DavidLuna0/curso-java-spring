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
import com.david.cursospring.domain.ItemPedido;
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
import com.david.cursospring.repositories.ItemPedidoRepository;
import com.david.cursospring.repositories.PagamentoRepository;
import com.david.cursospring.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Casa");
		Categoria cat4 = new Categoria(null, "Jardinagem");
		Categoria cat5 = new Categoria(null, "Automoveis");
		Categoria cat6 = new Categoria(null, "Gamer");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "mesa", 200.00);
		Produto p5 = new Produto(null, "Toalha", 20.00);
		Produto p6 = new Produto(null, "Colcha", 40.00);
		Produto p7 = new Produto(null, "Cortador de Grama", 400.00);
		Produto p8 = new Produto(null, "Processador", 1200.00);
		Produto p9 = new Produto(null, "Placa de video", 2000.00);
		Produto p10 = new Produto(null, "Parabrisa", 70.00);
		Produto p11= new Produto(null, "Janela de carro", 120.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p7));
		cat5.getProdutos().addAll(Arrays.asList(p10, p11));
		cat6.getProdutos().addAll(Arrays.asList(p8, p9));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat6));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat5));
		p11.getCategorias().addAll(Arrays.asList(cat5));
		
		logger.info("Saving categorias");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));
		
		logger.info("Saving produtos");
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
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
		
		logger.info("Saving pedidos");
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		logger.info("Saving pagamentos");
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		logger.info("Saving Itens de Pedido");
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
		
	}


	
}
