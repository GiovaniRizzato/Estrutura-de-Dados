package br.edu.udc.ed.lista.vetor;

public class ConjuntoAmostral<T extends Number> extends Vetor<T> {

	private Vetor<T> produzirRol() {

		Vetor<T> rol = super.clone();
		rol.organizaCrascente();
		return rol;
	}

	public float media() {

		// TODO Fazer com que o java entenda que <T> é numérico

		float somatorio = 0;

		for (int i = 0; i < super.tamanho(); i++) {
			somatorio += (float) super.obtem(i);
		}

		return somatorio / super.tamanho();
	}

	public T mediana() {

		Vetor<T> rol = this.produzirRol();
		return rol.obtem(rol.tamanho() / 2);
	}

	public T moda() {

		Vetor<T> rol = this.produzirRol();

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
