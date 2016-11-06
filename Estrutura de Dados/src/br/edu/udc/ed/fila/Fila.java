package br.edu.udc.ed.fila;

import br.edu.udc.ed.icollection.Colecao;
import br.edu.udc.ed.icollection.ColecaoIteravel;

public interface Fila<T> extends Colecao<T>{
	
	public void adiciona(T objeto);
	public void adiciona(ColecaoIteravel<T> colecao);
	public T remove();
	public T consulta();
}
