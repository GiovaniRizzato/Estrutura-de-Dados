package br.edu.udc.ed.fila.encadeada;

import br.edu.udc.ed.colecao.ColecaoIteravel;
import br.edu.udc.ed.fila.Fila;
import br.edu.udc.ed.iteradores.Iterador;

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

public class FilaEncadeada<T> implements Fila<T> {

	private int tamanho;
	private No<T> fim;
	private No<T> inicio;

	public FilaEncadeada() {
		this.fim = this.inicio = null;
		this.tamanho = 0;
	}

	public FilaEncadeada(ColecaoIteravel<T> vetor) {

		this.fim = this.inicio = null;
		this.tamanho = 0;

		this.adiciona(vetor);
	}

	// Métodos de "Coleção"
	public int tamanho() {
		return this.tamanho;
	}

	@Override
	public boolean contem(T object) {
		No<T> procurador = this.fim;

		while (procurador.proximo != null)
			if (procurador.dado.equals(object))
				return true;

		// Percorreu toda a fila e não encontrou nenhum elemento igual
		return false;
	}

	@Override
	public Iterador<T> inicio() {
		return new IteradorFila<T>(this, this.tamanho);
	}

	// Métodos de "Fila"
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

	public void adiciona(ColecaoIteravel<T> grupoElementos) {

		final Iterador<T> it = grupoElementos.inicio();

		while (it.temProximo())
			this.adiciona(it.getDado());
	}

	public T remove() {

		No<T> noRemovido;

		if (this.tamanho == 0)// não há elementos para remover
			throw new RuntimeException("Fila está vazia! Não há elementos para remover");

		if (this.fim == this.inicio) {// tem apenas um elemento ou null
			noRemovido = this.fim;
			this.fim = this.inicio = null;

		} else {// Possui varios elementos na lista
			noRemovido = this.inicio;
			this.inicio = this.inicio.anterior;
			this.inicio.proximo = null;
		}

		this.tamanho--;
		return noRemovido.dado;
	}

	public T consulta() {

		if (this.inicio == null) {// Não há elementos na lista
			throw new NullPointerException("Lista vazia, não há elementos para retornar");

		} else {// há, ao menos, um elemento na lista
			return this.inicio.dado;
		}
	}

	// Métodos de "Object"
	@Override
	public Object clone() {
		FilaEncadeada<T> filaClone = new FilaEncadeada<>();
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
		FilaEncadeada<T> clone = (FilaEncadeada<T>) ((FilaEncadeada<T>) obj).clone();

		while (cursor != null && clone.tamanho() > 0) {
			if (cursor.dado != clone.remove()) {
				return false;
			} else {
				cursor = cursor.proximo;
			}
		}

		return true;
	}
}