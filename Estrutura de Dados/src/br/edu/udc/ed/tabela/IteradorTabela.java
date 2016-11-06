package br.edu.udc.ed.tabela;

import br.edu.udc.ed.iteradores.Iterador;
import br.edu.udc.ed.lista.vetor.Vetor;

class IteradorTabela<T> implements Iterador<T> {

	private Vetor<Vetor<T>> referenciaTabela;
	private int vetorAtual;
	private Iterador<T> iterador;
	private int indexGeral;

	public IteradorTabela(Vetor<Vetor<T>> tabela, int vetor, int index) {
		this.vetorAtual = vetor;
		this.indexGeral = index;
		this.referenciaTabela = tabela;
		this.iterador = this.referenciaTabela.obtem(vetorAtual).inicio();
	}

	@Override
	public void anterior() {
		if (!this.temAnterior()) {
			throw new IllegalAccessError("Não há anterior elemento para ser iterado");
		}

		if (iterador.temAnterior()) {
			iterador.anterior();
		} else {
			this.vetorAtual--;
			final Vetor<T> vetor = this.referenciaTabela.obtem(vetorAtual);
			while (vetor.tamanho() > 0) {
				// podem existir vetores vazios no caminho
				// tabela não balanceada
				this.vetorAtual--;
			}
			this.iterador = this.referenciaTabela.obtem(vetorAtual).inicio();
		}
	}

	@Override
	public void proximo() {
		if (!this.temProximo()) {
			throw new IllegalAccessError("Não há próximo elemento para ser iterado");
		}

		if (iterador.temProximo()) {
			iterador.proximo();
		} else {

			this.vetorAtual++;
			final Vetor<T> vetor = this.referenciaTabela.obtem(vetorAtual);
			while (vetor.tamanho() > 0) {
				// podem existir vetores vazios no caminho
				// tabela não balanceada
				this.vetorAtual++;
			}

			this.iterador = this.referenciaTabela.obtem(vetorAtual).inicio();
		}
	}

	@Override
	public boolean temProximo() {
		return this.indexGeral < this.referenciaTabela.tamanho();
	}

	@Override
	public boolean temAnterior() {
		return this.indexGeral > 0;
	}

	@Override
	public T getDado() {
		return this.iterador.getDado();
	}

}
