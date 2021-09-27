package faerun.unite;

public class ChefNain extends Nain {
	
	public ChefNain() {
		super();
	}
	
	@Override
	protected int subirDegats(int degats) {
		int degatsSubits = degats / UniteEnv.CHEF_NAIN_DEGATS_DIVISEUR;
		super.subirDegats(degatsSubits);
		return degatsSubits;
	}
	
	@Override
	public int getRessourcesPourEntrainement() {
		return UniteEnv.CHEF_NAIN_RESSOURCES_POUR_ENTRAINEMENT;
	}
	
	@Override
    public String getName() {
		return UniteEnv.NOM_UNITE_CHEF_NAIN;
	}
}
