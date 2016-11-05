package br.edu.udc.ed.arvore;

import br.edu.udc.ed.icollection.EuColecao;
import br.edu.udc.ed.iteradores.Iterador;
import br.edu.udc.ed.vetor.Vetor;

public abstract class NodoAbstrato<E> {
	
	//Atributos
	protected NodoAbstrato<E> raiz;
	protected NodoAbstrato<E> pai;
	protected E elemento;

	protected EuColecao<NodoAbstrato<E>> filhos;

	// Métodos abstratos
	public abstract NodoAbstrato<E> adicionar(E elemento);

	public abstract int tamanhoArvore();

	public abstract EuColecao<NodoAbstrato<E>> getFilhos();
	
	//Contrutuores
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
	
	//Métodos Padrões
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
		return (NodoAbstrato<E>) this.raiz;
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
		return !this.interno();
	}

	public boolean interno() {
		return this.grau() > 0;
	}

	public boolean irmaoDe(NodoAbstrato<E> nodo) {
		return this.getPai().getFilhos().contem(nodo);
	}

	public boolean raiz() {
		return this.getRaiz().equals(this);
	}

	public int profundidade() {
		return NodoAbstrato.profundidade(this);
	}

	public static <E> int profundidade(NodoAbstrato<E> nodo) {
		if (nodo.raiz()) {
			return 0;
		}
		return 1 + NodoAbstrato.profundidade(nodo.getPai());
	}

	public int altura() {
		return NodoAbstrato.altura(this);
	}

	public static <E> int altura(NodoAbstrato<E> nodo) {
		if (nodo.externo()) {
			return 0;
		} else {

			final EuColecao<NodoAbstrato<E>> filhos = nodo.getFilhos();
			final Iterador<NodoAbstrato<E>> it = filhos.inicio();
			int maiorAltura = 0;

			while (it.temProximo()) {
				final int alturaNodo = 1 + NodoAbstrato.altura(it.getDado());
				if (alturaNodo > maiorAltura) {
					maiorAltura = alturaNodo;
				}
				it.proximo();
			}
			return maiorAltura;
		}
	}

	public boolean formaArestaCom(NodoAbstrato<E> nodoDestino) {
		return NodoAbstrato.formamAresta(this, nodoDestino);
	}

	public static <E> boolean formamAresta(NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino) {
		return nodoOrigem.getPai().equals(nodoDestino) || nodoDestino.getPai().equals(nodoOrigem);
	}

	public EuColecao<NodoAbstrato<E>> caminho(NodoAbstrato<E> nodoDestino) {
		return NodoAbstrato.caminho(this, nodoDestino);
	}

	public static <E> Vetor<NodoAbstrato<E>> caminho(NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino) {
		final Vetor<NodoAbstrato<E>> nodos = new Vetor<>();
		if (nodoOrigem.ancestralDe(nodoDestino)) {
			nodos.adiciona(nodoOrigem);
			final Iterador<NodoAbstrato<E>> it = nodoOrigem.getFilhos().inicio();
			while (it.temProximo()) {
				final NodoAbstrato<E> nodoFilho = it.getDado();
				if (nodoFilho.equals(nodoDestino)) {
					nodos.adiciona(nodoDestino);
					return nodos;// achou o destino
				} else {
					nodos.adiciona(nodoFilho.caminho(nodoDestino));
				}
				it.proximo();
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
		if (nodoOrigem.getRaiz().equals(nodoDestino)) {
			return true;
		}

		if (nodoOrigem.interno()) {
			final Iterador<NodoAbstrato<E>> it = nodoOrigem.getFilhos().inicio();
			while (it.temProximo()) {
				final NodoAbstrato<E> nodoFilho = it.getDado();
				if (nodoFilho.equals(nodoDestino) || NodoAbstrato.temAscendencia(nodoFilho, nodoDestino)) {
					return true;
				}
				it.proximo();
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

	@Override
	public String toString() {
		return NodoAbstrato.nodoToString(this);
	}

	public static <E> String nodoToString(NodoAbstrato<E> nodo) {

		StringBuffer stringAcomulador = new StringBuffer();
		stringAcomulador.append("{");
		stringAcomulador.append(nodo.getElemento().toString());

		if (!nodo.externo()) {

			final EuColecao<NodoAbstrato<E>> filhos = nodo.getFilhos();
			final Iterador<NodoAbstrato<E>> it = filhos.inicio();

			while (it.temProximo()) {
				final NodoAbstrato<E> nodoFilho = it.getDado();
				stringAcomulador.append(", ");
				stringAcomulador.append(NodoAbstrato.nodoToString(nodoFilho));

				it.proximo();
			}
		}

		stringAcomulador.append("}");
		return stringAcomulador.toString();
	}
	
	//@Overrides de Object
	@Override
	public int hashCode() {
		final int primo = 31;
		int hash = 1;
		hash = primo * hash + ((elemento == null) ? 0 : elemento.hashCode());
		hash = primo * hash + ((pai == null) ? 0 : pai.hashCode());
		return hash;
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
}
