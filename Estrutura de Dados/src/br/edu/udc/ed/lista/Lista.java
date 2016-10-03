package br.edu.udc.ed.lista;

import br.edu.udc.ed.iteradores.IteradorManipulador;
import br.edu.udc.ed.iteradores.Iterador;
import br.edu.udc.ed.vetor.Vetor;

public class Lista<T> {

	No<T> inicio;
	No<T> fim;

	int tamanho;

	public Lista() {
		this.inicio = this.fim = null;
		this.tamanho = 0;
	}

	public IteradorManipulador<T> inicio() {
		return new IteratorLista<>(this.inicio, this);
	}

	public IteradorManipulador<T> fim() {
		return new IteratorLista<>(this.fim, this);
	}

	public int tamanho() {
		return this.tamanho;
	}

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

	public void adiciona(T elementoAdicionado) {

		// Adiciona no fim da lista
		this.adiciona(elementoAdicionado, this.tamanho + 1);
	}

	public int adiciona(Vetor<T> grupoElementos) {

		// TODO voltar para Array simples após a implementação do toArray
		int numeroElementosAdicionados = 0;

		for (int i = 0; i < grupoElementos.tamanho(); i++) {

			try {
				this.adiciona(grupoElementos.obtem(i));

			} catch (NullPointerException e) {
				numeroElementosAdicionados--;
			}

			numeroElementosAdicionados++;
		}

		return numeroElementosAdicionados;
	}

	public void remove(int posicao) {

		if (inicio == null) {// se a lista está vazia
			throw new RuntimeException("Fila está vazia! Não há elementos para remover");
		}

		if (inicio == fim) {// se existe apenas um elemento na lista
			inicio = fim = null;
			this.tamanho--;
		}

		if (posicao <= 1) {// remover o primeiro elemento da lista
			inicio = inicio.proximo;
			inicio.anterior = null;

		} else if (posicao >= tamanho) {// remover o último elemento da lista
			fim = fim.anterior;
			fim.proximo = null;

		} else {// remover um elemento no meio da lista

			No<T> cursor = inicio;
			while (posicao > 0) {
				cursor = cursor.proximo;
				posicao--;
			}
			// remover o elemento cursor
			cursor.anterior.proximo = cursor.proximo;
			cursor.proximo.anterior = cursor.anterior;
		}
		this.tamanho--;
	}

	public void removeTodos() {

		// Irá eliminar o elemento inicio até que de
		// erro por deletar na lista vazia
		while (true) {

			try {
				this.remove(0);
			} catch (RuntimeException e) {
				break;
			}

		}
	}

	public T consulta(int pos) {

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

	public Vetor<T> toVetor() {

		// TODO mudar para Array simples(reflections)
		Vetor<T> vetor = new Vetor<>();
		No<T> cursor = this.inicio;

		while (cursor != null) {
			vetor.adiciona(cursor.dado);
			cursor = cursor.proximo;
		}

		return vetor;
	}

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
		Iterador<T> it = ((Lista<T>) obj).inicio();

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