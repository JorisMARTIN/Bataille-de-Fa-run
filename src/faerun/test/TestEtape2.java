package faerun.test;

import java.util.List;

import faerun.Chateau;
import faerun.unite.ChefElfe;
import faerun.unite.ChefNain;
import faerun.unite.Elfe;
import faerun.unite.Guerrier;
import faerun.unite.Nain;
import faerun.utils.Couleur;

public class TestEtape2 {

	public static void main(String[] args) {
		
		Chateau chateauBleu = new Chateau(Couleur.BLEU);
		
		Nain nainBleu = new Nain();
		ChefNain chefNainBleu = new ChefNain();
		Elfe elfeBleu = new Elfe();
		ChefElfe chefElfeBleu = new ChefElfe();
		
		chateauBleu.ajoutGuerrierNovice(chefElfeBleu);
		chateauBleu.ajoutGuerrierNovice(nainBleu);
		chateauBleu.ajoutGuerrierNovice(chefNainBleu);
		chateauBleu.ajoutGuerrierNovice(elfeBleu);
		
		Chateau chateauRouge = new Chateau(Couleur.ROUGE);
		
		Nain nainRouge = new Nain();
		ChefNain chefNainRouge = new ChefNain();
		Elfe elfeRouge = new Elfe();
		ChefElfe chefElfeRouge = new ChefElfe();
		
		chateauRouge.ajoutGuerrierNovice(nainRouge);
		chateauRouge.ajoutGuerrierNovice(chefElfeRouge);
		chateauRouge.ajoutGuerrierNovice(elfeRouge);
		chateauRouge.ajoutGuerrierNovice(chefNainRouge);
		
		// Simulation 3 tours
		for (int tour = 0; tour < 3; tour ++) {
			System.out.println("\n===============\nTour " + tour + "\n===============");
			
			System.out.println("Liste attente bleu : ");
			for(Guerrier g : chateauBleu.getGuerriersNovices()) {
				System.out.println("\t" + g);
			}
			System.out.println("\nListe attente rouge : ");
			for(Guerrier g : chateauRouge.getGuerriersNovices()) {
				System.out.println("\t" + g);
			}
			List<Guerrier> entrainerBleu = chateauBleu.entrainer();
			List<Guerrier> entrainerRouge = chateauRouge.entrainer();
			
			System.out.println("\nGuerriers bleus entrainé : ");
			for(Guerrier guerrier : entrainerBleu) {
				System.out.println("\t" + guerrier);
			}
			System.out.println("\nGuerriers rouges entrainé : ");
			for(Guerrier guerrier : entrainerRouge) {
				System.out.println("\t" + guerrier);
			}
		}
		
	}

}
