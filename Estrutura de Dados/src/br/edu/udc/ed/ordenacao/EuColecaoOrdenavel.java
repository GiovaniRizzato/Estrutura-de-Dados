package br.edu.udc.ed.ordenacao;

import br.edu.udc.ed.icollection.EuColecaoIteravel;

public interface EuColecaoOrdenavel<T> extends EuColecaoIteravel<T> {

	public void ordenaCrescnete();

	public void ordenaDecrescnete();

	public void randomiza();
}
