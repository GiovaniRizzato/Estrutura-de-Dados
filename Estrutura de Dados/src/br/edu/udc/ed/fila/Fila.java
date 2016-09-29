package br.edu.udc.ed.fila;

import br.edu.udc.ed.iteradores.Iterator;
import br.edu.udc.ed.vetor.Vetor;

/**
 * ------------------------------------------------------------------------------
 * Sobre nomes de atributos:
 * 
 * Para melhor abstratir o conceito de fila, pensa-se na fila real como exemplo
 * da fila de pessoas esperando para ser atendidas em um banco:
 * 
 * O indivio que acaba de chegar, vai para o "fim" da fila e o elemento � sua
 * frente (mais proximo de ser atendido � o "proximo"; O individuo que vai ser
 * atendido (por consequancia, removido da fila), � o elemento que esta no
 * "come�o" da fila seguido pelo seu "anterior".
 * 
 * Desta forma vai ser nomeado os atributos e sub-atributos desta classe.
 * ------------------------------------------------------------------------------
 * Sobre Iterator:
 * 
 * O Iterator concreto implementado nesta classe foi feito para que seja
 * execultado opera��es nos objetos contidos na estrutura sem que seja alterado
 * a estrutura em s� ,OU SEJA, nenhuma opera��o de lista como: Adiciona, Exclui
 * ser� feita pelo Iterator intencionalmente para garantir as propriedades de
 * Fila.
 * ------------------------------------------------------------------------------
 * 
 * @category Estrutura de dados - concreta
 * @param Object
 * @author Giovani Rizzato<giovanirizzato@gmail.com>
 */

public class Fila<T> {

	public Iterator<T> inicio() {
		return new IteradorFila<T>(this.inicio);
	}

	public Iterator<T> fim() {
		return new IteradorFila<T>(this.fim);
	}

	private int tamanho;
	private No<T> fim;
	private No<T> inicio;

	public int tamanho() {
		return this.tamanho;
	}

	public void adiciona(T adicionado) {

		if (adicionado == null)
			throw new NullPointerException();

		No<T> noAdicionado = new No<>(adicionado);

		// verifica se n�o existe nenhum elemento na lista
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

		// TODO voltar para Array simples ap�s a implementa��o do toArray
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

		if (this.tamanho == 0)// n�o h� elementos para remover
			throw new RuntimeException("Fila est� vazia! N�o h� elementos para remover");
		// TODO procurar uma exe��o que expresse melhor est� situa��o

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
	
	public void removeTodos() {

		// Ir� eliminar o elemento inicio at� que de
		// erro por deletar na lista vazia
		while (true) {

			try {
				this.remove();
			} catch (RuntimeException e) {
				break;
			}

		}
	}

	public T consultaProximoElemento() {

		if (this.inicio == null) {// N�o h� elementos na lista
			throw new NullPointerException("Lista vazia, n�o h� elementos para retornar");

		} else {// h�, ao menos, um elemento na lista
			return this.inicio.dado;
		}
	}

	public boolean contem(T Object) {

		if (Object == null) {
			throw new NullPointerException();
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
	public Object clone() {
		Fila<T> filaClone = new Fila<>();
		No<T> cursor = this.inicio;
		// Para que fique na mesma ordem da lista original,
		// deve-se adicionar elementos do fim at� o come�o.

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
		Iterator<T> it = ((Fila<T>) obj).inicio();

		while (it.temProximo()) {
			if (!(cursor.dado.equals(it.getDado()))) {
				return false;
			}

			cursor = cursor.anterior;
			it.proximo();
		}

		return true;
	}
}