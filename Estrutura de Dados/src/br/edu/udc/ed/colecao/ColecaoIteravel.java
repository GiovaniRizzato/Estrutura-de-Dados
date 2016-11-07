package br.edu.udc.ed.colecao;

import br.edu.udc.ed.iteradores.Iterador;

public interface ColecaoIteravel<T> extends Colecao<T>{
	
	public Iterador<T> inicio();
}
