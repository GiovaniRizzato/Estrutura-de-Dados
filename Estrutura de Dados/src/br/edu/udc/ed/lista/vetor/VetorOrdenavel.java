package br.edu.udc.ed.lista.vetor;

import br.edu.udc.ed.colecao.Comparavel;
import br.edu.udc.ed.lista.Ordenavel;

public class VetorOrdenavel<T extends Comparavel> extends Vetor<T> implements Ordenavel<T> {

	public void organizaCrascente() {

		for (int pivo = 0; pivo < super.tamanho(); pivo++) {

			int posicaoTroca = pivo;
			for (int procurador = pivo + 1; procurador < super.tamanho(); procurador++) {
				// procura desde elemento para frente, pois, os anteriores já
				// foram processador e são menores.

				final int resultadoComparacao = super.obtem(posicaoTroca).comparaCom(super.obtem(procurador));
				if (resultadoComparacao < 0) {
					// procura a posicao de um elemento menor da lista de i~"fim
					// do vetor"
					posicaoTroca = procurador;
				}
			}

			// faz a troca dos elementos
			super.trocaPosicoes(posicaoTroca, pivo);
		}
	}

	@Override
	public void organizaDecrascente() {
		for (int pivo = 0; pivo < super.tamanho(); pivo++) {

			int posicaoTroca = pivo;
			for (int procurador = pivo + 1; procurador < super.tamanho(); procurador++) {
				// procura desde elemento para frente, pois, os anteriores já
				// foram processador e são menores.

				final int resultadoComparacao = super.obtem(posicaoTroca).comparaCom(super.obtem(procurador));
				if (resultadoComparacao < 0) {
					// procura a posicao de um elemento menor da lista de i~"fim
					// do vetor"
					posicaoTroca = procurador;
				}
			}

			// faz a troca dos elementos
			super.trocaPosicoes(posicaoTroca, pivo);
		}
	}
}
