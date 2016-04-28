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

	private static final String CAT_DIR = "categorias";

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
	
	public String read(){
		return String.valueOf(System.console().readPassword());
	}
	
	
}
