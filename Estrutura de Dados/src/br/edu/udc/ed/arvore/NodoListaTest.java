package br.edu.udc.ed.arvore;

import static org.junit.Assert.*;
import org.junit.Test;

public class NodoListaTest {

	// grau()
	@Test
	public void grau_externo() {
		NodoLista<String> raiz = new NodoLista<>("raiz");

		assertEquals(raiz.grau(), 0);
	}

	@Test
	public void grau_2filhos() {
		NodoAbstrato<String> raiz = new NodoLista<>("raiz").adicionar("filho1").getPai().adicionar("filho2");

		assertEquals(raiz.grau(), 2);
	}

	// externo()
	@Test
	public void externo_true() {
		NodoLista<String> raiz = new NodoLista<>("raiz");

		assertTrue(raiz.externo());
	}

	@Test
	public void externo_false() {
		NodoLista<String> raiz = (NodoLista<String>) new NodoLista<>("raiz").adicionar("filho1").getPai().adicionar("filho2");

		assertFalse(raiz.externo());
	}

	// interno()
	@Test
	public void interno_true() {
		NodoLista<String> raiz = new NodoLista<>("raiz");

		assertFalse(raiz.externo());
	}

	@Test
	public void interno_false() {
		NodoLista<String> raiz = (NodoLista<String>) new NodoLista<>("raiz").adicionar("filho1").getPai().adicionar("filho2");

		assertTrue(raiz.externo());
	}

	// irmaoDe()
	@Test
	public void irmaoDe_CasoFeliz() {
		NodoLista<String> raiz = new NodoLista<>("raiz");
		NodoLista<String> filho1 = new NodoLista<>("filho1", raiz);
		NodoLista<String> filho2 = new NodoLista<>("filho2", raiz);

		assertTrue(filho1.irmaoDe(filho2));
	}

	@Test
	public void irmaoDe_outraArvore() {
		NodoLista<String> raiz1 = new NodoLista<>("raiz1");
		NodoLista<String> filho = new NodoLista<>("filho", raiz1);
		NodoLista<String> raiz2 = new NodoLista<>("raiz2");

		assertFalse(filho.irmaoDe(raiz2));
	}

	// raiz()
	@Test
	public void raiz_CasoFeliz() {
		NodoLista<String> raiz = new NodoLista<>("raiz");

		assertTrue(raiz.raiz());
	}

	@Test
	public void raiz_filho() {
		NodoLista<String> raiz = new NodoLista<>("raiz");
		NodoLista<String> filho = new NodoLista<>("filho", raiz);

		assertFalse(filho.raiz());
	}

	// formaArestaCom()
	@Test
	public void formaArestaCom_CasoFeliz() {
		NodoLista<String> raiz = new NodoLista<>("raiz");
		NodoLista<String> filho = new NodoLista<>("filho", raiz);

		assertTrue(raiz.formaArestaCom(filho));
	}

	@Test
	public void formaArestaCom_arvoresDiferentes() {
		NodoLista<String> a = new NodoLista<>("a");
		NodoLista<String> b = new NodoLista<>("b");

		assertFalse(a.formaArestaCom(b));
	}
	// getPai()
	// getRaiz()
	// getElemento()
	// setElemento()
}
