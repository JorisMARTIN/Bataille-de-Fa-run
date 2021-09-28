package faerun.unite;

public class ChefElfe extends Elfe {
	

	public ChefElfe() {
		super();
	}
	
	@Override
	public int getForce() {
		return super.getForce() * UniteEnv.CHEF_ELFE_DEGATS_MULTIPLICATEUR;
	}
	
	@Override
	public int getRessourcesPourEntrainement() {
		return UniteEnv.CHEF_ELFE_RESSOURCES_POUR_ENTRAINNEMENT;
	}
	
	@Override
    public String getName() {
		return UniteEnv.NOM_UNITE_CHEF_ELFE;
	}
}
