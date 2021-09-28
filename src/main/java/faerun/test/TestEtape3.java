package faerun.test;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

import faerun.Carreau;
import faerun.Chateau;
import faerun.Plateau;
import faerun.unite.ChefElfe;
import faerun.unite.ChefNain;
import faerun.unite.Elfe;
import faerun.unite.Guerrier;
import faerun.unite.Nain;
import faerun.utils.Couleur;

public class TestEtape3 {

	public static void main(String[] args) {
		Plateau plateau = new Plateau();
		
		Chateau chateauBleu = new Chateau(Couleur.BLEU);
		chateauBleu.setPlateau(plateau);
		
		Nain nainBleu = new Nain();
		ChefNain chefNainBleu = new ChefNain();
		Elfe elfeBleu = new Elfe();
		ChefElfe chefElfeBleu = new ChefElfe();
		
		chateauBleu.ajoutGuerrierNovice(nainBleu);
		chateauBleu.ajoutGuerrierNovice(chefNainBleu);
		chateauBleu.ajoutGuerrierNovice(elfeBleu);
		chateauBleu.ajoutGuerrierNovice(chefElfeBleu);
		
		chateauBleu.entrainer();
		
		Chateau chateauRouge = new Chateau(Couleur.ROUGE);
		chateauRouge.setPlateau(plateau);
		
		Nain nainRouge = new Nain();
		ChefNain chefNainRouge = new ChefNain();
		Elfe elfeRouge = new Elfe();
		ChefElfe chefElfeRouge = new ChefElfe();
		
		chateauRouge.ajoutGuerrierNovice(nainRouge);
		chateauRouge.ajoutGuerrierNovice(chefElfeRouge);
		chateauRouge.ajoutGuerrierNovice(elfeRouge);
		chateauRouge.ajoutGuerrierNovice(chefNainRouge);
		chateauRouge.entrainer();
		
		System.out.println("Etat initial plateau : ");
		affichePlateau(plateau);
		
		System.out.println("=======\nDéplacement\n=======");
		plateau.avancerGuerriers();
		affichePlateau(plateau);
		
		System.out.println("=======\nDéplacement\n=======");
		plateau.avancerGuerriers();
		affichePlateau(plateau);
		
		System.out.println("=======\nDéplacement\n=======(Ne doit pas bouger)");
		plateau.avancerGuerriers();
		affichePlateau(plateau);
		
		System.out.println("////Nouvel entrainement////");
		chateauBleu.entrainer();
		chateauRouge.entrainer();
		chateauBleu.entrainer();
		chateauRouge.entrainer();
		
		affichePlateau(plateau);
		
		System.out.println("=======\nDéplacement x 2 \n=======");
		plateau.avancerGuerriers();
		plateau.avancerGuerriers();
		affichePlateau(plateau);
		
	}
	
	private static void affichePlateau(Plateau plateau) {
		for (int i = 0; i < plateau.getCarreaux().length; i++) {
			Carreau current = plateau.getCarreaux()[i];
			System.out.println("Carreau " + (i+1) + " :\n\tBleu :");
			for (Guerrier g : current.getGuerriersBleus()) {
				System.out.println("\t\t" + g);
			}
			System.out.println("\tRouge : ");
			for (Guerrier g : current.getGuerriersRouges()) {
				System.out.println("\t\t" + g);
			}
			System.out.println();
		}
	}

}
