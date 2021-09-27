package faerun.unite;

import faerun.Chateau;
import faerun.utils.Couleur;
import faerun.utils.PlateauUtils;

public abstract class Guerrier {

	private int force;
	private int pointDeVie;
	private Chateau chateau;
	
	public Guerrier() {
		this.force = UniteEnv.FORCE_DE_BASE;
		this.pointDeVie = UniteEnv.VIE_DE_BASE;
	}

	public int getPointDeVie() {
		return pointDeVie;
	}
	
	public int getForce() {
		return force;
	}
	
	public int getRessourcesPourEntrainement() {
		return UniteEnv.RESSOURCE_BASE;
	}
	
	public Chateau getChateau() {
		return this.chateau;
	}
	
	private void setPointDeVie(int pointDeVie) {
		this.pointDeVie = pointDeVie;
	}
	
	public void setChateau(Chateau chateau) {
		this.chateau = chateau;
	}
	
	public boolean estVivant() {
		return this.getPointDeVie() > 0;
	}
	
	public void attaquer(Guerrier guerrier) {
		int degatsInfliges = PlateauUtils.De3(this.getForce());
		int degatsSubits = guerrier.subirDegats(degatsInfliges);
		
		System.out.println(this + " à infligé " + degatsInfliges + " dégats à " + guerrier + " qui en a subit " + degatsSubits);
	}
	
	protected int subirDegats(int degats) {
		int newPointDeVie = this.getPointDeVie() - degats;
		this.setPointDeVie((newPointDeVie < 0) ? 0 : newPointDeVie);
		return degats;
	}
	
	public String getName() {
		return UniteEnv.NOM_UNITE_BASE;
	}
	
	@Override
	public String toString() {
		return this.getChateau().getCouleur().getTerminalColorCode() + this.getName() + Couleur.TERMINAL_RESET_COLOR_CODE;
	}
}
