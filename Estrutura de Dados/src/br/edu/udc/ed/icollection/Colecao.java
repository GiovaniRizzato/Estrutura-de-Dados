package br.edu.udc.ed.icollection;

public interface Colecao<T> {

	public boolean contem(T object);

	public int tamanho();
}
