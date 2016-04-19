package br.com.forca.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import br.com.forca.lib.Util;
import br.com.forca.model.Categoria;
import br.com.forca.model.Palavra;

public class Run {

	private static Scanner in;

	public static void main(String[] args) {

		Util util = new Util();

		int opcao;
		
		boolean venceu = false;
		
		boolean perdeu = false;

		in = new Scanner(System.in);

		
		try {

			List<Categoria> categorias = new ArrayList<Categoria>(
					Util.Categorias());

			Collections.sort(categorias, Categoria.CategoriaComparator);

			util.print("Escolha uma categoria", 2, true);

			do {

				Iterator<Categoria> it = categorias.iterator();
				// Imprime opcoes
				while (it.hasNext()) {
					Categoria cat = it.next();

					if (it.hasNext()) {
						util.print(cat.getOpcao() + " - " + cat.getNome(),
								false);
					} else {
						util.print(cat.getOpcao() + " - " + cat.getNome(), true);
					}
				}
				util.print("Escolha uma opcao: ", true);
				opcao = in.nextInt();

			} while (!util.opcaoValida(categorias, opcao));
			
			Categoria categoria = categorias.get(opcao);
			
			//Escolhe palavra aleatória
			Palavra palavra = categoria.getPalavra(Util.randInt(0, categoria.getPalavras().size()-1));
			
			while (!(venceu ^ perdeu)){
				
				String opc;
				
				util.print(palavra.getPalavraJogo(), false);
				
				opc = in.next().toUpperCase();
				
				//Verifica se usuario deseja arriscar um palpite
				if(opc.equals("FORCA")){
					util.print("Qual a palavra", false);
					
					palavra.Tentativa(in.next().toUpperCase());
					
					if(!palavra.venceu()){
						util.print("Palavra errada", false);
						continue;
					}
					
					
				}else{
					palavra.Tentativa(opc.charAt(0));
				}
				
				venceu = palavra.venceu();
				
				perdeu = palavra.perdeu();
				 
			}
			
			if(venceu){
				util.print("Parabens vc Venceu =) !!", false);
			}else{
				util.print("Vc perdeu =//", false);
			}
			
			util.print("Palavra Correta: " + palavra.getPalavra(), false);
			
			util.print("Seu Jogo: " + palavra.getPalavraJogo(), false);
			
			util.print("Numero de Tentativas: " + palavra.getTentativas(), true);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
