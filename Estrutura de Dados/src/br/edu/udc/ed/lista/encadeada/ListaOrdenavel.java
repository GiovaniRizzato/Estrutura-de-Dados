package br.edu.udc.ed.lista.encadeada;

import br.edu.udc.ed.colecao.Comparavel;
import br.edu.udc.ed.lista.Ordenavel;

public class ListaOrdenavel<T extends Comparavel> extends ListaEncadeada<T> implements Ordenavel<T> {

	@Override
	public void organizaCrascente() {

		No<T> pivo = super.inicio;
		if (pivo == null) {
			return;
		}

		while (pivo.proximo != null) {

			No<T> menor = pivo;
			No<T> procurador = pivo.proximo;
			while (procurador != null) {

				// se controu algum valor menor que o menor
				final T dadoAtual = menor.dado;
				final int resultadoComparacao = dadoAtual.comparaCom(procurador.dado);
				if (resultadoComparacao < 0) {
					menor = procurador;
				}

				procurador = procurador.proximo;
			}

			this.trocaPosicoes(pivo, menor);
			pivo = pivo.proximo;
		}
	}

	@Override
	public void organizaDecrascente() {

		No<T> pivo = super.inicio;
		if (pivo == null) {
			return;
		}

		while (pivo.proximo != null) {

			No<T> menor = pivo;
			No<T> procurador = pivo.proximo;
			while (procurador != null) {

				// se controu algum valor menor que o menor
				final T dadoAtual = menor.dado;
				final int resultadoComparacao = dadoAtual.comparaCom(procurador.dado);
				if (resultadoComparacao < 0) {
					menor = procurador;
				}

				procurador = procurador.proximo;
			}

			this.trocaPosicoes(pivo, menor);
			pivo = pivo.proximo;
		}

	}

	private void trocaPosicoes(No<T> no1, No<T> no2) {
		if (no1.equals(no2)) {
			return;
		}
	}

	public static <T extends Comparavel> void inserionSoert(T[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0; j--) {
				final T elemento1 = array[j];
				final T elemento2 = array[j - 1];

				final int resultado = elemento1.comparaCom(elemento2);
				if (resultado < 0) {
					array[j] = elemento2;
					array[j - 1] = elemento1;
				}
			}
		}
	}
}