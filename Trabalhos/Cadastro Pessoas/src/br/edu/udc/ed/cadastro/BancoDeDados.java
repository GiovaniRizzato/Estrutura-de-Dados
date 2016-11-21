package br.edu.udc.ed.cadastro;

import br.edu.udc.ed.lista.vetor.Vetor;

public class BancoDeDados {

	private Vetor<Vetor<Cadastro>> tabela = new Vetor<>();
	private Integer tamanho = 0;
	private final Integer TAMANHO_MINIMO = 10;

	public BancoDeDados() {

		for (int i = 0; i < TAMANHO_MINIMO; i++) {
			tabela.adiciona(new Vetor<Cadastro>());
		}
	}

	private void redimencionarTabela(int novaCapacidade) {
		final Vetor<Cadastro> todosObjetos = this.todas();
		this.tabela = new Vetor<>();

		for (int i = 0; i < novaCapacidade; i++) {
			this.tabela.adiciona(new Vetor<Cadastro>());
		}

		for (int i = 0; i < todosObjetos.tamanho(); i++) {
			final Cadastro cadastro = todosObjetos.obtem(i);
			final int indice = this.calculaIndice(cadastro.getDocumento());
			this.tabela.obtem(indice).adiciona(cadastro);
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

	private int calculaIndice(String documento) {
		return Math.abs(documento.hashCode() % this.tabela.tamanho());
	}

	public void adiciona(String documento, Pessoa pessoa) {

		final int indice = this.calculaIndice(documento);
		final Vetor<Cadastro> vetor = this.tabela.obtem(indice);

		if (this.contem(documento)) {
			throw new IllegalArgumentException("Pessoa já cadastrada");
		} else {
			vetor.adiciona(new Cadastro(documento, pessoa));
			this.tamanho++;
			this.verificaCapacidade();
		}
	}

	public int tamanho() {
		return this.tamanho;
	}

	public boolean contem(String documento) {

		final int indice = this.calculaIndice(documento);
		final Vetor<Cadastro> vetor = this.tabela.obtem(indice);

		for (int i = 0; i < vetor.tamanho(); i++) {

			final Cadastro cadastro = vetor.obtem(i);
			if (cadastro.getDocumento().equals(documento)) {
				return true;
			}
		}

		return false;
	}

	public Pessoa obtem(String documento) {

		final int indice = this.calculaIndice(documento);
		final Vetor<Cadastro> vetor = this.tabela.obtem(indice);

		for (int i = 0; i < vetor.tamanho(); i++) {
			final Cadastro cadastro = vetor.obtem(i);
			if (cadastro.getDocumento().equals(documento)) {
				return cadastro.getPessoa();
			}
		}

		throw new IllegalArgumentException("Não existe pessoa com este documento");
	}

	public void remove(String documento) {

		final int indice = this.calculaIndice(documento);
		final Vetor<Cadastro> vetor = this.tabela.obtem(indice);

		for (int i = 0; i < vetor.tamanho(); i++) {

			final Cadastro cadastro = vetor.obtem(i);
			if (cadastro.getDocumento().equals(documento)) {
				vetor.remove(i);
				this.tamanho--;
				this.verificaCapacidade();

				return;
			}
		}

		throw new IllegalArgumentException("Não existe objeto com esta chave");
	}

	public Vetor<Cadastro> todas() {
		final Vetor<Cadastro> todosObjetos = new Vetor<>();

		for (int i = 0; i < this.tabela.tamanho(); i++) {
			final Vetor<Cadastro> vetorNoIndice = this.tabela.obtem(i);

			for (int j = 0; j < vetorNoIndice.tamanho(); j++) {
				final Cadastro cadastro = todosObjetos.obtem(j);
				todosObjetos.adiciona(cadastro);
			}
		}
		return todosObjetos;
	}

	public Vetor<String> todosDocumentos() {
		Vetor<String> conjunto = new Vetor<>();

		for (int i = 0; i < this.tabela.tamanho(); i++) {

			final Vetor<Cadastro> vetor = this.tabela.obtem(i);
			for (int j = 0; j < vetor.tamanho(); j++) {

				final Cadastro cadastro = vetor.obtem(j);
				conjunto.adiciona(cadastro.getDocumento());
			}
		}

		return conjunto;
	}

	public Vetor<Pessoa> todosValores() {
		Vetor<Pessoa> conjunto = new Vetor<>();

		for (int i = 0; i < this.tabela.tamanho(); i++) {

			final Vetor<Cadastro> vetor = this.tabela.obtem(i);
			for (int j = 0; j < vetor.tamanho(); j++) {

				final Cadastro cadastro = vetor.obtem(j);
				conjunto.adiciona(cadastro.getPessoa());
			}
		}

		return conjunto;
	}
	
	@Override
	public String toString(){
		StringBuffer acomulador = new StringBuffer();

		for (int i = 0; i < this.tabela.tamanho(); i++) {

			final Vetor<Cadastro> vetor = this.tabela.obtem(i);
			for (int j = 0; j < vetor.tamanho(); j++) {

				final Cadastro cadastro = vetor.obtem(j);
				acomulador.append(cadastro.toString());
				acomulador.append(String.format(", "));
			}
			
			acomulador.append(String.format("\n"));
		}

		return acomulador.toString();
	}
}