package br.edu.udc.ed.iteradores;

public interface IteradorManipulador<T> extends Iterator<T> {

	// MODIFICA��O DA ESTRUTURA DE DADOS
	public void adicionaDepois(T elementoAdicionado);

	public void adicionaAntes(T elementoAdicionado);

	public void remove();
}
