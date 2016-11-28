package br.edu.udc.ed.arvore;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.lista.vetor.Vetor;

public class NodoListaTeste {
	@Test
	public void testaAlturaDaArvore(){
			//Fluent Interface
			final NodoLista<String> organograma = new NodoLista<>("Fábio");
			organograma
				.adicionar("Angela")
					.adicionar("Alessandra")
						.adicionar("Luciano")
							.getPai()
								.adicionar("Rodrigo")
									.getPai()
										.adicionar("Joao")
											.adicionar("filhodoJoao")
												.getPai()
													.adicionar("miguel");
			
			Assert.assertEquals(new NodoLista<>("Fábio"), organograma);
			Assert.assertEquals(8, organograma.tamanhoArvore());
			Assert.assertEquals(4, organograma.altura());

	}
	
	@Test
	public void testaAlturaeProfundidadeDaArvorDevemRetornar0(){
			//Fluent Interface
			final NodoLista<String> organograma = new NodoLista<>("Fábio");
			
			Assert.assertEquals(new NodoLista<>("Fábio"), organograma);
			Assert.assertEquals(1, organograma.tamanhoArvore());
			Assert.assertEquals(0, organograma.altura());
			Assert.assertEquals(0, organograma.profundidade());
	}
	
	
	
	@Test
	public void verificaSeUmNodoExterno() {
		final NodoLista<String> organograma = new NodoLista<>("Fabio");
		
		organograma.adicionar("Angela 4");
		
		Assert.assertTrue(organograma.getFilhos().obtem(0).externo());
		Assert.assertFalse(organograma.getFilhos().obtem(0).interno());
	}

	@Test
	public void adicionaNaPosicaoDevePassar() {
		final NodoLista<String> organograma = new NodoLista<>("Fabio");
		organograma.adicionar("Angela 1", 0);
		organograma.adicionar("Angela 2", 1);
		organograma.adicionar("Angela 3", 2);
		organograma.adicionar("Angela 4");
		
		Assert.assertEquals("Angela 1", organograma.getFilhos().obtem(0).getElemento());
		Assert.assertEquals("Angela 2", organograma.getFilhos().obtem(1).getElemento());
		Assert.assertEquals("Angela 3", organograma.getFilhos().obtem(2).getElemento());
		Assert.assertEquals("Angela 4", organograma.getFilhos().obtem(3).getElemento());

	}
	
	@Test
	public void grauComFilhosDevePassar() {
		final NodoLista<String> arvore = new NodoLista<>("R");
		
		arvore.adicionar("Filho 1");
		arvore.adicionar("Filho 2");
		arvore.adicionar("Filho 3");
		
		Assert.assertEquals(3, arvore.grau());
	}
	@Test
	public void grausemFilhosDevePassar() {
		final NodoLista<String> arvore = new NodoLista<>("R");
		
		Assert.assertEquals(0, arvore.grau());
	}
	
	@Test
	public void irmaoDevePassar() {
		final NodoAbstrato<String> texto = new NodoLista<>("Alfabeto");
		final NodoAbstrato<String> a = texto.adicionar("A");
		final NodoAbstrato<String> a1 = a.adicionar("A1");
		final NodoAbstrato<String> a2 = a.adicionar("A3");
		final NodoAbstrato<String> a3 = a.adicionar("A2");
		final NodoAbstrato<String> b = texto.adicionar("b");
		
		Assert.assertTrue(a.irmaoDe(b));
		Assert.assertTrue(a1.irmaoDe(a2));
		Assert.assertTrue(a3.irmaoDe(a3));
		
		Assert.assertFalse(a.irmaoDe(a1));
	}
	
	
	@Test
	public void descendenteAscendenteDevePassar() {
		final NodoLista<String> alfabeto = new NodoLista<String>("Alfabeto");
		final NodoLista<String> a = alfabeto.adicionar("a");
		final NodoLista<String> a1 = a.adicionar("a1");
		final NodoLista<String> a11 = a1.adicionar("a11");
		
		final NodoLista<String> b = alfabeto.adicionar("b");
		final NodoLista<String> c = alfabeto.adicionar("c");
		
		Assert.assertTrue(alfabeto.ancestralDe(a11));
		Assert.assertTrue(a.ancestralDe(a1));
		Assert.assertTrue(a.ancestralDe(a11));
		
		Assert.assertFalse(a11.ancestralDe(a));
		Assert.assertFalse(b.ancestralDe(a1));
		
		Assert.assertTrue(a1.descendenteDe(a));
		Assert.assertTrue(b.descendenteDe(alfabeto));
		
		Assert.assertFalse(c.ancestralDe(alfabeto));
		
	}
	
	@Test
	public void caminhoSistemaDeArquivosDevePassar() {
		final NodoLista<String> c = new NodoLista<String>("C:/");
		final NodoLista<String> windows = c.adicionar("Windows/");
		final NodoLista<String> system32 = windows.adicionar("System 32/");
		system32.adicionar("temp/").getPai()
		.adicionar("drivers/").getPai()
		.adicionar("xovo/").getPai()
		.adicionar("xnxx/");
		
		final NodoLista<String> fonts = windows.adicionar("fonts");
		final Vetor<NodoAbstrato<String>> caminho = c.caminho(fonts);
		
		Assert.assertEquals(3, c.comprimento(fonts));
		Assert.assertEquals(3, caminho.tamanho());
	}

}
