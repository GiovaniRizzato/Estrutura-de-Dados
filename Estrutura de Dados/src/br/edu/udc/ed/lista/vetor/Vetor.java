package br.edu.udc.ed.lista.vetor;

import java.util.Random;

import br.edu.udc.ed.colecao.ColecaoIteravel;
import br.edu.udc.ed.iteradores.Iterador;
import br.edu.udc.ed.iteradores.IteradorManipulador;
import br.edu.udc.ed.lista.Lista;

public class Vetor<T> implements Lista<T> {

	@SuppressWarnings("unchecked")
	private T vetor[] = (T[]) new Object[100];
	// Inicializando um array de Object com capacidade 100.

	private int tamanho = 0;

	@Override
	public IteradorManipulador<T> inicio() {
		return new IteradorVetor<>(0, this);
	}

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

	public void adiciona(ColecaoIteravel<T> colecao) {

		Iterador<T> it = colecao.inicio();
		while (it.temProximo()) {
			this.adiciona(it.getDado());
			it.proximo();
		}
	}

	public void adiciona(T object, int posicao) {
		if (!this.posicaoOcupada(posicao) && posicao != this.tamanho)

			throw new IndexOutOfBoundsException("Posição Invalida");

		// desloca todos os vetor para a direita a partir da posicao
		for (int i = this.tamanho - 1; i >= posicao; i -= 1)
			this.vetor[i + 1] = this.vetor[i];

		this.vetor[posicao] = object;
		this.tamanho++;
	}

	public void sobrepoemPosicao(T object, int posicao) {
		// É um metodo que faz sobreosicação do Object na posição solicitada
		if (!this.posicaoOcupada(posicao))

			throw new IndexOutOfBoundsException("Posição Invalida");

		this.vetor[posicao] = object;
	}

	public T obtem(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição Invalida");
		}
		return this.vetor[posicao];
	}

	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < this.tamanho;
	}

	public void remove(int posicao) {
		if (!this.posicaoOcupada(posicao))
			throw new IndexOutOfBoundsException("Posição Invalida");

		for (int i = posicao; i < this.tamanho - 1; i++)
			this.vetor[i] = this.vetor[i + 1];

		this.tamanho--;
	}

	@Override
	public boolean contem(T object) {
		for (int i = 0; i < this.tamanho; i++) {
			if (object.equals(this.vetor[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
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

	public void randomiza() {

		final Random random = new Random();

		for (int i = 0; i < this.tamanho; i++) {
			final int posicaoTroca = Math.abs(random.nextInt() % this.tamanho);

			// Troca a posição "i" por uma posição randomica do vetor,
			// garantindo que cada elemento foi trocado ao menos uma vez.
			T bufferTroca = vetor[i];
			vetor[i] = vetor[posicaoTroca];
			vetor[posicaoTroca] = bufferTroca;
		}
	}

	protected void trocaPosicoes(int pos1, int pos2) {
		if (pos1 != pos2) {
			T bufferTroca = vetor[pos2];
			vetor[pos2] = vetor[pos1];
			vetor[pos1] = bufferTroca;
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
			// verifica se todos os elementos são iguais
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

		StringBuffer stringAcomulador = new StringBuffer();

		stringAcomulador.append("[");

		// Acomula os numeros dentro dos conchets
		for (int i = 0; i < this.tamanho; i++) {
			stringAcomulador.append(vetor[i].toString());
			stringAcomulador.append(",");
		}

		if (stringAcomulador.charAt(stringAcomulador.length() - 1) == ',') {
			stringAcomulador.deleteCharAt(stringAcomulador.length() - 1);
		}

		stringAcomulador.append("]");

		return stringAcomulador.toString();
	}
}