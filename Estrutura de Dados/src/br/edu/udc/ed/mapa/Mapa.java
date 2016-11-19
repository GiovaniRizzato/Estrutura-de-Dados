package br.edu.udc.ed.mapa;

import br.edu.udc.ed.lista.vetor.Vetor;

public class Mapa<C, V> {

	private Vetor<Vetor<Associacao<C, V>>> tabela = new Vetor<>();
	private Integer tamanho = 0;
	private final Integer TAMANHO_MINIMO = 10;

	public Mapa() {

		for (int i = 0; i < TAMANHO_MINIMO; i++) {
			tabela.adiciona(new Vetor<Associacao<C, V>>());
		}
	}

	private void redimencionarTabela(int novaCapacidade) {
		final Vetor<Associacao<C, V>> todosObjetos = this.todas();
		this.tabela = new Vetor<>();

		for (int i = 0; i < novaCapacidade; i++) {
			this.tabela.adiciona(new Vetor<Associacao<C, V>>());
		}

		for (int i = 0; i < todosObjetos.tamanho(); i++) {
			final Associacao<C, V> associacao = todosObjetos.obtem(i);
			final int indice = this.calculaIndice(associacao.getChave());
			this.tabela.obtem(indice).adiciona(associacao);
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

	private int calculaIndice(C chave) {
		return Math.abs(chave.hashCode() % this.tabela.tamanho());
	}

	public void adiciona(C chave, V valor) {

		final int indice = this.calculaIndice(chave);
		final Vetor<Associacao<C, V>> vetor = this.tabela.obtem(indice);

		if (this.contem(chave)) {
			// procura a posicao de sobreposição
			for (int i = 0; i < vetor.tamanho(); i++) {

				final Associacao<C, V> associacao = vetor.obtem(i);
				if (associacao.getChave().equals(chave)) {

					// cria nova associação com a mesma chave e novo valor
					final Associacao<C, V> novaAssociacao = new Associacao<>(chave, valor);
					vetor.sobrepoemPosicao(novaAssociacao, i);
					return;
				}
			}
		} else {
			vetor.adiciona(new Associacao<C, V>(chave, valor));
			this.tamanho++;
			this.verificaCapacidade();
		}
	}

	public int tamanho() {
		return this.tamanho;
	}

	public boolean contem(C chave) {

		final int indice = this.calculaIndice(chave);
		final Vetor<Associacao<C, V>> vetor = this.tabela.obtem(indice);

		for (int i = 0; i < vetor.tamanho(); i++) {

			final Associacao<C, V> associacao = vetor.obtem(i);
			if (associacao.getChave().equals(chave)) {
				return true;
			}
		}

		return false;
	}

	public V obtem(C chave) {

		final int indice = this.calculaIndice(chave);
		final Vetor<Associacao<C, V>> vetor = this.tabela.obtem(indice);

		for (int i = 0; i < vetor.tamanho(); i++) {
			final Associacao<C, V> associacao = vetor.obtem(i);
			if (associacao.getChave().equals(chave)) {
				return associacao.getValor();
			}
		}

		throw new IllegalArgumentException("Não existe objeto com esta chave");
	}

	public void remove(C chave) {

		final int indice = this.calculaIndice(chave);
		final Vetor<Associacao<C, V>> vetor = this.tabela.obtem(indice);

		for (int i = 0; i < vetor.tamanho(); i++) {

			final Associacao<C, V> associacao = vetor.obtem(i);
			if (associacao.getChave().equals(chave)) {
				vetor.remove(i);
				this.tamanho--;
				this.verificaCapacidade();

				return;
			}
		}

		throw new IllegalArgumentException("Não existe objeto com esta chave");
	}

	public Vetor<Associacao<C, V>> todas() {
		final Vetor<Associacao<C, V>> todosObjetos = new Vetor<>();

		for (int i = 0; i < this.tabela.tamanho(); i++) {
			final Vetor<Associacao<C, V>> vetorNoIndice = this.tabela.obtem(i);

			for (int j = 0; j < vetorNoIndice.tamanho(); j++) {
				final Associacao<C, V> objeto = todosObjetos.obtem(j);
				todosObjetos.adiciona(objeto);
			}
		}
		return todosObjetos;
	}

	public Vetor<C> todasChaves() {
		Vetor<C> conjunto = new Vetor<>();

		for (int i = 0; i < this.tabela.tamanho(); i++) {

			final Vetor<Associacao<C, V>> vetor = this.tabela.obtem(i);
			for (int j = 0; j < vetor.tamanho(); j++) {

				final Associacao<C, V> associacao = vetor.obtem(j);
				conjunto.adiciona(associacao.getChave());
			}
		}

		return conjunto;
	}
	
	public Vetor<V> todosValores() {
		Vetor<V> conjunto = new Vetor<>();

		for (int i = 0; i < this.tabela.tamanho(); i++) {

			final Vetor<Associacao<C, V>> vetor = this.tabela.obtem(i);
			for (int j = 0; j < vetor.tamanho(); j++) {

				final Associacao<C, V> associacao = vetor.obtem(j);
				conjunto.adiciona(associacao.getValor());
			}
		}

		return conjunto;
	}
	
	@Override
	public String toString(){
		StringBuffer acomulador = new StringBuffer();

		for (int i = 0; i < this.tabela.tamanho(); i++) {

			final Vetor<Associacao<C, V>> vetor = this.tabela.obtem(i);
			for (int j = 0; j < vetor.tamanho(); j++) {

				final Associacao<C, V> associacao = vetor.obtem(j);
				acomulador.append(associacao.toString());
				acomulador.append(String.format("\n"));
			}
		}

		return acomulador.toString();
	}


	public void imprimir() {

		for (int i = 0; i < this.tabela.tamanho(); i++) {
			final Vetor<Associacao<C, V>> objetos = this.tabela.obtem(i);

			if (objetos.tamanho() == 0) {
				continue;
			}

			System.out.println("Codigo: " + i + " --- Total: " + objetos.tamanho());

			for (int j = 0; j < objetos.tamanho(); j++) {
				System.out.println(objetos.obtem(j));
			}
		}
	}
}