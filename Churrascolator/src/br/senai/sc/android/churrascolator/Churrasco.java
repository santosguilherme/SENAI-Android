package br.senai.sc.android.churrascolator;

import java.io.Serializable;

public class Churrasco implements Serializable {

	private static final long serialVersionUID = -9204996280014928378L;
	public static final String KEY_BUNDLE = "churrasco";

	private Integer quantidadeCarne;
	private Integer quantidadeLinguica;
	private Integer quantidadeRefrigerante;
	private Integer numeroPessoas;

	public Churrasco() {
	}

	public Integer getQuantidadeCarne() {
		return quantidadeCarne;
	}

	public void setQuantidadeCarne(Integer quantidadeCarne) {
		this.quantidadeCarne = quantidadeCarne;
	}

	public Integer getQuantidadeLinguica() {
		return quantidadeLinguica;
	}

	public void setQuantidadeLinguica(Integer quantidadeLinguica) {
		this.quantidadeLinguica = quantidadeLinguica;
	}

	public Integer getQuantidadeRefrigerante() {
		return quantidadeRefrigerante;
	}

	public void setQuantidadeRefrigerante(Integer quantidadeRefrigerante) {
		this.quantidadeRefrigerante = quantidadeRefrigerante;
	}

	public Integer getNumeroPessoas() {
		return numeroPessoas;
	}

	public void setNumeroPessoas(Integer numeroPessoas) {
		this.numeroPessoas = numeroPessoas;
	}

	public CharSequence getResultadoCalculo() {
		setQuantidadeCarne(this.quantidadeCarne * this.numeroPessoas);
		setQuantidadeLinguica(quantidadeLinguica * this.numeroPessoas);
		setQuantidadeRefrigerante(quantidadeRefrigerante * this.numeroPessoas);

		return toString();
	}

	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append("Churrasco: \n");
		toStringBuilder.append("Quantidade de Pessoas: ");
		toStringBuilder.append(this.numeroPessoas);
		toStringBuilder.append("\n");
		toStringBuilder.append("Quantidade de Carne: ");
		toStringBuilder.append(this.quantidadeCarne);
		toStringBuilder.append("g\n");
		toStringBuilder.append("Quantidade de Linguiça: ");
		toStringBuilder.append(this.quantidadeLinguica);
		toStringBuilder.append("Un\n");
		toStringBuilder.append("Quantidade de Refrigerante: ");
		toStringBuilder.append(this.quantidadeRefrigerante);
		toStringBuilder.append("ml\n");
		return toStringBuilder.toString();
	}

}
