package br.edu.udc.ed.colecao;

public interface Estatistica<T extends Number> extends Colecao<T> {

	public double media();

	public T moda();

	public T mediana();
}
