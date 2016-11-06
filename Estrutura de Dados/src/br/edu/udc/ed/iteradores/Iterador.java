package br.edu.udc.ed.iteradores;

public interface Iterador<T> {

	// NAVEGAÇÃO
	public void proximo();

	public boolean temProximo();

	// MANIPULÇAO DE DADOS
	public T getDado();
}
