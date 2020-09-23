package com.david.cursospring;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.david.cursospring.domain.Categoria;
import com.david.cursospring.domain.Cidade;
import com.david.cursospring.domain.Estado;
import com.david.cursospring.domain.Produto;
import com.david.cursospring.repositories.CategoriaRepository;
import com.david.cursospring.repositories.CidadeRepository;
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
		
		
	}


	
}
