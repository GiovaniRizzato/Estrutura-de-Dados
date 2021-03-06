package br.edu.udc.ed.lista;

import br.edu.udc.ed.colecao.ColecaoIteravel;
import br.edu.udc.ed.iteradores.IteradorManipulador;

public interface Lista<T> extends ColecaoIteravel<T> {

	public void adiciona(T objeto);

	public void adiciona(T objeto, int posicao);

	public void adiciona(ColecaoIteravel<T> colecao);

	public T obtem(int posicao);

	public void remove(int posicao);
	
	@Override
	public IteradorManipulador<T> inicio();
}
