package br.edu.udc.ed.vetor;

import java.util.Random;

public class Vetor<T> {

	@SuppressWarnings("unchecked")
	private T vetor[] = (T[]) new Object[100];
	// Inicializando um array de Object com capacidade 100.

	private int tamanho = 0;

	public void adiciona(T object) {
		this.verificaCapacidade();

		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] == null) {
				vetor[i] = object;
				break;
			}
		}

		this.vetor[tamanho] = object;
		this.tamanho++;
	}

	public void adiciona(T object, int posicao) {
		if (!this.posicaoOcupada(posicao) && posicao != this.tamanho)

			throw new IndexOutOfBoundsException("Posi��o Invalida");

		// desloca todos os vetor para a direita a partir da posicao
		for (int i = this.tamanho - 1; i >= posicao; i -= 1)
			this.vetor[i + 1] = this.vetor[i];

		this.vetor[posicao] = object;
		this.tamanho++;
	}

	public void sobrepoemPosicao(T object, int posicao) {
		// � um metodo que faz sobreosica��o do Object na posi��o solicitada
		if (!this.posicaoOcupada(posicao) && posicao != this.tamanho)

			throw new IndexOutOfBoundsException("Posi��o Invalida");

		this.vetor[posicao] = object;
	}

	public T obtem(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posi��o Invalida");
		}
		return this.vetor[posicao];
	}

	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < this.tamanho;
	}

	public void remove(int posicao) {
		if (!this.posicaoOcupada(posicao))
			throw new IndexOutOfBoundsException("Posi��o Invalida");

		for (int i = posicao; i < this.tamanho - 1; i++)
			this.vetor[i] = this.vetor[i + 1];

		this.tamanho--;
	}

	public boolean contem(T object) {
		for (int i = 0; i < this.tamanho; i++) {
			if (object.equals(this.vetor[i])) {
				return true;
			}
		}
		return false;
	}

	public int tamanho() {
		return this.tamanho;
	}

	@SuppressWarnings("unchecked")
	private void verificaCapacidade() {
		if (this.tamanho == this.vetor.length) {
			final Object[] novaArray = new Object[this.vetor.length * 2];

			for (int i = 0; i < this.vetor.length; i++) {
				novaArray[i] = this.vetor[i];
			}

			this.vetor = (T[]) novaArray;
		}
	}

	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] array = (T[]) new Object[this.tamanho];

		for (int i = 0; i < this.tamanho; i++) {
			array[i] = vetor[i];
		}

		return array;
	}

	public void shuffle() {

		final Random random = new Random();

		for (int i = 0; i < this.tamanho; i++) {
			final int posicaoTroca = Math.abs(random.nextInt() % this.tamanho);

			// Troca a posi��o "i" por uma posi��o randomica do vetor,
			// garantindo que cada elemento foi trocado ao menos uma vez.
			T bufferTroca = vetor[i];
			vetor[i] = vetor[posicaoTroca];
			vetor[posicaoTroca] = bufferTroca;
		}
	}

	public void organizaCrascente() {

		for (int i = 0; i < this.tamanho; i++) {

			int posicaoTroca = i;
			for (int j = i + 1; j < this.tamanho; j++) {
				// procura desde elemento para frente, pois, os anteriores j�
				// foram processador e s�o menores.

				if (vetor[posicaoTroca].hashCode() > vetor[j].hashCode()) {
					// procura a posicao de um elemento menor da lista de i~"fim
					// do vetor"
					posicaoTroca = j;
				}
			}

			// faz a troca dos elementos
			T bufferTroca = vetor[i];
			vetor[i] = vetor[posicaoTroca];
			vetor[posicaoTroca] = bufferTroca;
		}
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

		if (this.tamanho != (((Vetor<T>) obj).tamanho))
			return false;

		for (int i = 0; i < this.tamanho; i++) {
			// verifica se todos os elementos s�o iguais
			if (!vetor[i].equals(((Vetor<T>) obj).obtem(i))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public Vetor<T> clone() {
		Vetor<T> vetorClone = new Vetor<T>();

		for (int i = 0; i < this.tamanho; i++) {
			vetorClone.adiciona(this.vetor[i]);
		}

		return vetorClone;
	}

	@Override
	public String toString() {

		String stringAcomulador = new String();

		stringAcomulador = stringAcomulador.concat(String.format("["));

		// Acomula os numeros dentro dos conchets
		for (int i = 0; i < this.tamanho; i++) {
			stringAcomulador = stringAcomulador.concat(String.format("%s,", vetor[i].toString()));
		}

		if (stringAcomulador.endsWith(",")) {
			stringAcomulador = stringAcomulador.substring(0, stringAcomulador.length() - 1);
		}

		stringAcomulador = stringAcomulador.concat(String.format("]"));

		return stringAcomulador;
	}
}