package br.edu.udc.ed.lista.encadeada;

import br.edu.udc.ed.colecao.Comparavel;
import br.edu.udc.ed.lista.Ordenavel;

public class ListaOrdenavel<T extends Comparavel> extends ListaEncadeada<T> implements Ordenavel<T> {

	private void inserionSort(int ordenacao) {

		for (No<T> cursor = super.inicio; cursor != null; cursor = cursor.proximo) {
			for (No<T> procurador = cursor; procurador.anterior != null; procurador = procurador.anterior) {
				final T elemento1 = procurador.dado;
				final T elemento2 = procurador.anterior.dado;

				final int resultado = (elemento1.comparaCom(elemento2) * ordenacao);
				if (resultado < 0) {
					procurador.dado = elemento2;
					procurador.anterior.dado = elemento1;
				}//END IF resultado
			}//END FOR procurador
		}//END FOR cursor
	}

	@Override
	public void organizaCrascente() {
		this.inserionSort(1);
	}

	@Override
	public void organizaDecrascente() {
		this.inserionSort(-1);
	}
}