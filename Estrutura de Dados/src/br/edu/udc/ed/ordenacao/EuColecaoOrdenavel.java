package br.edu.udc.ed.ordenacao;

import br.edu.udc.ed.icollection.ColecaoIteravel;

public interface EuColecaoOrdenavel<T> extends ColecaoIteravel<T> {

	public void ordenaCrescnete();

	public void ordenaDecrescnete();

	public void randomiza();
}
