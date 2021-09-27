package faerun.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import faerun.Carreau;
import faerun.Plateau;
import faerun.unite.Guerrier;

public class PlateauUtils {
	
	// Attribut contenant un objet de type random
	private static final Random RANDOM = new Random();
	
	// Méthode qui simule le lancement d’un dé de 3 faces 
	// et retourne le résultat
	public static int De3() {
		return RANDOM.nextInt(3)+1;
	}
	
	// Méthode qui simule plusieurs lancés d’un dé et retourne le résultat
	public static int De3(int nombreDes) {
		int somme = 0;
		for (int i = 0; i < nombreDes; i++) {
			somme = somme + De3();
		}
		return somme;
	}

	
	// Méthode d'affichage
	public static void affichePlateau(Plateau plateau) {
		int nombreDeCarreaux = plateau.getCarreaux().length;
		
		for (int i = 0; i < nombreDeCarreaux; i++) {
			afficheCarreau(plateau.getCarreaux()[i]);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void afficheCarreau(Carreau carreau) {
		
		LinkedList<Guerrier> bleus = (LinkedList<Guerrier>) carreau.getGuerriersBleus().clone();
		LinkedList<Guerrier> rouges = (LinkedList<Guerrier>) carreau.getGuerriersRouges().clone();
		Collections.reverse(bleus);
		
		int largeurCarreau = 0;
		for (Guerrier g : bleus) {
			largeurCarreau += g.getName().length() + 1;
		}
		for (Guerrier g : rouges) {
			largeurCarreau += g.getName().length() + 1;
		}
		
		if (largeurCarreau == 0) {
			largeurCarreau = 5;
		}
		
		largeurCarreau += 5;
		
		
		
		displayHeaderFooter(largeurCarreau);
		for (int h = 0; h < 3; h++) {
			System.out.print('|');
			
			if (h == 1) {
				
				if (bleus.size() == 0 && rouges.size() == 0) {
					System.out.print("          ");
				} else {
					for (Guerrier g : bleus) {
						System.out.print(" " + g);
					}
					
					System.out.print(" <->");
					
					for (Guerrier g : rouges) {
						System.out.print(" " + g);
					}
					System.out.print(' ');
				}
				
			} else {
				for(int l = 0; l < largeurCarreau; l++) {
					System.out.print(' ');
				}
			}
			System.out.println('|');
		}
		displayHeaderFooter(largeurCarreau);
		
	}
	
	private static void displayHeaderFooter(int longeur) {
		System.out.print('+');
		for (int i = 0; i < longeur; i++) {
			System.out.print('-');
		}
		System.out.println("+");
	}
}
