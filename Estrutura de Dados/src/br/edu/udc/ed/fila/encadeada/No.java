package br.edu.udc.ed.fila.encadeada;

class No<T> {

	public T dado;
	public No<T> proximo;
	public No<T> anterior;

	public No(T data) {
		this.dado = data;
		this.proximo = this.anterior = null;
	}
}
