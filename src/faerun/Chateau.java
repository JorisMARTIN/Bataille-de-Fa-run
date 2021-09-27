package faerun;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import faerun.unite.Guerrier;
import faerun.utils.Couleur;

public class Chateau {

	private static final int RESSOURCES_INITIALES = 3;
	private static final int RESSOURCES_AJOUTES_PAR_TOUR = 1;
	
	private int ressources;
	private Couleur couleur;
	
	private LinkedList<Guerrier> guerriersNovices;
	
	private Plateau plateau;
	
	public Chateau(Couleur couleur) {
		guerriersNovices = new LinkedList<Guerrier>();
		
		this.ressources = RESSOURCES_INITIALES;
		this.couleur = couleur;
	}
	
	public void ajoutGuerrierNovice(Guerrier guerrier) {
		this.guerriersNovices.add(guerrier);
		guerrier.setChateau(this);
	}
	
	public LinkedList<Guerrier> getGuerriersNovices() {
		return this.guerriersNovices;
	}
	
	public List<Guerrier> entrainer() {
		/*
         * Tant que ressources >= guerrierNovices.get(0).getRessourcesPour...
         * 
         * Lire premier élement de GuerrierNovice
         * 
         * 		* => Ajout a GuerrierEntraine et retirement de GuerrierNovice
         * 		* Maj des ressources 
         * 
         * Incrémentation ressources
         * 
         * Placer les guerrier entrainer sur le plateau
         */
		
		ArrayList<Guerrier> guerrierEntraines = new ArrayList<Guerrier>();
		
		while (!this.getGuerriersNovices().isEmpty() && this.ressources >= this.getGuerriersNovices().getFirst().getRessourcesPourEntrainement()) {
			Guerrier guerrier = this.getGuerriersNovices().getFirst();
			guerrierEntraines.add(guerrier);
			guerriersNovices.removeFirst();
			this.ressources -= guerrier.getRessourcesPourEntrainement();
		}
		
		this.incrementerRessources();
		
		// On envoie les guerrier entrainer sur le plateau
		for(Guerrier g : guerrierEntraines) {
			try {
				plateau.lancerGuerrier(g);
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			}
		}
		
		return guerrierEntraines;
	}
	
	private void incrementerRessources() {
		this.ressources += RESSOURCES_AJOUTES_PAR_TOUR;
	}
	
	public int getRessources() {
		return ressources;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public Plateau getPlateau() {
		return this.plateau;
	}
	
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	public boolean estBleu() {
		return this.getCouleur() == Couleur.BLEU;
	}
	
	public boolean estRouge() {
		return this.getCouleur() == Couleur.ROUGE;
	}
	
}
