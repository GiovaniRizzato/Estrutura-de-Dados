package br.edu.udc.ed.arvore;

import br.edu.udc.ed.lista.vetor.Vetor;

public class NodoBinarioEncadeado<E> extends NodoBinarioAbstrato<E> {

	protected NodoBinarioEncadeado<E> esquerdo;
	protected NodoBinarioEncadeado<E> direito;
	protected int tamanhoArvore = 0;

	public NodoBinarioEncadeado(E elemento, NodoBinarioEncadeado<E> nodoPai) {
		super(elemento, nodoPai);
	}

	public NodoBinarioEncadeado(E elementoRaiz) {
		super(elementoRaiz);
		this.tamanhoArvore = 1;
	}

	@Override
	public NodoBinarioEncadeado<E> getEsquerdo() {
		return this.esquerdo;
	}

	@Override
	public NodoBinarioEncadeado<E> getDireito() {
		return this.direito;
	}

	@Override
	public int tamanhoArvore() {
		final NodoBinarioEncadeado<E> raiz = (NodoBinarioEncadeado<E>) super.getRaiz();
		return raiz.tamanhoArvore;
	}

	@Override
	public NodoBinarioEncadeado<E> adicionar(E elemento) {
		final NodoBinarioEncadeado<E> nodoFilho = new NodoBinarioEncadeado<E>(elemento, this);

		if (this.getEsquerdo() == null) {
			this.esquerdo = nodoFilho;
		} else if (this.getDireito() == null) {
			this.direito = nodoFilho;
		} else {
			throw new IllegalArgumentException("Já contém um filho esquerdo e direito");
		}

		final NodoBinarioEncadeado<E> raiz = (NodoBinarioEncadeado<E>) super.getRaiz();
		raiz.tamanhoArvore++;
		return (NodoBinarioEncadeado<E>) nodoFilho;
	}

	@Override
	public Vetor<NodoAbstrato<E>> getFilhos() {
		final Vetor<NodoAbstrato<E>> filhos = new Vetor<>();

		if (this.getEsquerdo() != null) {
			filhos.adiciona(this.getEsquerdo());
			
			if (this.getDireito() != null) {
				filhos.adiciona(this.getDireito());
			}
		}

		return filhos;
	}
}
