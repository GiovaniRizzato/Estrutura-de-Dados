package br.edu.udc.ed.arvore;

import br.edu.udc.ed.icollection.EuColecao;

public class NodoLista<E> extends NodoAbstrato<E> {
	
	public NodoLista(E elementoRaiz) {
		super(elementoRaiz);
	}
	
	public NodoLista(E elemento, NodoAbstrato<E> nodoPai) {
		super(elemento, nodoPai);
	}

	@Override
	public NodoAbstrato<E> adicionar(E elemento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int tamanhoArvore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EuColecao<NodoAbstrato<E>> getFilhos() {
		// TODO Auto-generated method stub
		return null;
	}
}
