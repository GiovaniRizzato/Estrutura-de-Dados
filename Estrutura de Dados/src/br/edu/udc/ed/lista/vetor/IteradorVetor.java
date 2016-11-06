package br.edu.udc.ed.lista.vetor;

import br.edu.udc.ed.iteradores.Iterador;

class IteradorVetor<T> implements Iterador<T> {

	private Integer index;
	private Vetor<T> referenciaVetor;

	public IteradorVetor(int index, Vetor<T> vetor) {
		this.index = index;
		this.referenciaVetor = vetor;
	}

	@Override
	public void proximo() {
		this.index++;
	}

	@Override
	public boolean temProximo() {
		if (this.referenciaVetor.tamanho() > this.index) {
			return true;
		} else
			return false;
	}

	@Override
	public T getDado() {
		return this.referenciaVetor.obtem(this.index);
	}
}
