package br.com.forca.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Categoria {
	
	private int opcao;
	
	private String nome;
	
	private List<Palavra> palavras = new ArrayList<Palavra>();
	
	public Categoria(int opcao, String nome) {
		super();
		this.opcao = opcao;
		this.nome = nome;
	}

	public int getOpcao() {
		return opcao;
	}

	public void setOpcao(int opcao) {
		this.opcao = opcao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		nome.compareTo("");
		this.nome = nome;
	}

	public List<Palavra> getPalavras() {
		return palavras;
	}

	public void setPalavras(List<Palavra> palavras) {
		this.palavras = palavras;
	}
	
	
	/**
	 * Adiciona nova palavra
	 * @param palavra
	 */
	public void addPalavra(Palavra palavra){
		this.palavras.add(palavra);
	}
	
	public Palavra getPalavra(int index){
		return this.palavras.get(index);
	}
	
	public static Comparator<Categoria> CategoriaComparator 
    = new Comparator<Categoria>() {

		public int compare(Categoria cat1, Categoria cat2) {

		//ascending order
		return cat1.getOpcao() < cat2.getOpcao() ? -1 : cat1.getOpcao() == cat2.getOpcao() ? 0 : 1;

		}
	};
}

