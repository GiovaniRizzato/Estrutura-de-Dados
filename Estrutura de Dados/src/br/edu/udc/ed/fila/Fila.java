package br.edu.udc.ed.fila;

import br.edu.udc.ed.colecao.ColecaoIteravel;

public interface Fila<T> extends ColecaoIteravel<T>{
	
	public void adiciona(T objeto);
	public void adiciona(ColecaoIteravel<T> colecao);
	public T remove();
	public T consulta();
}
