package br.edu.udc.ed.arvore;

import br.edu.udc.ed.colecao.ColecaoIteravel;
import br.edu.udc.ed.iteradores.Iterador;
import br.edu.udc.ed.lista.vetor.Vetor;

public abstract class NodoAbstrato<E> {

	protected NodoAbstrato<E> raiz;
	protected NodoAbstrato<E> pai;
	protected E elemento;

	public NodoAbstrato(E elementoRaiz) {
		if (elementoRaiz == null) {
			throw new IllegalArgumentException("Uma árvore precisa de um elemento raíz.");
		}

		this.setElemento(elementoRaiz);
		this.pai = null;
		this.raiz = this;
	}

	public NodoAbstrato(E elemento, NodoAbstrato<E> nodoPai) {
		if (nodoPai == null || elemento == null) {
			throw new IllegalArgumentException("Um nodo válido precisa de um pai e um elemento.");
		}

		this.pai = nodoPai;
		this.raiz = nodoPai.getRaiz();
		this.setElemento(elemento);
	}

	public E getElemento() {
		return this.elemento;
	}

	public void setElemento(E elemento) {
		this.elemento = elemento;
	}

	public int grau() {
		return this.getFilhos() != null ? this.getFilhos().tamanho() : 0;
	}

	public boolean externo() {
		return this.grau() == 0;
	}

	public boolean interno() {
		return !this.externo();
	}

	public NodoAbstrato<E> getPai() {
		if (this.pai == null) {
			throw new IndexOutOfBoundsException("Não contém um pai. É a raíz?");
		}
		return this.pai;
	}

	public NodoAbstrato<E> getRaiz() {
		if (this.raiz == null) {
			throw new IndexOutOfBoundsException("Não existe nenhuma raiz definida.");
		}
		return this.raiz;
	}

	public boolean raiz() {
		return this.getRaiz().equals(this);
	}

	public boolean irmaoDe(NodoAbstrato<E> nodo) {
		return this.getPai().getFilhos().contem(nodo);
	}

	public int profundidade() {
		return NodoAbstrato.profundidade(this);
	}

	public static <E> int profundidade(NodoAbstrato<E> nodo) {
		// Alteração do modo para não ser recursivo
		if (nodo.raiz()) {
			return 0;
		}

		int profundidade = 0;
		NodoAbstrato<E> cursor = nodo;
		while (cursor != nodo.getRaiz()) {
			cursor = cursor.getPai();
			profundidade++;
		}

		return profundidade;
	}

	public int altura() {
		if (this.externo()) {
			return 0;
		}

		int maiorAltura = 0;
		Iterador<NodoAbstrato<E>> i = this.getFilhos().inicio();
		while (i.temProximo()) {

			// passa de filho em filho "perguntando" a altura dele
			final int alturaNo = i.getDado().altura();
			if (alturaNo > maiorAltura) {
				maiorAltura = alturaNo;
			}
			i.proximo();
		}
		return 1 + maiorAltura;
	}

	public boolean formaArestaCom(NodoAbstrato<E> nodoDestino) {
		return NodoAbstrato.formamAresta(this, nodoDestino);
	}

	public static <E> boolean formamAresta(NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino) {
		return nodoOrigem.getPai().equals(nodoDestino) || nodoDestino.getPai().equals(nodoOrigem);
	}

	public Vetor<NodoAbstrato<E>> caminho(NodoAbstrato<E> nodoDestino) {
		return NodoAbstrato.caminho(this, nodoDestino);
	}

	public static <E> Vetor<NodoAbstrato<E>> caminho(NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino) {
		final Vetor<NodoAbstrato<E>> nodos = new Vetor<>();
		if (nodoOrigem.ancestralDe(nodoDestino)) {
			nodos.adiciona(nodoOrigem);
			final Iterador<NodoAbstrato<E>> i = nodoOrigem.getFilhos().inicio();
			while (i.temProximo()) {
				final NodoAbstrato<E> nodoFilho = i.getDado();
				if (nodoFilho.equals(nodoDestino)) {
					nodos.adiciona(nodoDestino);
					return nodos;// achou o destino
				} else {
					nodos.adiciona(nodoFilho.caminho(nodoDestino));
				}
				i.proximo();
			}
		} else if (nodoOrigem.descendenteDe(nodoDestino)) {

			nodos.adiciona(nodoOrigem);
			final NodoAbstrato<E> nodoPai = nodoOrigem.getPai();

			if (nodoPai.equals(nodoDestino)) {
				nodos.adiciona(nodoDestino);
				return nodos;// achou o destino
			} else {
				nodos.adiciona(nodoPai.caminho(nodoDestino));
			}
		}

		return nodos;
	}

	public int comprimento(NodoAbstrato<E> nodoDestino) {
		return NodoAbstrato.caminho(this, nodoDestino).tamanho();
	}

	public boolean ancestralDe(NodoAbstrato<E> nodo) {
		return NodoAbstrato.temAscendencia(this, nodo);
	}

	public static <E> boolean temAscendencia(NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino) {
		if (nodoOrigem.equals(nodoDestino.getRaiz()))
			return true;
		if (nodoOrigem.interno()) {
			final Iterador<NodoAbstrato<E>> i = nodoOrigem.getFilhos().inicio();
			while (i.temProximo()) {
				final NodoAbstrato<E> nodoFilho = i.getDado();
				if (nodoFilho.equals(nodoDestino) || NodoAbstrato.temAscendencia(nodoFilho, nodoDestino)) {
					return true;
				}
				i.proximo();
			}
		}
		return false;
	}

	public boolean descendenteDe(NodoAbstrato<E> nodo) {
		return NodoAbstrato.temDescendencia(this, nodo);
	}

	public static <E> boolean temDescendencia(NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino) {
		if (nodoOrigem.raiz()) {
			return false;
		}

		final NodoAbstrato<E> nodoPai = nodoOrigem.getPai();
		if (nodoPai.equals(nodoDestino)) {
			return true;
		}

		return NodoAbstrato.temDescendencia(nodoPai, nodoDestino);
	}

	public abstract int tamanhoArvore();

	public abstract NodoAbstrato<E> adicionar(E elemento);

	public abstract ColecaoIteravel<NodoAbstrato<E>> getFilhos();

	@Override
	public String toString() {
		StringBuffer acomulador = new StringBuffer();
		acomulador.append(this.elemento.toString());

		if (this.getFilhos().tamanho() > 0) {
			acomulador.append("{ ");

			Iterador<NodoAbstrato<E>> i = this.getFilhos().inicio();
			while (i.temProximo()) {
				i.getDado().toString();
				acomulador.append(" ");
				i.proximo();
			}

			acomulador.append("}");
		}

		return acomulador.toString();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof NodoAbstrato))
			return false;

		final NodoAbstrato other = (NodoAbstrato) obj;
		if (this.elemento == null) {
			if (other.elemento != null)
				return false;
		} else if (!this.elemento.equals(other.elemento))
			return false;

		if (this.pai == null) {
			if (other.pai != null)
				return false;
		} else if (!this.pai.equals(other.pai))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int primo = 31;
		int hash = 1;
		hash = primo * hash + ((elemento == null) ? 0 : elemento.hashCode());
		hash = primo * hash + ((pai == null) ? 0 : pai.hashCode());
		return hash;
	}
}
