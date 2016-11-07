package br.edu.udc.ed.lista;

import br.edu.udc.ed.colecao.Comparavel;

public interface Ordenavel<T extends Comparavel> extends Lista<T> {

	public void organizaCrascente();

	public void organizaDecrascente();
}
