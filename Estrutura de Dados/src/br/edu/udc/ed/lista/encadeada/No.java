package br.edu.udc.ed.lista.encadeada;

class No<T> {
	public No<T> anterior;
	public No<T> proximo;
	public T dado;

	public No(No<T> proximo, No<T> anterior, T dado) {
		this.anterior = anterior;
		this.proximo = proximo;
		this.dado = dado;
	}

	public No(T dado) {
		this.anterior = null;
		this.proximo = null;
		this.dado = dado;
	}
}
