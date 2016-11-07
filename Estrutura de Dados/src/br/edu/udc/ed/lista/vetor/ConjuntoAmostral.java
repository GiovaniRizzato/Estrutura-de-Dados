package br.edu.udc.ed.lista.vetor;

import br.edu.udc.ed.lista.Lista;

public class ConjuntoAmostral<T extends Integer> extends Vetor<T> {
	
	//TODO ConjuntoAmostral - fazer o java entender que "Number" é comparavel e suporte +=
	
	private Lista<T> produzirRol() {

		Lista<T> rol = super.clone();
		for (int pivo = 0; pivo < super.tamanho(); pivo++) {

			int posicaoTroca = pivo;
			for (int procurador = pivo + 1; procurador < super.tamanho(); procurador++) {
				// procura desde elemento para frente, pois, os anteriores já
				// foram processador e são menores.

				if (super.obtem(posicaoTroca) < super.obtem(procurador)) {
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

	public float media() {

		Float somatorio = 0F;

		for (int i = 0; i < super.tamanho(); i++) {
			somatorio = somatorio + super.obtem(i);
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
				qtdElementoAtual = 0;// pois é um novo elemento a ser contado
			}

			if (qtdElementoAtual > qtdModaAtual) {
				qtdModaAtual = qtdElementoAtual;
				posicaoModaAtual = i - 1;
			}
		}

		return super.obtem(posicaoModaAtual);
	}
	
	@Override
	public ConjuntoAmostral<T> clone() {
		return (ConjuntoAmostral<T>) super.clone();
	}
}
