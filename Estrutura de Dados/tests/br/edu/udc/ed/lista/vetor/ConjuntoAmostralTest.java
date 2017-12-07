package br.edu.udc.ed.lista.vetor;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConjuntoAmostralTest {

	@Test
	public void variancia1() {
		ConjuntoAmostral<Integer> conjunto = new ConjuntoAmostral<>();
		conjunto.adiciona(2);
		conjunto.adiciona(8);
		conjunto.adiciona(5);
		conjunto.adiciona(6);

		assertEquals(4.6875D, conjunto.variancia(), 0.00009D);
	}

	@Test
	public void variancia2() {
		ConjuntoAmostral<Integer> conjunto = new ConjuntoAmostral<>();
		conjunto.adiciona(2, 3);
		conjunto.adiciona(3, 5);
		conjunto.adiciona(4, 8);
		conjunto.adiciona(5, 4);

		assertEquals(0.9275D, conjunto.variancia(), 0.00009D);
	}

}
