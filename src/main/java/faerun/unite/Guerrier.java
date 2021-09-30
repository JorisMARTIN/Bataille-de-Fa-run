package faerun.unite;

import faerun.Chateau;
import faerun.utils.Couleur;
import faerun.utils.CoupDivinException;
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
	
	public void attaquer(Guerrier guerrier) throws CoupDivinException {

		int degatsInfliges = PlateauUtils.De3(this.getForce());

		// Lance coup divin si les dégat infligé sont supérieurs a 80% des dégats maximums pouvant etre infligé
		if (degatsInfliges > (this.getForce() * 3) * 0.8) throw new CoupDivinException("\u001B[41m" + "\u001B[30m" + "COUP DIVIN de la part de " + this + "\u001B[41m" + "\u001B[30m" +  " !!!" + Couleur.TERMINAL_RESET_COLOR_CODE);

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
