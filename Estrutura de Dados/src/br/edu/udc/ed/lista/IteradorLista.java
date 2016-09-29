package br.edu.udc.ed.lista;

import br.edu.udc.ed.iteradores.IteradorManipulador;

class IteratorLista<T> implements IteradorManipulador<T> {

	private No<T> cursor;
	private Lista<T> referenciaLista;

	public IteratorLista(No<T> no, Lista<T> lista) {
		
		this.cursor = no;
		this.referenciaLista = lista;
	}

	@Override
	public void anterior() {
		if (cursor != null) {
			this.cursor = this.cursor.anterior;
		} else {
			throw new NullPointerException("Não existe anterior para ser iterado");
			// TODO procurar uma exeção melhor para este caso
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
			throw new NullPointerException();

		if (this.cursor == null) {// lista vazia
			noAdicionado = new No<T>(elementoAdicionado);
			this.cursor = this.referenciaLista.inicio = this.referenciaLista.fim = noAdicionado;

		} else {// já existem elementos na lista

			noAdicionado = new No<>(this.cursor.proximo, this.cursor, elementoAdicionado);

			if (this.cursor.proximo != null) {
				this.cursor.proximo.anterior = noAdicionado;
			} else {
				// this.cursor esta no fim da lista, ou seja, foi criado um
				// No que deveria esta no fim, mas sem a referencia de fim
				this.referenciaLista.fim = this.cursor.proximo;// elemento recem criado
			}

			this.cursor.proximo = noAdicionado;
		}
		this.referenciaLista.tamanho++;
	}

	@Override
	public void adicionaAntes(T elementoAdicionado) {

		No<T> noAdicionado;

		if (elementoAdicionado == null)
			throw new NullPointerException();

		if (this.cursor == null) {// lista vazia
			noAdicionado = new No<>(elementoAdicionado);
			this.cursor = this.referenciaLista.inicio = this.referenciaLista.fim = noAdicionado;

		} else {// já existem elementos na lista

			noAdicionado = new No<>(this.cursor, this.cursor.anterior, elementoAdicionado);

			if (this.cursor.anterior != null) {
				this.cursor.anterior.proximo = noAdicionado;
			} else {
				// this.cursor esta no inicio da lista
				this.referenciaLista.inicio = this.cursor.anterior;// elemento recem criado
			}

			this.cursor.anterior = noAdicionado;
		}
		this.referenciaLista.tamanho++;
	}

	@Override
	public void remove() {

		if (this.cursor == null) {// lista vazia
			throw new RuntimeException("Fila está vazia! Não há elementos para remover");

		} else if (this.referenciaLista.inicio == this.referenciaLista.fim) {// existe apenas um elemento na lista
			this.referenciaLista.inicio = this.referenciaLista.fim = this.cursor = null;

		} else {// existe mais de um elemento na lista
			No<T> noRemovido = this.cursor;

			// Posiciona o cursor em outro elemento para que
			// noRemovido seja deletado
			if (noRemovido.proximo != null) {
				this.cursor = this.cursor.proximo;
			} else {
				this.cursor = this.cursor.anterior;
			}
			// Se ele for o unico elemento da lista, o cursor irá reeber
			// anterior que é null, previsto pela ideia do Iterador

			if (noRemovido.proximo != null) {
				noRemovido.proximo.anterior = noRemovido.anterior;
			} else {// elemento esta no fim da lista
				this.referenciaLista.fim = this.referenciaLista.fim.anterior;
				this.referenciaLista.fim.proximo = null;
			}

			if (noRemovido.anterior != null) {
				noRemovido.anterior.proximo = noRemovido.proximo;
			} else {// esta no inicio da lista
				this.referenciaLista.inicio = this.referenciaLista.inicio.proximo;
				this.referenciaLista.inicio.anterior = null;
			}
		}
		this.referenciaLista.tamanho--;
	}
}
