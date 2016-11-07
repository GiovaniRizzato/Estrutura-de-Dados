package br.edu.udc.ed.lista.vetor;

import br.edu.udc.ed.iteradores.IteradorManipulador;

class IteradorVetor<T> implements IteradorManipulador<T> {

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

	@Override
	public void adicionaDepois(T elementoAdicionado) {
		final int posicaoAdicao = this.index + 1;
		this.referenciaVetor.adiciona(elementoAdicionado, posicaoAdicao);
	}

	@Override
	public void adicionaAntes(T elementoAdicionado) {
		final int posicaoAdicao = this.index - 1;
		this.referenciaVetor.adiciona(elementoAdicionado, posicaoAdicao);
	}

	@Override
	public void remove() {
		final int posicao = this.index;
		this.referenciaVetor.remove(posicao);

		if (this.index > this.referenciaVetor.tamanho()) {
			this.index = this.referenciaVetor.tamanho();
		}
	}
}
