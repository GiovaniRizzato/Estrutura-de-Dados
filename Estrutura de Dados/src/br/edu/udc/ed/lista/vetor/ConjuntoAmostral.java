package br.edu.udc.ed.lista.vetor;

import br.edu.udc.ed.lista.Lista;

public class ConjuntoAmostral<T extends Number> extends Vetor<T> {

	private Lista<T> produzirRol() {

		Lista<T> rol = super.clone();
		for (int pivo = 0; pivo < super.tamanho(); pivo++) {

			int posicaoTroca = pivo;
			for (int procurador = pivo + 1; procurador < super.tamanho(); procurador++) {
				// procura desde elemento para frente, pois, os anteriores j�
				// foram processador e s�o menores.

				if (super.obtem(posicaoTroca).doubleValue() < super.obtem(procurador).doubleValue()) {
					// procura a posicao de um elemento menor da lista de i~"fim
					// do vetor"
					posicaoTroca = procurador;
				}
			}

			// faz a troca dos elementos
			super.trocaPosicoes(posicaoTroca, pivo);
		}
		return rol;
	}

	public void adiciona(T valor, int frequencia) {

		for (int i = 0; i < frequencia; i++)
			super.adiciona(valor);
	}

	public double media() {

		Double somatorio = 0D;

		for (int i = 0; i < super.tamanho(); i++) {
			somatorio = somatorio + super.obtem(i).doubleValue();
		}

		return somatorio / super.tamanho();
	}

	public T mediana() {

		Lista<T> rol = this.produzirRol();
		return rol.obtem(rol.tamanho() / 2);
	}

	public T moda() {

		Lista<T> rol = this.produzirRol();

		int qtdModaAtual = 0;
		int posicaoModaAtual = 0;

		for (int i = 1; i < super.tamanho(); i++) {

			int qtdElementoAtual = 0;
			if (rol.obtem(i) == rol.obtem(i - 1)) {
				qtdElementoAtual += 1;
			} else {
				qtdElementoAtual = 0;// pois � um novo elemento a ser contado
			}

			if (qtdElementoAtual > qtdModaAtual) {
				qtdModaAtual = qtdElementoAtual;
				posicaoModaAtual = i - 1;
			}
		}

		return super.obtem(posicaoModaAtual);
	}

	public double variancia() {

		double acomulador = 0D;
		final double media = this.media();

		for (int i = 0; i < super.tamanho(); i++)
			acomulador += Math.pow(media - super.obtem(i).doubleValue(), 2);

		return acomulador / super.tamanho();
	}

	public double desvioPadrao() {
		return Math.sqrt(this.variancia());
	}

	@Override
	public ConjuntoAmostral<T> clone() {
		return (ConjuntoAmostral<T>) super.clone();
	}
}
