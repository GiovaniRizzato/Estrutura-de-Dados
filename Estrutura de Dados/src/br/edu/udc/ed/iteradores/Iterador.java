package br.edu.udc.ed.iteradores;

public interface Iterador<T> {

	// NAVEGA��O
	public void proximo();

	public boolean temProximo();

	// MANIPUL�AO DE DADOS
	public T getDado();
}
