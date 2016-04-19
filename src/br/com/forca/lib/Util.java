package br.com.forca.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.forca.model.Categoria;
import br.com.forca.model.Palavra;

public class Util {

	private Boolean open = true;

	private static final String CAT_DIR = "categorias";

	private static final int MAX_COL = 55;

	private static final int DEFAULT_ALIGN = 1;

	/**
	 * Returns a pseudo-random number between min and max, inclusive. The
	 * difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min
	 *            Minimum value
	 * @param max
	 *            Maximum value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

		// NOTE: This will (intentionally) not run as written so that folks
		// copy-pasting have to think about how to initialize their
		// Random instance. Initialization of the Random instance is outside
		// the main scope of the question, but some decent options are to have
		// a field that is initialized once and then re-used as needed or to
		// use ThreadLocalRandom (if using at least Java 1.7).
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static Set<Categoria> Categorias() throws IOException {
		Set<Categoria> categorias = new HashSet<Categoria>();

		File folder = new File("./" + CAT_DIR);

		for (int i = 0; i < folder.listFiles().length; i++) {

			File file = folder.listFiles()[i];

			String linha;

			Categoria categoria = new Categoria(i, file.getName().toUpperCase()
					.replace(".TXT", ""));

			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((linha = br.readLine()) != null) {
				categoria.addPalavra(new Palavra(linha.toUpperCase()));
			}

			categorias.add(categoria);

			br.close();
		}

		return categorias;
	}
	
	public boolean opcaoValida(List<Categoria> categorias, int opcao){
		
		for (Categoria categoria : categorias) {
			if(categoria.getOpcao() == opcao) return true;
		}
		
		return false;
	}

	public void print(String phrase, boolean footer) {

		if (getOpen()) {
			setOpen(false);
			printOpen();
		}

		printf(phrase, DEFAULT_ALIGN, false, footer);

	}

	public void print(String phrase, int align, boolean footer) {

		if (getOpen()) {
			setOpen(false);
			printOpen();
		}

		printf(phrase, align, false, footer);

	}

	private void printOpen() {
		printf("Bem Vindo ao Jogo da Forca!!"				, 2, true, true);
		printf("Regras"										, 2, false, true);
		printf("1) Escolha a categoria"						, 1, false, false);
		printf("2) Tente acertar as letras"					, 1, false, false);
		printf("3) A qualquer momento digite 'forca' para"	, 1, false, false);
		printf("arriscar um palpite de palavra."			, 1, false, true);
	}

	private void printf(String phrase, int align, boolean header, boolean footer) {

		if (header)
			System.out
					.println(new String(new char[MAX_COL]).replace("\0", "#"));

		// Tratar quebar de linha
		if (phrase.length() > (MAX_COL - 6)) {

		} else {
			
			int spaceQt = ((MAX_COL - 6) - phrase.length());
			
			
			switch (align) {
			
			case 1:
				
				System.out.println("## "
						+ phrase
						+ new String(new char[spaceQt]).replace("\0", " ")
						+ " ##");

				break;

			case 2:
				
				
				System.out.println("## "
						+ new String(new char[spaceQt / 2]).replace("\0", " ")
						+ phrase
						+ new String(new char[spaceQt % 2 == 0 ? spaceQt / 2 : (spaceQt / 2)+1]).replace("\0", " ")
						+ " ##");

				break;

			default:
				break;
			}

		}

		if (footer)
			System.out
					.println(new String(new char[MAX_COL]).replace("\0", "#"));
	}

	@Deprecated
	public void clearConsole(){
		try {
			Runtime.getRuntime().exec("cls");
			
			setOpen(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Getters and Setters
	 */
	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
}
