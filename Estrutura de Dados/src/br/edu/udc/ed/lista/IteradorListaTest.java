package br.edu.udc.ed.lista;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.udc.ed.iteradores.Iterador;
import br.edu.udc.ed.iteradores.IteradorManipulador;
import br.edu.udc.ed.vetor.Vetor;

public class IteradorListaTest {

	@Test
	public void navegcao_CasoFeliz() {
		Lista<Integer> lista = new Lista<>();

		for (int i = 0; i < 15; i++) {
			lista.adiciona(i);
		}

		Integer i = 0;
		for (Iterador<Integer> it = lista.inicio(); it.temProximo(); it.proximo()) {
			assertEquals(i, it.getDado());
			i++;
		}

		i = 14;
		for (Iterador<Integer> it = lista.fim(); it.temAnterior(); it.anterior()) {
			assertEquals(i, it.getDado());
			i--;
		}
	}

	@Test
	public void adicionar_CasoFeliz() {

		Lista<Integer> lista = new Lista<>();
		Vetor<Integer> vetor = new Vetor<>();

		IteradorManipulador<Integer> it = lista.inicio();
		for (int i = 0; i < 15; i++) {
			it.adicionaAntes(i);
			vetor.adiciona(i);
		}

		assertEquals(15, lista.tamanho());
		assertEquals(vetor, lista.toVetor());
	}
	
	@Test
	public void remove_CasoFeliz(){
		Lista<Integer> lista = new Lista<>();
		Vetor<Integer> vetor = new Vetor<>();

		for (int i = 0; i < 6; i++) {
			lista.adiciona(i);
			vetor.adiciona(i);
		}
		
		for (IteradorManipulador<Integer> it = lista.inicio(); it.temProximo(); it.proximo()) {
			if(it.getDado() == 6 || it.getDado() == 3 || it.getDado() == 0){
				it.remove();
			}
		}
		
		assertEquals(3, lista.tamanho());
		assertEquals(vetor, lista.toVetor());
	}
}
