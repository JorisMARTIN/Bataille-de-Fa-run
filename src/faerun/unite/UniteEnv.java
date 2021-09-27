package faerun.unite;

public class UniteEnv {
	
	/* NOM DES UNITES */
	public static String NOM_UNITE_BASE = "Guerrier";
	public static final String NOM_UNITE_NAIN = "Nain";
	public static final String NOM_UNITE_CHEF_NAIN = "Chef Nain";
	public static final String NOM_UNITE_ELFE = "Elfe";
	public static final String NOM_UNITE_CHEF_ELFE = "Chef Elfe";
	
	/* VALEURS DE BASE */
	public static final int FORCE_DE_BASE = 10;
	public static final int VIE_DE_BASE = 100;
	public static final int RESSOURCE_BASE = 1;
	
	/* NAIN */
	public static final int NAIN_DEGATS_DIVISEUR = 2;
	public static final int NAIN_RESSOURCES_POUR_ENTRAINEMENT = RESSOURCE_BASE;
	
	/* CHEF NAIN */
	public static final int CHEF_NAIN_DEGATS_DIVISEUR = 2; // Prendre en compte qu'il possède les capacitées de la classe Nain
	public static final int CHEF_NAIN_RESSOURCES_POUR_ENTRAINEMENT = 3;
	
	/* ELFE */
	public static final int ELFE_DEGATS_MULTIPLICATEUR = 2;
	public static final int ELFE_RESSOURCES_POUR_ENTRAINEMENT = 2;
	
	/* CHEF ELFE */
	public static final int CHEF_ELFE_DEGATS_MULTIPLICATEUR = 2; // Prendre en compte qu'il possède les capacitées de la classe Elfe
	public static final int CHEF_ELFE_RESSOURCES_POUR_ENTRAINNEMENT = 4;
	
}
