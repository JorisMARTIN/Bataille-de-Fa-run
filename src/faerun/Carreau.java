package faerun;

import java.security.InvalidAlgorithmParameterException;
import java.util.LinkedList;

import faerun.unite.Guerrier;
import faerun.utils.Couleur;

public class Carreau {
	
	private LinkedList<Guerrier> guerriersBleus;
	private LinkedList<Guerrier> guerriersRouges;	
	
	public Carreau() {
		this.guerriersBleus = new LinkedList<Guerrier>();
		this.guerriersRouges = new LinkedList<Guerrier>();
	}

	public LinkedList<Guerrier> getGuerriersBleus() {
		return guerriersBleus;
	}

	public LinkedList<Guerrier> getGuerriersRouges() {
		return guerriersRouges;
	}
	
	/**
	 * @param guerrier
	 * @throws InvalidAlgorithmParameterException
	 * 
	 * Leve une exception si le guerrier founis n'appartient a aucun chateau ou si le guerrier est deja sur le carreau
	 * 
	 * 
	 */
	public void ajouterGuerrier(Guerrier guerrier) throws InvalidAlgorithmParameterException {
		if (guerrier.getChateau() == null) {
			throw new InvalidAlgorithmParameterException("Le guerrier " + guerrier + " ne possède pas de chateau !");
		}
		
		// Vérification si le guerrier est déjà présent sur le carreau
		if (this.getGuerriersBleus().contains(guerrier) || this.getGuerriersRouges().contains(guerrier)) {
			throw new InvalidAlgorithmParameterException("Le guerrier " + guerrier + " est déjà présent sur la case !");
		}
		
		if (guerrier.getChateau().estBleu()) this.getGuerriersBleus().add(guerrier);
		else this.getGuerriersRouges().add(guerrier);
	}
	
	/**
	 * 
	 * @param couleur
	 * 
	 * Retire le premier guerrier de la liste correspondant a la couleur
	 */
	public void suprimerGuerrier(Couleur couleur) {
		Guerrier g = this.getGerriersFromColor(couleur).removeFirst();
		g = null;
	}
	
	public void ajouterGuerriers(Couleur couleur, LinkedList<Guerrier> guerriers) {
		this.getGerriersFromColor(couleur).addAll(guerriers);
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<Guerrier> retirerGuerriers(Couleur couleur) {
		LinkedList<Guerrier> guerrierRetires = (LinkedList<Guerrier>) this.getGerriersFromColor(couleur).clone();
		this.getGerriersFromColor(couleur).clear();
		return guerrierRetires;
		
	}
	
	public boolean estChampDeBataille() {
		return this.getGuerriersBleus().size() > 0 && this.getGuerriersRouges().size() > 0;
	}
	
	/**
	 * 
	 *  Pour les guerriers de la couleur choisi
	 * 	Taper 1er guerrier adverse
	 * 	Si Guerrier adverse = mort => Retirer du carreau
	 * 	
	 *  guerrier suivant
	 *  
	 * Récursif avec param manche = 2
	 * 
	 * @param couleur La première couleur a taper
	 * @param estDerniereManche La manche du combat (false = Premiere manche algo rappeler avec l'autre equipe, true = Derniere manche l'algo n'est pas rappelé)
	 * @throws Exception Si le carreau n'est pas un champ de bataille
	 * 
	 */
	public void lanceCombat(Couleur couleur, boolean estDerniereManche) {
		
		Couleur couleurAdverse = (couleur == Couleur.BLEU) ? Couleur.ROUGE : Couleur.BLEU;
		
		for(Guerrier g : this.getGerriersFromColor(couleur)) {
			Guerrier guerrierAdverse = this.getGerriersFromColor(couleurAdverse).getFirst();
			g.attaquer(guerrierAdverse);
			
			// On retire et supprime le guerrier si il est mort
			if (!guerrierAdverse.estVivant()) this.suprimerGuerrier(couleurAdverse);
		}
		
		// Appel récursif avec estDerniereManche = true
		if (!estDerniereManche) this.lanceCombat(couleurAdverse, true);
	}
	
	public boolean estRouge() {
		return this.getGuerriersRouges().size() > 0;
	}
	
	public boolean estBleu() {
		return this.getGuerriersBleus().size() > 0;
	}
	
	/**
	 * 
	 * @param couleur Couleur de l'équipe
	 * @return la liste des guerrier sur le carreau de l'équipe de couleur 'couleur'
	 */
	private LinkedList<Guerrier> getGerriersFromColor(Couleur couleur) {
		return (couleur == Couleur.BLEU) ? this.getGuerriersBleus() : this.getGuerriersRouges();
	}
}
