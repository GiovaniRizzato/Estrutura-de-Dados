package br.edu.udc.ed.cadastro;

public class Pessoa {

	private String nome;
	private char sexo;

	public Pessoa(String nome, char sexo, String documento) {
		this.nome = nome;
		this.sexo = sexo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		if (sexo == 'm' || sexo == 'f') {
			this.sexo = sexo;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Pessoa other = (Pessoa) obj;
		if (!nome.equals(other.nome))
			return false;
		if (sexo != other.sexo)
			return false;

		return true;
	}

}
