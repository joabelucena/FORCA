package br.com.forca.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.com.forca.lib.Printer;
import br.com.forca.lib.Util;
import br.com.forca.model.Categoria;
import br.com.forca.model.Palavra;

public class Run {

//	private static Scanner in;

	public static void main(String[] args) {

		Util util = new Util();
		
		Printer p = new Printer();

		int opcao;
		
		boolean venceu = false;
		
		boolean perdeu = false;

//		in = new Scanner(System.in);

		
		try {

			List<Categoria> categorias = new ArrayList<Categoria>(
					Util.Categorias());

			Collections.sort(categorias, Categoria.CategoriaComparator);

			p.print("Escolha uma categoria", 2, true);

			do {

				Iterator<Categoria> it = categorias.iterator();
				// Imprime opcoes
				while (it.hasNext()) {
					Categoria cat = it.next();

					if (it.hasNext()) {
						p.print(cat.getOpcao() + " - " + cat.getNome(),
								false);
					} else {
						p.print(cat.getOpcao() + " - " + cat.getNome(), true);
					}
				}
				p.printi("Escolha uma opcao: ", true);
				
				try {
					opcao = Integer.valueOf(util.read());
				} catch (Exception e) {
					opcao = -1;
				}
				
				
				

			} while (!util.opcaoValida(categorias, opcao));
			
			Categoria categoria = categorias.get(opcao);
			
			p.print("Categoria: " + categoria.getNome(), true);
			
			//Escolhe palavra aleat�ria
			Palavra palavra = categoria.getPalavra(Util.randInt(0, categoria.getPalavras().size()-1));
			
			while (!(venceu ^ perdeu)){
				
				String opc;
				
				p.printi(palavra.getPalavraJogo(), false);
				
				opc = util.read().toUpperCase();
				
				//Verifica se usuario deseja arriscar um palpite
				if(opc.equals("FORCA")){
					p.printi("Qual a Palavra: ", true, false);
					
					palavra.Tentativa(util.read().toUpperCase());
					
					if(!palavra.venceu()){
						p.print("Palavra Errada", true, true);
						continue;
					}
					
					
				}else{
					palavra.Tentativa(opc.charAt(0));
				}
				
				venceu = palavra.venceu();
				
				perdeu = palavra.perdeu();
				 
			}
			
			if(venceu){
				p.print("PARABENS VC VENCEU =) !!", true , false);
			}else{
				p.print("VC PERDEU =//", true, false);
			}
			
			p.print("Palavra Correta: " + palavra.getPalavra(), false);
			
			p.print("Seu Jogo: " + palavra.getPalavraJogo(), false);
			
			p.print("Numero de Tentativas: " + palavra.getTentativas(), true);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
