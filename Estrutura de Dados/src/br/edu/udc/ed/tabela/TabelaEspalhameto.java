package br.edu.udc.ed.tabela;

import br.edu.udc.ed.iteradores.Iterador;
import br.edu.udc.ed.lista.vetor.Vetor;

public class TabelaEspalhameto<T>{
	private Vetor<Vetor<T>> tabela = new Vetor<Vetor<T>>();
	private final short TAMANHO_MINIMO = 10;
	private int tamanho = 0;

	public TabelaEspalhameto() {
		for (int i = 0; i < TAMANHO_MINIMO; i++) {
			final Vetor<T> lista = new Vetor<T>();
			this.tabela.adiciona(lista);
		}
	}

	public int tamanho() {
		return this.tamanho;
	}

	public Iterador<T> inicio() {
		return new IteradorTabela<T>(this.tabela, 0, 0);
	}

	private void redimencionarTabela(int novaCapacidade) {
		final Vetor<T> todosObjetos = this.todas();
		this.tabela = new Vetor<>();

		for (int i = 0; i < novaCapacidade; i++) {
			this.tabela.adiciona(new Vetor<T>());
		}

		for (int i = 0; i < todosObjetos.tamanho(); i++) {
			final T objeto = todosObjetos.obtem(i);
			final int indice = this.calculaIndice(objeto);
			this.tabela.obtem(indice).adiciona(objeto);
		}
	}

	private void verificaCapacidade() {
		int capacidade = this.tabela.tamanho();
		final int carga = this.tamanho / capacidade;

		if (carga >= 0.75) {
			this.redimencionarTabela(capacidade * 2);
		}
		if (carga <= 0.25) {
			final int novaCapacidade = capacidade / 2;
			if (novaCapacidade >= TAMANHO_MINIMO) {
				this.redimencionarTabela(novaCapacidade);
			}
		}
	}

	private int calculaIndice(T objeto) {
		int codigoDeEspalhamento = objeto.hashCode();
		Math.abs(codigoDeEspalhamento);

		return codigoDeEspalhamento % this.tabela.tamanho();
	}

	public void adiciona(T objeto) {
		if (!this.contem(objeto)) {
			this.verificaCapacidade();

			final int indice = this.calculaIndice(objeto);
			this.tabela.obtem(indice).adiciona(objeto);
			this.tamanho++;
		}
	}

	public void remove(T objeto) {
		if (this.contem(objeto)) {
			final int indice = this.calculaIndice(objeto);
			final Vetor<T> lista = this.tabela.obtem(indice);
			for (int i = 0; i < lista.tamanho(); i++) {
				if (lista.obtem(i).equals(objeto)) {
					lista.remove(i);
					break;

				}
			}
			this.tamanho--;
			this.verificaCapacidade();
		} else {
			throw new IllegalArgumentException("Objeto não faz parte da tabela");
		}
	}

	public boolean contem(T objeto) {
		final int indice = this.calculaIndice(objeto);
		return this.tabela.obtem(indice).contem(objeto);
	}

	public Vetor<T> todas() {
		final Vetor<T> todosObjetos = new Vetor<T>();

		for (int i = 0; i < this.tabela.tamanho(); i++) {
			final Vetor<T> vetorNoIndice = this.tabela.obtem(i);

			for (int j = 0; j < vetorNoIndice.tamanho(); j++) {
				final T objeto = todosObjetos.obtem(j);
				todosObjetos.adiciona(objeto);
			}
		}
		return todosObjetos;
	}

	@Override
	public String toString() {

		String string = String.format("Quantidade: %d\n", this.tabela.tamanho());

		for (int i = 0; i < this.tabela.tamanho(); i++) {
			string.concat(String.format("%s\n", tabela.obtem(i).toString()));
		}

		return string;
	}
}