package br.edu.udc.ed.icollection;

import br.edu.udc.ed.iteradores.Iterador;

public interface EuColecao<T> {

	public boolean contem(T object);

	public int tamanho();

	public Iterador<T> inicio();

	public Iterador<T> fim();
}
