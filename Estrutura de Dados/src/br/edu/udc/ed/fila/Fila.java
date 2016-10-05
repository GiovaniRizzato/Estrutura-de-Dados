package br.edu.udc.ed.fila;

import br.edu.udc.ed.iteradores.Iterador;

import br.edu.udc.ed.vetor.Vetor;

/**
 * ------------------------------------------------------------------------------
 * Sobre nomes de atributos:
 * 
 * Para melhor abstratir o conceito de fila, pensa-se na fila real como exemplo
 * da fila de pessoas esperando para ser atendidas em um banco:
 * 
 * O indivio que acaba de chegar, vai para o "fim" da fila e o elemento à sua
 * frente (mais proximo de ser atendido é o "proximo"; O individuo que vai ser
 * atendido (por consequancia, removido da fila), é o elemento que esta no
 * "começo" da fila seguido pelo seu "anterior".
 * 
 * Desta forma vai ser nomeado os atributos e sub-atributos desta classe.
 * ------------------------------------------------------------------------------
 * 
 * @category Estrutura de dados - concreta
 * @param Object<T>
 * @author Giovani Rizzato<giovanirizzato@gmail.com>
 */

public class Fila<T> {

	private int tamanho;
	private No<T> fim;
	private No<T> inicio;

	public Fila() {
		this.fim = this.inicio = null;
		this.tamanho = 0;
	}

	public Fila(Vetor<T> vetor) {

		this.fim = this.inicio = null;
		this.tamanho = 0;

		this.adiciona(vetor);
	}

	public Iterador<T> inicio() {
		return new IteradorFila<T>(this.inicio);
	}

	public Iterador<T> fim() {
		return new IteradorFila<T>(this.fim);
	}

	public int tamanho() {
		return this.tamanho;
	}

	public void adiciona(T adicionado) {

		if (adicionado == null)
			throw new NullPointerException();

		No<T> noAdicionado = new No<>(adicionado);

		// verifica se não existe nenhum elemento na lista
		if (this.fim == null) {
			this.fim = this.inicio = noAdicionado;

		} else {
			noAdicionado.proximo = this.fim;
			noAdicionado.proximo.anterior = noAdicionado;
			this.fim = noAdicionado;
		}

		this.tamanho++;
	}

	public int adiciona(Vetor<T> grupoElementos) {

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

	public T remove() {

		No<T> noRemovido;

		if (this.tamanho == 0)// não há elementos para remover
			throw new RuntimeException("Fila está vazia! Não há elementos para remover");
		//TODO [Professor] procurar melhor exeção para este caso

		if (this.fim == this.inicio) {// tem apenas um elemento ou null
			noRemovido = this.fim;
			this.fim = this.inicio = null;

		} else {
			noRemovido = this.inicio;
			this.inicio = this.inicio.anterior;
			this.inicio.proximo = null;
		}

		this.tamanho--;
		return noRemovido.dado;
	}

	public T consultaProximoElemento() {

		if (this.inicio == null) {// Não há elementos na lista
			throw new NullPointerException("Lista vazia, não há elementos para retornar");

		} else {// há, ao menos, um elemento na lista
			return this.inicio.dado;
		}
	}

	public boolean contem(T Object) {

		if (Object == null) {
			return false;
		}

		No<T> cursor = this.fim;

		while (cursor != null) {
			if (cursor.dado.equals(Object))
				return true;
			else
				cursor = cursor.proximo;
		}

		return false;
	}

	public Vetor<T> toVetor() {

		Vetor<T> vetor = new Vetor<>();
		No<T> cursor = this.inicio;

		while (cursor != null) {
			vetor.adiciona(cursor.dado);
			cursor = cursor.anterior;
		}

		return vetor;
	}

	@Override
	public Object clone() {
		Fila<T> filaClone = new Fila<>();
		No<T> cursor = this.inicio;
		// Para que fique na mesma ordem da lista original,
		// deve-se adicionar elementos do fim até o começo.

		for (int i = 0; i < this.tamanho; i++) {
			filaClone.adiciona(cursor.dado);
			cursor = cursor.anterior;
		}
		return filaClone;
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
		Iterador<T> it = ((Fila<T>) obj).inicio();

		while (it.temProximo()) {
			if (!(cursor.dado.equals(it.getDado()))) {
				return false;
			}

			cursor = cursor.anterior;
			it.proximo();
		}

		return true;
	}

	@Override
	public void finalize() {

		while (true) {
			try {
				this.remove();
			} catch (RuntimeException e) {
				break;
			}
		}
	}
}