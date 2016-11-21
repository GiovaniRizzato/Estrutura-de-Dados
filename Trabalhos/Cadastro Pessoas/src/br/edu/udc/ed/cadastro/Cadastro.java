package br.edu.udc.ed.cadastro;

public class Cadastro {
	private final String documento;
	private final Pessoa pessoa;

	public Cadastro(String documento, Pessoa pessoa) {
		super();
		this.documento = documento;
		this.pessoa = pessoa;
	}

	public String getDocumento() {
		return this.documento;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	@Override
	public String toString() {
		return this.getPessoa().getNome();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Cadastro other = (Cadastro) obj;
		if (!documento.equals(other.documento))
			return false;
		if (!pessoa.equals(other.pessoa))
			return false;

		return true;
	}
}
