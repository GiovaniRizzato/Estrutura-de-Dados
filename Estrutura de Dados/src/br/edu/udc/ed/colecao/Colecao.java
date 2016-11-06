package br.edu.udc.ed.colecao;

public interface Colecao<T> {

	public boolean contem(T object);

	public int tamanho();
}
