package br.edu.udc.ed.arvore;

import br.edu.udc.ed.lista.vetor.Vetor;

public class NodoLista<E> extends NodoAbstrato<E> {

	protected Vetor<NodoAbstrato<E>> filhos = new Vetor<>();
	protected int tamanhoArvore = 0;

	public NodoLista(E elemento, NodoLista<E> nodoPai) {
		super(elemento, nodoPai);
	}

	public NodoLista(E elementoRaiz) {
		super(elementoRaiz);
		this.tamanhoArvore = 1;
	}

	@Override
	public int tamanhoArvore() {
		return this.tamanhoArvore;
	}

	@Override
	public NodoLista<E> adicionar(E elemento) {
		return this.adicionar(elemento, this.filhos.tamanho());
	}

	public NodoLista<E> adicionar(E elemento, int ordem) {
		final NodoLista<E> nodoFilho = new NodoLista<E>(elemento, this);
		this.filhos.adiciona(nodoFilho, ordem);

		final NodoLista<E> raiz = (NodoLista<E>) super.getRaiz();
		raiz.tamanhoArvore++;
		return (NodoLista<E>) nodoFilho;
	}

	@Override
	public Vetor<NodoAbstrato<E>> getFilhos() {
		return this.filhos;
	}
}
