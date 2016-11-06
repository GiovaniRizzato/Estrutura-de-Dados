package br.edu.udc.ed.lista.encadeada;

import br.edu.udc.ed.lista.Ordenavel;
import br.edu.udc.ed.ordenacao.Comparavel;

public class ListaOrdenavel<T extends Comparavel> extends ListaEncadeada<T> implements Ordenavel<T> {

	@Override
	public void organizaCrascente() {

		No<T> pivo = super.inicio;
		while (pivo != null) {

			No<T> menor = pivo;
			No<T> procurador = pivo.proximo;
			while (procurador != null) {

				// se controu algum valor menor que o menor
				final T dadoAtual = menor.dado;
				if (dadoAtual.menor(procurador.dado)) {
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
		while (pivo != null) {

			No<T> menor = pivo;
			No<T> procurador = pivo.proximo;
			while (procurador != null) {

				// se controu algum valor menor que o menor
				final T dadoAtual = menor.dado;
				if (dadoAtual.menor(procurador.dado)) {
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
}