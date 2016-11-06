package br.edu.udc.ed.lista.encadeada;

import br.edu.udc.ed.iteradores.IteradorManipulador;

class IteratorLista<T> implements IteradorManipulador<T> {

	private No<T> cursor;
	private ListaEncadeada<T> referenciaLista;

	IteratorLista(No<T> no, ListaEncadeada<T> lista) {

		this.cursor = no;
		this.referenciaLista = lista;
	}

	@Override
	public void anterior() {
		if (cursor != null) {
			this.cursor = this.cursor.anterior;
		} else {
			throw new NullPointerException("Não existe anterior para ser iterado");
		}
	}

	@Override
	public void proximo() {
		if (cursor != null) {
			this.cursor = this.cursor.proximo;
		} else {
			throw new NullPointerException("Não existe proximo para ser iterado");
		}
	}

	@Override
	public boolean temProximo() {
		if (this.cursor != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean temAnterior() {
		if (this.cursor != null) {
			return true;
		}
		return false;
	}

	@Override
	public T getDado() {
		return this.cursor.dado;
	}

	@Override
	public void adicionaDepois(T elementoAdicionado) {

		No<T> noAdicionado;

		if (elementoAdicionado == null)
			throw new NullPointerException("Não se adiciona elementos nulos na lista");

		if (this.cursor == null) {// lista vazia
			this.referenciaLista.adiciona(elementoAdicionado);
			this.cursor = this.referenciaLista.inicio;

		} else {// ao menos um elemento na lista
			noAdicionado = new No<>(this.cursor.proximo, this.cursor, elementoAdicionado);
			this.cursor.proximo = noAdicionado;

			if (noAdicionado.proximo != null) {
				noAdicionado.proximo.anterior = noAdicionado;
			} else {
				this.referenciaLista.fim = noAdicionado;
			}

			this.referenciaLista.tamanho++;
		}
	}

	@Override
	public void adicionaAntes(T elementoAdicionado) {

		No<T> noAdicionado;

		if (elementoAdicionado == null)
			throw new NullPointerException();

		if (this.cursor == null) {// lista vazia
			this.referenciaLista.adiciona(elementoAdicionado);
			this.cursor = this.referenciaLista.inicio;

		} else {// ao menos um elemento na lista

			noAdicionado = new No<>(this.cursor, this.cursor.anterior, elementoAdicionado);
			this.cursor.anterior = noAdicionado;

			if (noAdicionado.anterior != null) {
				noAdicionado.anterior.proximo = noAdicionado;
			} else {
				this.referenciaLista.inicio = noAdicionado;
			}

			this.referenciaLista.tamanho++;
		}
	}

	@Override
	public void remove() {

		if (this.cursor == null) {// lista vazia
			throw new RuntimeException("Fila está vazia! Não há elementos para remover");
		}

		if (this.referenciaLista.fim == this.referenciaLista.inicio) {
			// apenas 1 elemento
			this.referenciaLista.fim = null;
			this.referenciaLista.inicio = null;
			this.cursor = null;

		} else {// existe mais de um elemento na lista
			No<T> noRemovido = this.cursor;

			// Posiciona o cursor em outro elemento para que
			// noRemovido seja deletado
			if (noRemovido.proximo != null) {
				this.cursor = this.cursor.proximo;
			} else {
				this.cursor = this.cursor.anterior;
			}

			// removendo das referencias do "proximo"
			if (noRemovido.proximo != null) {
				noRemovido.proximo.anterior = noRemovido.anterior;
			} else {// esta no fim da lista
				this.referenciaLista.fim = noRemovido.anterior;
				// this.referenciaLista.reposicionaFim();
			}

			// removendo das referencias do "anterior"
			if (noRemovido.anterior != null) {
				noRemovido.anterior.proximo = noRemovido.proximo;
			} else {// esta no inicio da lista
				this.referenciaLista.inicio = noRemovido.proximo;
			}
		}
		this.referenciaLista.tamanho--;
	}
}
