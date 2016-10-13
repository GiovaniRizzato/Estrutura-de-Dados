package br.edu.udc.ed.mapa;
class Associacao<C, V> {

	private final C chave;
	private final V valor;

	public Associacao(C chave, V valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public C getChave() {
		return this.chave;
	}

	public V getValor() {
		return this.valor;
	}

	// Object's methods
	@Override
	public String toString() {
		return this.chave + " = " + this.valor;
	}
}
