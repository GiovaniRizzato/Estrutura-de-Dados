package br.edu.udc.ed.dialogo;

import java.util.Scanner;

import br.edu.udc.ed.arvore.NodoBinarioEncadeado;

public class Dialogo {

	public static void main(String args[]) {
		Dialogo dialogo = new Dialogo();
		dialogo.execultar();
	}

	private NodoBinarioEncadeado<String> inicio;

	public Dialogo() {
		this.inicio = new NodoBinarioEncadeado<>("Olá, tudo bem?");
		this.inicio
			.adicionar("Algum motivo especial?")
				.adicionar("Que continue assim!")
					.getPai()
				.adicionar("Espero que não eseje drogado :)")
					.getPai()
				.getPai()
			.adicionar("Notas baixas?")
				.adicionar("Sei como se sente")
					.getPai()
				.adicionar("Então pare de charar >:(");
	}

	public void execultar() {

		NodoBinarioEncadeado<String> cursor = this.inicio;
		Scanner scaner = new Scanner(System.in);
		
		while (!cursor.externo()) {
			System.out.println(cursor.getElemento() + "('s'/'n'):");

			switch (scaner.nextLine()) {
			case "s":
				cursor = cursor.getEsquerdo();
				break;
			case "n":
				cursor = cursor.getDireito();
				break;
			}
		}
		System.out.println(cursor.getElemento());
		scaner.close();
	}
}
