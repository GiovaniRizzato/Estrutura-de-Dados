package br.edu.udc.ed.iteradores;

public interface Iterador<T> {

	// NAVEGAÇÃO
	public void anterior();

	public void proximo();

	public boolean temProximo();

	public boolean temAnterior();

	// MANIPULÇAO DE DADOS
	public T getDado();
}
