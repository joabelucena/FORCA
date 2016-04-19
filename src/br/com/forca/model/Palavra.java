package br.com.forca.model;

public class Palavra {

	private static final int MAX_TENTATIVAS = 6;

	private String palavra;

	private char[] palavraJogo;

	private int tentativas = 0;

	public Palavra(String palavra) {
		super();
		this.palavra = palavra;
		this.palavraJogo = new char[palavra.length()];
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public int getTamanho() {
		return this.palavra.length();
	}
	
	public int getTentativas(){
		return this.tentativas;
	}
	
	

	public boolean podeNova() {
		if (this.tentativas + 1 < MAX_TENTATIVAS)
			return true;
		return false;
	}

	public void Tentativa() {
		this.tentativas++;
	}
	
	public boolean Tentativa(char letra) {
		return Tentativa(letra, true);
	}

	public boolean Tentativa(char letra, boolean contab) {
		
		char[] array = this.palavra.toCharArray();

		for (int i = 0; i < array.length; i++) {
			if (letra == array[i])
				this.palavraJogo[i] = letra;
		}

		if(contab) Tentativa();

		return true;

	}
	
	public void Tentativa(String tentativa){
		char[] array = tentativa.toCharArray();
		
		for (int i = 0; i < array.length; i++) {
			Tentativa(array[i], false);
		}
	}

	public String getPalavraJogo() {
		StringBuilder sb = new StringBuilder();

		String result;

		for (int i = 0; i < palavraJogo.length; i++) {
			if (palavraJogo[i] == '\0') {
				sb.append(" _");
			} else {
				sb.append(" " + palavraJogo[i]);
			}
		}

		result = sb.toString();

		return result;
	}
	
	public boolean acertouUma(){
		for (int i = 0; i < palavraJogo.length; i++) if(palavraJogo[i] != '\0') return true;
		
		return false;
	}

	public boolean venceu() {
		for (int i = 0; i < palavraJogo.length; i++) if(palavraJogo[i] == '\0') return false;
		
		return true;
	}
	
	public boolean perdeu(){
		return tentativas > MAX_TENTATIVAS;
	}

}
