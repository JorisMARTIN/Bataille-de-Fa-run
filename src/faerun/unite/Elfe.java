package faerun.unite;

public class Elfe extends Guerrier {
	
	public Elfe() {
		super();
	}
	
	@Override
	public int getForce() {
		return super.getForce() * UniteEnv.ELFE_DEGATS_MULTIPLICATEUR;
	}
	
	@Override
	public int getRessourcesPourEntrainement() {
		return UniteEnv.ELFE_RESSOURCES_POUR_ENTRAINEMENT;
	}
	
	@Override
    public String getName() {
		return UniteEnv.NOM_UNITE_ELFE;
	}
}
