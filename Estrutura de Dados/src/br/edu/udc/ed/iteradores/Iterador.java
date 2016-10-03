package br.edu.udc.ed.iteradores;

public interface Iterador<T> {

	// NAVEGA��O
	public void anterior();

	public void proximo();

	public boolean temProximo();

	public boolean temAnterior();

	// MANIPUL�AO DE DADOS
	public T getDado();
}
