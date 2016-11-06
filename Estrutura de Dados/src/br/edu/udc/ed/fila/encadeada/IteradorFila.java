package br.edu.udc.ed.fila.encadeada;

import br.edu.udc.ed.iteradores.Iterador;

class IteradorFila<T> implements Iterador<T> {

	No<T> cursor;

	public IteradorFila(No<T> no) {
		this.cursor = no;
	}

	// Iterador.proximo faz o cursor ir para anterior para que fique mais
	// intuitivo a, porem, a abstração desta classe está ao contrario,
	// portanto foi feito desta forma para que continue abstraido de forma
	// padrão e tenha sequencia intuitiva
	@Override
	public void proximo() {
		this.cursor = this.cursor.anterior;
	}

	@Override
	public void anterior() {
		this.cursor = this.cursor.proximo;
	}

	@Override
	public boolean temProximo() {
		if (this.cursor != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean temAnterior() {
		if (this.cursor != null) {
			return true;
		}
		return false;
	}

	@Override
	public T getDado() {
		return this.cursor.dado;
	}
}