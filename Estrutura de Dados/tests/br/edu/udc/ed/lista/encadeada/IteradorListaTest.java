package br.edu.udc.ed.lista.encadeada;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.udc.ed.iteradores.Iterador;
import br.edu.udc.ed.iteradores.IteradorManipulador;
import br.edu.udc.ed.lista.vetor.Vetor;

public class IteradorListaTest {

	@Test
	public void navegcao_CasoFeliz() {
		ListaEncadeada<Integer> lista = new ListaEncadeada<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i);
		}

		Integer i = 0;
		for (Iterador<Integer> it = lista.inicio(); it.temProximo(); it.proximo()) {
			assertEquals(i, it.getDado());
			i++;
		}
	}

	// ADICIONAR
	@Test
	public void adicionarAntes_CasoFeliz() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 1; i < 15; i++) {
			vetor.adiciona(i);
		}
		vetor.adiciona(0);

		IteradorManipulador<Integer> it = lista.inicio();
		for (int i = 0; i < 15; i++) {
			it.adicionaAntes(i);
		}

		assertEquals(15, lista.tamanho());
	}

	@Test
	public void adicionarDepois_CasoFeliz() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		vetor.adiciona(0);
		for (int i = 1; i < 15; i++) {
			vetor.adiciona(15 - i);
		}

		IteradorManipulador<Integer> it = lista.inicio();
		for (int i = 0; i < 15; i++) {
			it.adicionaDepois(i);
		}

		assertEquals(15, lista.tamanho());
		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}

	@Test(expected = NullPointerException.class)
	public void adiciona_null() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		IteradorManipulador<Integer> it = lista.inicio();
		it.adicionaAntes(null);

		fail("NullPointer not triggered");
	}

	// REMOVER
	@Test
	public void remove_CasoFeliz_meio() {
		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 7; i++) {
			lista.adiciona(i);
			vetor.adiciona(i);
		}
		vetor.remove(3);

		for (IteradorManipulador<Integer> it = lista.inicio(); it.temProximo(); it.proximo()) {
			if (it.getDado() == 3) {
				it.remove();
				break;
			}
		}

		assertEquals(6, lista.tamanho());
		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}

	@Test
	public void remove_CasoFeliz_comeco() {
		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 7; i++) {
			lista.adiciona(i);
			vetor.adiciona(i);
		}
		vetor.remove(0);

		for (IteradorManipulador<Integer> it = lista.inicio(); it.temProximo(); it.proximo()) {
			if (it.getDado() == 0) {
				it.remove();
				break;
			}
		}

		assertEquals(6, lista.tamanho());
		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}

	@Test
	public void remove_CasoFeliz_fim() {
		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 7; i++) {
			lista.adiciona(i);
			vetor.adiciona(i);
		}
		vetor.remove(vetor.tamanho() - 1);

		for (IteradorManipulador<Integer> it = lista.inicio(); it.temProximo(); it.proximo()) {
			if (it.getDado() == 6) {
				it.remove();
				break;
			}
		}

		assertEquals(6, lista.tamanho());
		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}

	@Test(expected = RuntimeException.class)
	public void remove_listaVazia() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		IteradorManipulador<Integer> it = lista.inicio();
		it.remove();

		fail("Runtime not triggred");
	}

	@Test
	public void remove_tudo() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i);
		}

		IteradorManipulador<Integer> it = lista.inicio();
		while (true) {
			try {
				it.remove();
			} catch (RuntimeException e) {
				break;
			}
		}

		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}
}
