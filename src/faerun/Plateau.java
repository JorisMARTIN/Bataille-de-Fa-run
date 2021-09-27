package faerun;

import java.security.InvalidAlgorithmParameterException;
import java.util.LinkedList;

import faerun.unite.Guerrier;
import faerun.utils.Couleur;

public class Plateau {

	private static final int NOMBRE_DE_CARREAU = 5;
	
	private Carreau[] carreaux;
	
	public Plateau() {
		this.carreaux = new Carreau[NOMBRE_DE_CARREAU];
		for(int i = 0; i < NOMBRE_DE_CARREAU; i++) {
			carreaux[i] = new Carreau();
		}
	}
	
	public Carreau[] getCarreaux() {
		return this.carreaux;
	}
	
	/**
	 * @param guerrier
	 * 
	 * Leve une exception si le guerrier ne possède pas de chateau
	 * Pose le guerrier sortie de l'entrainement sur le carreaux de départ de son chateau
	 */
	public void lancerGuerrier(Guerrier guerrier) throws InvalidAlgorithmParameterException {
		if (guerrier.getChateau() == null) {
			throw new InvalidAlgorithmParameterException("Le guerrier ne possède pas de chateau !");
		}
		
		if (guerrier.getChateau().estBleu()) carreaux[0].ajouterGuerrier(guerrier);
		else carreaux[NOMBRE_DE_CARREAU - 1].ajouterGuerrier(guerrier);
	}
	
	/**
	 * 
	 * @return La couleur gagnante. null si aucune victoire, Couleur.BLANC si égalité
	 */
	public Couleur getGagnant() {
		boolean rougeWin = this.getCarreaux()[0].estRouge();
		boolean bleuWin = this.getCarreaux()[this.getCarreaux().length - 1].estBleu();
			
		if (!rougeWin && !bleuWin) return null;
		else if (rougeWin && !bleuWin) return Couleur.ROUGE;
		else if (!rougeWin && bleuWin) return Couleur.BLEU;
		else return Couleur.BLANC;
	}
	
	public void lanceCombat(Couleur couleur) {
		
		for (int i = 0; i < this.getCarreaux().length; i++) {
			Carreau current = this.getCarreaux()[i];
			if (current.estChampDeBataille()) {
				System.out.println("Carreau " + i + " : Combat !");
				current.lanceCombat(couleur, false);
			} else {
				System.out.println("Carreau " + i + " : Pas de combat.");
			}
		}
		
	}
	
	public void avancerGuerriers() {
		
		LinkedList<Guerrier> attente = new LinkedList<Guerrier>();
		
		for (int i = 0; i < NOMBRE_DE_CARREAU; i++) {
			Carreau currentCarreau = carreaux[i];
			
			// Si le carreau est un champ de bataille on continue
			if (currentCarreau.estChampDeBataille()) {
				currentCarreau.ajouterGuerriers(Couleur.BLEU, attente);
				attente.clear();
				continue;
			};

			LinkedList<Guerrier> save = currentCarreau.retirerGuerriers(Couleur.BLEU);
			currentCarreau.ajouterGuerriers(Couleur.BLEU, attente);

			// On avance les rouges sauf a la premiere itération ou si il y a un champ de bataille
			if (i > 0 && !currentCarreau.estChampDeBataille()) {
				carreaux[i-1].ajouterGuerriers(Couleur.ROUGE, currentCarreau.retirerGuerriers(Couleur.ROUGE));
			}

			attente = save;
		}
	}
}
