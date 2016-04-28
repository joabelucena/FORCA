package br.com.forca.lib;

public class Printer {

	private Boolean open = true;

	

	private static final int MAX_COL = 55;

	private static final int DEFAULT_ALIGN = 1;

	public void printi(String phrase, boolean footer) {
		if (getOpen()) {
			setOpen(false);
			printOpen();
		}

		printf(phrase, DEFAULT_ALIGN, false, footer, false);

	}

	public void printi(String phrase, boolean header, boolean footer) {
		if (getOpen()) {
			setOpen(false);
			printOpen();
		}

		printf(phrase, DEFAULT_ALIGN, header, footer, false);

	}

	public void print(String phrase, boolean footer) {

		if (getOpen()) {
			setOpen(false);
			printOpen();
		}

		printf(phrase, DEFAULT_ALIGN, false, footer, true);

	}

	public void print(String phrase, boolean header, boolean footer) {

		if (getOpen()) {
			setOpen(false);
			printOpen();
		}

		printf(phrase, DEFAULT_ALIGN, header, footer, true);

	}

	public void print(String phrase, int align, boolean footer) {

		if (getOpen()) {
			setOpen(false);
			printOpen();
		}

		printf(phrase, align, false, footer, true);

	}

	private void printOpen() {
		printf("Bem Vindo ao Jogo da Forca!!", 2, true, true, true);
		printf("Regras", 2, false, true, true);
		printf("1) Escolha a categoria", 1, false, false, true);
		printf("2) Tente acertar as letras", 1, false, false, true);
		printf("3) A qualquer momento digite 'forca' para", 1, false, false, true);
		printf("arriscar um palpite de palavra.", 1, false, true, true);
	}

	private void printf(String phrase, int align, boolean header, boolean footer, boolean line) {

		String printable = "";

		if (header)
			printable = new String(new char[MAX_COL]).replace("\0", "#") + "\n";

		// Tratar quebar de linha
		if (phrase.length() > (MAX_COL - 6)) {

		} else {

			int spaceQt = ((MAX_COL - 6) - phrase.length());

			switch (align) {

			case 1:

				printable += "## " + phrase + new String(new char[spaceQt]).replace("\0", " ") + " ##";

				break;

			case 2:

				printable += "## " + new String(new char[spaceQt / 2]).replace("\0", " ") + phrase
						+ new String(new char[spaceQt % 2 == 0 ? spaceQt / 2 : (spaceQt / 2) + 1]).replace("\0", " ")
						+ " ##";

				break;

			default:

				break;
			}

		}

		if (footer) {
			printable += "\n";
			printable += new String(new char[MAX_COL]).replace("\0", "#");

			if (line)
				printable += "\n";

		} else {
			if (line)
				printable += "\n";
		}

		System.out.print(printable);

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
