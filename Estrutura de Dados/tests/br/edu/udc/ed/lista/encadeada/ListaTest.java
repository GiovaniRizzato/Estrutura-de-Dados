package br.edu.udc.ed.lista.encadeada;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.udc.ed.lista.vetor.Vetor;

public class ListaTest {

	@Test
	public void adiciona_CasoFeliz() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i);
		}

		assertEquals(15, lista.tamanho());

		for (Integer i = 0; i < 15; i++) {
			assertEquals(i, lista.obtem(i));
		}
	}

	@Test
	public void adiciona_inicio() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i, 0);
			vetor.adiciona(i, 0);
		}

		assertEquals(15, lista.tamanho());
		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}

	@Test
	public void adiciona_meio() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i, 0);
			vetor.adiciona(i, 0);
		}

		lista.adiciona(10, 5);
		lista.adiciona(10, 5);
		lista.adiciona(10, 5);

		vetor.adiciona(10, 5);
		vetor.adiciona(10, 5);
		vetor.adiciona(10, 5);

		assertEquals(18, lista.tamanho());
		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}

	@Test
	public void adiciona_vetor() {
		ListaEncadeada<Integer> listaFor = new ListaEncadeada<>();
		ListaEncadeada<Integer> listaVetor = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 15; i++) {
			listaFor.adiciona(i);
			vetor.adiciona(i);
		}

		listaVetor.adiciona(vetor);

		assertEquals(15, listaFor.tamanho());
		assertEquals(15, listaVetor.tamanho());
		assertEquals(listaFor, listaVetor);
	}

	@Test(expected = NullPointerException.class)
	public void adiciona_null() {

		ListaEncadeada<Object> lista = new ListaEncadeada<>();
		lista.adiciona(null);

		fail("NullPointer not triggered");
	}

	// Remove
	@Test
	public void remove_CasoFeliz() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i);
			vetor.adiciona(i);
		}

		lista.remove(0);
		vetor.remove(0);
		lista.remove(0);
		vetor.remove(0);

		assertEquals(13, lista.tamanho());
		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}

	@Test
	public void remove_fim() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i);
			vetor.adiciona(i);
		}

		lista.remove(lista.tamanho());
		vetor.remove(vetor.tamanho() - 1);
		lista.remove(lista.tamanho());
		vetor.remove(vetor.tamanho() - 1);

		assertEquals(13, lista.tamanho());
		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}

	@Test
	public void remove_meio() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i);
			vetor.adiciona(i);
		}

		lista.remove(10);
		vetor.remove(10);
		lista.remove(10);
		vetor.remove(10);

		assertEquals(13, lista.tamanho());
		for (int i = 0; i < vetor.tamanho(); i++) {
			assertEquals(vetor.obtem(i), lista.obtem(i));
		}
	}

	@Test(expected = RuntimeException.class)
	public void remove_ListaVazia() {
		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		lista.remove(0);

		fail("RunTime not triggered");
	}

	// Consulta
	@Test
	public void consulta_CasoFeliz() {
		ListaEncadeada<Integer> lista = new ListaEncadeada<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i);
		}

		for (int i = 0; i < 15; i++) {
			assertEquals((Integer) i, lista.obtem(i));
		}
	}

	@Test
	public void consulta_null() {

		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		assertNull(lista.obtem(0));
	}
}
