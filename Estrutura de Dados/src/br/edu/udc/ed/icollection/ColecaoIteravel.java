package br.edu.udc.ed.icollection;

import br.edu.udc.ed.iteradores.Iterador;

public interface ColecaoIteravel<T> extends Colecao<T> {

	public Iterador<T> inicio();

	public Iterador<T> fim();
}
