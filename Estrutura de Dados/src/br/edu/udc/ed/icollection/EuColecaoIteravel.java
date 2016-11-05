package br.edu.udc.ed.icollection;

import br.edu.udc.ed.iteradores.Iterador;

public interface EuColecaoIteravel<T> extends EuColecao<T> {

	public Iterador<T> inicio();

	public Iterador<T> fim();
}
