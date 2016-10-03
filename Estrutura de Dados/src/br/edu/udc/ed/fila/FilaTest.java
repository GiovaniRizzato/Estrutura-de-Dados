package br.edu.udc.ed.fila;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.udc.ed.vetor.Vetor;

public class FilaTest {

	// CONTRUTOR - com vetor
	@Test
	public void contrutor_CasoFeliz() {

		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 15; i++) {
			vetor.adiciona(i);
		}

		Fila<Integer> fila = new Fila<>(vetor);

		assertEquals(vetor, fila.toVetor());
	}

	@Test
	public void contrutor_algunsNull() {

		Vetor<Object> vetor = new Vetor<>();

		for (int i = 0; i < 15; i++) {
			vetor.adiciona(i);
		}

		vetor.sobrepoemPosicao(null, 3);
		vetor.sobrepoemPosicao(null, 4);
		vetor.sobrepoemPosicao(null, 5);

		Fila<Object> fila = new Fila<>(vetor);

		vetor.remove(5);
		vetor.remove(4);
		vetor.remove(3);

		assertEquals(vetor, fila.toVetor());
	}

	// ADICIONA
	@Test
	public void adiciona_CasoFeli() {

		Fila<Integer> fila = new Fila<>();

		for (int i = 0; i < 15; i++) {
			fila.adiciona(i);
			assertEquals(i + 1, fila.tamanho());
		}

		assertEquals((Integer) 0, fila.consultaProximoElemento());
	}

	@Test(expected = NullPointerException.class)
	public void adiciona_null() {

		Fila<Object> fila = new Fila<>();
		fila.adiciona(null);

		fail("NullPointerExeption not triggered");
	}

	// REMOVE
	@Test
	public void remove_CasoFeliz() {

		Fila<Integer> fila = new Fila<>();

		for (int i = 0; i < 15; i++) {
			fila.adiciona(i);
		}

		for (int i = 0; i < 15; i++) {
			assertEquals((Integer) i, fila.consultaProximoElemento());
			fila.remove();
		}

		assertEquals(0, fila.tamanho());
	}

	@Test(expected = RuntimeException.class)
	public void remove_FilaVazia() {

		Fila<Integer> fila = new Fila<>();
		fila.remove();

		fail("RunTimeExeception not trigered");
	}

	// CONTEM
	@Test
	public void contem_CasoFeiz() {

		Fila<Integer> fila = new Fila<>();

		for (int i = 0; i < 15; i++) {
			fila.adiciona(i);
		}

		for (int i = 0; i < 15; i++) {
			assertTrue(fila.contem(i));
		}
	}

	@Test
	public void contem_naoEstaNaLista() {

		Fila<Integer> fila = new Fila<>();

		for (int i = 0; i < 15; i++) {
			fila.adiciona(i);
		}

		for (int i = 0; i < 15; i++) {
			assertFalse(fila.contem(i + 20));
		}
	}

	@Test
	public void contem_null() {

		Fila<Integer> fila = new Fila<>();

		for (int i = 0; i < 15; i++) {
			fila.adiciona(i);
		}

		assertFalse(fila.contem(null));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void clone_CasoFeliz() {

		Fila<Integer> fila1 = new Fila<>();

		for (int i = 0; i < 15; i++) {
			fila1.adiciona(i);
		}

		Fila<Integer> fila2 = (Fila<Integer>) fila1.clone();

		assertEquals("Por Vetor<T>", fila1.toVetor(), fila2.toVetor());
		assertEquals("Direto", fila1, fila2);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void clone_Independencia() {

		Fila<Integer> fila1 = new Fila<>();

		for (int i = 0; i < 15; i++) {
			fila1.adiciona(i);
		}

		Fila<Integer> fila2 = (Fila<Integer>) fila1.clone();

		fila2.remove();
		fila2.remove();
		fila2.remove();

		assertNotEquals("Por Vetor<T>", fila1.toVetor(), fila2.toVetor());
		assertNotEquals("Direto", fila1, fila2);
	}
}
