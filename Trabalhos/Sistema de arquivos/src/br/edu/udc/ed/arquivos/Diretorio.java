package br.edu.udc.ed.arquivos;

import br.edu.udc.ed.lista.vetor.Vetor;

public class Diretorio {
	private Data dataCriacao;
	private String nome;
	private int tamanho;

	private Diretorio pai;
	private Vetor<Diretorio> filhos = new Vetor<>();

	public Diretorio(Data dataCriacao, String nome, int tamanho) {
		this.dataCriacao = dataCriacao;
		this.nome = nome;
		this.tamanho = tamanho;
		this.pai = null;
	}

	public Diretorio(Data dataCriacao, String nome, int tamanho, Diretorio pai) {
		this.dataCriacao = dataCriacao;
		this.nome = nome;

		this.pai = pai;
		this.getPai().getFilhos().adiciona(this);

		this.tamanho = 0;
		this.setTamanho(tamanho);
	}

	public Data getDataCriacao() {
		return this.dataCriacao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Diretorio getPai() {
		return this.pai;
	}

	public Vetor<Diretorio> getFilhos() {
		return this.filhos;
	}

	public int getTamanho() {
		return this.tamanho;
	}

	private void setTamanho(int novoTamanho) {
		final int incremento = novoTamanho - this.tamanho;
		this.tamanho = novoTamanho;

		this.getPai().incrementaTamanho(incremento);
	}

	private void incrementaTamanho(int incremeneto) {
		this.tamanho += incremeneto;
		if (this.getPai() != null) {
			this.getPai().incrementaTamanho(incremeneto);
		}
	}

	public void ordenaFilhos() {

		for (int i = 1; i < this.filhos.tamanho(); i++) {

			for (int k = i; k > 1 && this.filhos.obtem(k).compararCom(this.filhos.obtem(k - 1)) < 0; k--) {
				final Diretorio posterior = this.filhos.obtem(k);
				final Diretorio anterior = this.filhos.obtem(k - 1);

				this.filhos.sobrepoemPosicao(posterior, k - 1);
				this.filhos.sobrepoemPosicao(anterior, k);
			}
		}
	}

	public int compararCom(Diretorio comparado) {
		for (int i = 0; i > this.getNome().length(); i++) {

			if (i > comparado.getNome().length()) {
				// nome do comparado é menor que o nome e todos os caracteres
				// até agora são iguais
				return 1;
			}

			final char letraInterno = this.getNome().charAt(i);
			final char letraComparado = comparado.getNome().charAt(i);
			if (letraInterno != letraComparado) {
				return letraInterno - letraComparado;
			}
		}

		return 0;
	}
}
