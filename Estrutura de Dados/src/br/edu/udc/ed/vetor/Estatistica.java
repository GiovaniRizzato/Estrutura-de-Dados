package br.edu.udc.ed.vetor;

public class Estatistica {

	public static double media(Vetor<Integer> dadosBrutos) {

		double somatorio = 0;

		for (int i = 0; i < dadosBrutos.tamanho(); i++) {
			somatorio += (double) dadosBrutos.obtem(i);
		}

		return (float) (somatorio / dadosBrutos.tamanho());
	}

	public static Number mediana(Vetor<Integer> dadosBrutos) {

		Vetor<Integer> rol = dadosBrutos.clone();
		rol.organizaCrascente();

		return rol.obtem(rol.tamanho() / 2);
	}

	public static Number moda(Vetor<Integer> dadosBrutos) {

		Vetor<Integer> rol = dadosBrutos.clone();
		rol.organizaCrascente();

		int qtdModaAtual = 0;
		int posicaoModaAtual = 0;

		for (int i = 1; i < dadosBrutos.tamanho(); i++) {

			int qtdElementoAtual = 0;
			if (dadosBrutos.obtem(i) == dadosBrutos.obtem(i - 1)) {
				qtdElementoAtual += 1;
			}else{
				qtdElementoAtual = 0;//pois é um novo elemento a ser contado
			}
			
			if(qtdElementoAtual > qtdModaAtual){
				qtdModaAtual = qtdElementoAtual;
				posicaoModaAtual = i-1;
			}
		}

		return dadosBrutos.obtem(posicaoModaAtual);
	}
}
