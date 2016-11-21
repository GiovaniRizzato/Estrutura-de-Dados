package br.edu.udc.ed.arvore;

public abstract class NodoBinarioAbstrato<E> extends NodoAbstrato<E> {
	
	public NodoBinarioAbstrato(E elemento, NodoBinarioAbstrato<E> nodoPai) {
		super(elemento, nodoPai);
	}

	public NodoBinarioAbstrato(E elementoRaiz) {
		super(elementoRaiz);
	}
	
	public abstract NodoBinarioAbstrato<E> getEsquerdo();
	public abstract NodoBinarioAbstrato<E> getDireito();
}
