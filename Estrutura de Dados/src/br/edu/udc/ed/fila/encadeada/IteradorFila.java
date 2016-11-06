package br.edu.udc.ed.fila.encadeada;

import br.edu.udc.ed.fila.Fila;
import br.edu.udc.ed.iteradores.Iterador;

class IteradorFila<T> implements Iterador<T> {

	private Fila<T> referenciaFila;
	private int iteracoesPodemSerFeitas;

	public IteradorFila(Fila<T> referencia, int tamnhoFila) {
		this.referenciaFila = referencia;
		this.iteracoesPodemSerFeitas = tamnhoFila;
	}

	// Iterador.proximo faz o cursor ir para anterior para que fique mais
	// intuitivo a, porem, a abstração desta classe está ao contrario,
	// portanto foi feito desta forma para que continue abstraido de forma
	// padrão e tenha sequencia intuitiva
	@Override
	public void proximo() {
		this.referenciaFila.adiciona(this.referenciaFila.remove());
		this.iteracoesPodemSerFeitas--;
	}

	@Override
	public boolean temProximo() {
		return (this.iteracoesPodemSerFeitas > 0);
	}

	@Override
	public T getDado() {
		return this.referenciaFila.consulta();
	}
}