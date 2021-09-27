package faerun;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import faerun.unite.ChefElfe;
import faerun.unite.ChefNain;
import faerun.unite.Elfe;
import faerun.unite.Guerrier;
import faerun.unite.Nain;
import faerun.unite.UniteEnv;
import faerun.utils.Couleur;
import faerun.utils.PlateauUtils;

public class GameManager {
	
	private Scanner sc;

	private Plateau plateau;
	
	private Chateau chateauBleu;
	private Chateau chateauRouge;
	
	private Chateau winner;
	
	
	public GameManager() {
		
		this.sc = new Scanner(System.in);
		
		this.plateau = new Plateau();
		this.chateauBleu = new Chateau(Couleur.BLEU);
		this.chateauRouge = new Chateau(Couleur.ROUGE);
		
		this.getChateauBleu().setPlateau(this.getPlateau());
		this.getChateauRouge().setPlateau(this.getPlateau());
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public Chateau getChateauBleu() {
		return chateauBleu;
	}

	public Chateau getChateauRouge() {
		return chateauRouge;
	}

	public Chateau getWinner() {
		return winner;
	}

	public void setWinner(Chateau winner) {
		this.winner = winner;
	}
	
	public void launchGame() {
		
		System.out.println("\n\n\t\tDébut de la partie\n\n");
		
		boolean isWin = false;
		Couleur gagnant = null;
		int tour = 0;
		
		/**
		 * Boucle de jeu
		 * 
		 * 1 - Ajout des guerriers novices
		 *  1.1 - Bleu
		 *  1.2 - Rouge
		 *  
		 * 2 - Entrainement
		 *  2.1 - Bleu
		 *  2.2 - Rouge
		 *  
		 * 3 - Deplacement des geurriers
		 * 	3.1 - Bleu 1 tour / 2
		 *  3.2 - Rouge quand pas bleu
		 *  
		 * 4 - Verification pour chaque carreau si il y a combat
		 *  4.1 - Oui -> Lance combat 
		 *  
		 * 5 - Vérification de la victoire
		 */
		while(!isWin) {
			
			System.out.println("\n\t\tSaisie des guerriers novices :");
			saisieGuerrierNovices(this.getChateauBleu());
			saisieGuerrierNovices(this.getChateauRouge());
			
			System.out.println("\n\t\tEntrainement des guerriers : ");
			List<Guerrier> bleusEntraine = this.getChateauBleu().entrainer();
			List<Guerrier> rougesEntraine = this.getChateauRouge().entrainer();
			System.out.println("\tBleus entrainés : " + bleusEntraine.toString());
			System.out.println("\tRouges entrainés : " + rougesEntraine.toString());
			
			System.out.println("\n\t\tDéplacement des guerriers :");
			this.getPlateau().avancerGuerriers();
			
			System.out.println("\n\t\tEtat plateau avant combat :");
			PlateauUtils.affichePlateau(this.getPlateau());
			
			System.out.println("\n\t\tLancement des combats : ");
			this.getPlateau().lanceCombat((tour % 2 == 0 ? Couleur.BLEU : Couleur.ROUGE));
			
			System.out.println("\n\t\tEtat plateau après combat :");
			PlateauUtils.affichePlateau(this.getPlateau());
			
			gagnant = this.getPlateau().getGagnant();
			if (gagnant != null) {
				isWin = true;
			}
		}
		
		
		System.out.println("\n\t\tFin de la partie !");
		if (gagnant == Couleur.ROUGE) {
			System.out.println(Couleur.ROUGE.getTerminalColorCode() + "\n\t\tLe chateau Rouge a gagné !" + Couleur.TERMINAL_RESET_COLOR_CODE);
		} else if (gagnant == Couleur.BLEU) {
			System.out.println(Couleur.BLEU + "\n\t\tLe chateau Bleu a gagné !" + Couleur.TERMINAL_RESET_COLOR_CODE);
		} else {
			System.out.println("\n\t\tEgalité");
		}
		
	}
	
	public void saisieGuerrierNovices(Chateau chateau) {
		System.out.println("Chateau " + (chateau.getCouleur() == Couleur.BLEU ? Couleur.BLEU.getTerminalColorCode() + "Bleu" : Couleur.ROUGE.getTerminalColorCode() + "Rouge") + Couleur.TERMINAL_RESET_COLOR_CODE);
		System.out.println("Souhaitez-vous ajotuer des guerrier novices ? (Oui / Non)\nEtat actuelle de la file novices : " + chateau.getGuerriersNovices().toString());
		String res = sc.nextLine();
		
		if (res.equalsIgnoreCase("Non")) {
			System.out.println();
			return;
		}
		
		System.out.println(
				"Saisissez dans l'orde d'entrainement souhaité les guerriers à placer en formation : \n"
				+ "\t[1] - " + UniteEnv.NOM_UNITE_NAIN + "\t\t(Coût : " + UniteEnv.NAIN_RESSOURCES_POUR_ENTRAINEMENT + ")\n"
				+ "\t[2] - " + UniteEnv.NOM_UNITE_ELFE + "\t\t(Coût : " + UniteEnv.ELFE_RESSOURCES_POUR_ENTRAINEMENT + ")\n"
				+ "\t[3] - " + UniteEnv.NOM_UNITE_CHEF_NAIN + "\t\t(Coût : " + UniteEnv.CHEF_NAIN_RESSOURCES_POUR_ENTRAINEMENT + ")\n"
				+ "\t[4] - " + UniteEnv.NOM_UNITE_CHEF_ELFE + "\t\t(Coût : " + UniteEnv.CHEF_ELFE_RESSOURCES_POUR_ENTRAINNEMENT + ")\n\n");
	
		System.out.println("Saisissez dans l'odre les indices des guerriers à former séparer d'un ' '. Si vous ne souhaiter rien former appuyer sur ENTRER.");
		System.out.println("Ressouces disponible : " + chateau.getRessources());
		res = sc.nextLine();
		
		if (res.length() != 0) {
			boolean isCorrect = false;
			while (!isCorrect) {
				
				try {
					List<Integer> indices = Arrays.stream(res.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
					
					isCorrect = true;
					
					for (Integer i : indices) {
						
						switch(i) {
						
						// NAIN
						case 1:
							System.out.println("Ajout Nain - OK");
							chateau.ajoutGuerrierNovice(new Nain());
							break;
							
							// ELFE
						case 2:
							System.out.println("Ajout Elfe - OK");
							chateau.ajoutGuerrierNovice(new Elfe());
							break;
							
							// CHEF NAIN
						case 3:
							System.out.println("Ajout Chef Nain - OK");
							chateau.ajoutGuerrierNovice(new ChefNain());
							break;
							
							// CHEF ELFE
						case 4:
							System.out.println("Ajout Chef Elfe - OK");
							chateau.ajoutGuerrierNovice(new ChefElfe());
							break;
							
							// NOMBRE INVALIDE
						default:
							System.out.println("[" + i + "] - Invalide (Non-traité)");
							break;
						}
						
					}
					
					// L'utilisateur a saisi un nombre invalide
				} catch (NumberFormatException e) {
					System.err.println("La suite d'indices renseigne de contient pas que des chiffres ! Veillez recommencer :");
				}
			}
			
			System.out.println("Fin d'ajout! Etat après ajout de la file novice : " + chateau.getGuerriersNovices().toString() + "\n" + Couleur.TERMINAL_RESET_COLOR_CODE);
		}
	}
	
}
