package br.edu.udc.ed.lista.encadeada;

import br.edu.udc.ed.iteradores.IteradorManipulador;
import br.edu.udc.ed.lista.Lista;
import br.edu.udc.ed.colecao.ColecaoIteravel;
import br.edu.udc.ed.iteradores.Iterador;

public class ListaEncadeada<T> implements Lista<T> {

	protected No<T> inicio;
	protected No<T> fim;

	int tamanho;

	public ListaEncadeada() {
		this.inicio = this.fim = null;
		this.tamanho = 0;
	}

	// Métodos da interface "Coleção"
	@Override
	public IteradorManipulador<T> inicio() {
		return new IteratorLista<>(this.inicio, this);
	}

	@Override
	public int tamanho() {
		return this.tamanho;
	}

	// Métodos da interface "Lista"
	@Override
	public void adiciona(T elementoAdicionado, int posicao) {

		No<T> noAdicionado;

		if (elementoAdicionado == null)
			throw new NullPointerException();

		if (this.inicio == null) {// lista vazia

			noAdicionado = new No<>(elementoAdicionado);
			this.inicio = this.fim = noAdicionado;
		} else {// já existem elementos na lista

			if (posicao <= 1) {// inserir no inicio da lista

				noAdicionado = new No<>(this.inicio, null, elementoAdicionado);
				this.inicio.anterior = noAdicionado;
				this.inicio = noAdicionado;
			} else if (posicao >= tamanho) {// inserir no final da lista

				noAdicionado = new No<>(null, this.fim, elementoAdicionado);
				this.fim.proximo = noAdicionado;
				this.fim = noAdicionado;
			} else {// inserir no meio da lista

				No<T> cursor = this.inicio;
				while (posicao > 0) {
					cursor = cursor.proximo;
					posicao--;
				}
				// inserir na posição de cursor
				noAdicionado = new No<>(cursor, cursor.anterior, elementoAdicionado);
				cursor.anterior.proximo = noAdicionado;
				cursor.anterior = noAdicionado;
			}
		}
		this.tamanho++;
	}

	@Override
	public void adiciona(T elementoAdicionado) {

		// Adiciona no fim da lista
		this.adiciona(elementoAdicionado, this.tamanho + 1);
	}

	@Override
	public void adiciona(ColecaoIteravel<T> colecao) {

		Iterador<T> it = colecao.inicio();
		while (it.temProximo()) {
			this.adiciona(it.getDado());
			it.proximo();
		}
	}

	@Override
	public boolean contem(T object) {

		No<T> cursor = this.inicio;
		while (cursor != null) {
			if (cursor.dado.equals(object)) {
				return true;
			}

			cursor = cursor.proximo;
		}

		return false;
	}

	@Override
	public T obtem(int pos) {

		if (pos < 0 || pos > this.tamanho) {
			throw new IndexOutOfBoundsException("Posição invalida");
		}

		if (this.inicio == null) {
			// Se a lista está vazia
			return null;
		}

		if (this.inicio == this.fim) {
			// Se existe apenas um elemento na lista
			return this.inicio.dado;
		}

		if (pos == 0) {
			// consulta o primeiro elemento da lista
			return this.inicio.dado;
		}

		else if (pos == this.tamanho) {
			// consulta o último elemento da lista
			return this.fim.dado;
		} else {
			// consulta um elemento no meio da lista
			No<T> cursor = this.inicio;
			while (pos > 0) {
				cursor = cursor.proximo;
				pos--;
			}
			// consulta o elemento cursor
			return cursor.dado;
		}
	}

	public void remove(int posicao) {

		if (inicio == null) {// se a lista está vazia
			throw new RuntimeException("Fila está vazia! Não há elementos para remover");
		}

		if (inicio == fim) {// se existe apenas um elemento na lista
			inicio = fim = null;
			this.tamanho--;
		}
		
		posicao--;

		if (posicao <= 0) {// remover o primeiro elemento da lista
			inicio = inicio.proximo;
			inicio.anterior = null;

		} else if (posicao >= tamanho) {// remover o último elemento da lista
			fim = fim.anterior;
			fim.proximo = null;

		} else {// remover um elemento no meio da lista

			No<T> cursor = inicio;
			int posicaoCursor = posicao;
			while (posicaoCursor > 0) {
				cursor = cursor.proximo;
				posicaoCursor--;
			}
			// remover o elemento cursor
			cursor.anterior.proximo = cursor.proximo;
			cursor.proximo.anterior = cursor.anterior;
		}
		this.tamanho--;
	}
	
	// Métodos de "Object"
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {

		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		if (this == obj)
			return true;

		No<T> cursor = this.inicio;
		Iterador<T> it = ((ListaEncadeada<T>) obj).inicio();

		while (it.temProximo()) {
			if (!(cursor.dado.equals(it.getDado()))) {
				return false;
			}

			cursor = cursor.proximo;
			it.proximo();
		}

		return true;
	}
}