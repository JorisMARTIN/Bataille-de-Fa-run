package faerun.unite;

public class Nain  extends Guerrier {

	public Nain() {
		super();
	}
	
	@Override
	protected int subirDegats(int degats) {
		int degatsSubit = degats/UniteEnv.NAIN_DEGATS_DIVISEUR;
		super.subirDegats(degatsSubit);
		return degatsSubit;
	}
	
	@Override
	public int getRessourcesPourEntrainement() {
		return UniteEnv.NAIN_RESSOURCES_POUR_ENTRAINEMENT;
	}
	
	@Override
    public String getName() {
		return UniteEnv.NOM_UNITE_NAIN;
	}
}
