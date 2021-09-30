package faerun.test;

import faerun.unite.ChefElfe;
import faerun.unite.ChefNain;
import faerun.unite.Elfe;
import faerun.unite.Guerrier;
import faerun.unite.Nain;
import faerun.utils.CoupDivinException;

public class TestEtape1 {

	public static void main(String[] args) {
		
// 		TEST AVANT PASSAGE DE GUERRIER EN ABSTRACT
//
//		Guerrier g1 = new Guerrier();
//		Guerrier g2 = new Guerrier();
//		
//		System.out.println("Guerrier 1 attaque Guerrier 2");
//		g1.attaquer(g2);
//		System.out.println("Vie guerrier 1 : " + g1.getPointDeVie() + "\nVie guerrier 2 : " + g2.getPointDeVie());
//		
//		System.out.println(
//				"=================================\n"
//				+ "Combat\n"
//				+ "================================="
//		);
//		
//		while(g1.estVivant() && g2.estVivant()) {
//			g1.attaquer(g2);
//			if(g2.estVivant()) {
//				g2.attaquer(g1);
//			}
//			System.out.println(
//					"Fin de manche !\n"
//					+ "Vie Guerrier 1 : " + g1.getPointDeVie() + "\n"
//					+ "Vie Guerrier 2 : " + g2.getPointDeVie() + "\n"
//			);
//		}
//		
//		System.out.println("FIN DU COMBAT !");
//		
//		if (!g1.estVivant()) {
//			System.out.println("Guerrier 1 est mort !");
//		}
//		
//		if(!g2.estVivant()) {
//			System.out.println("Guerrier 2 est mort !");
//		}
		
	
		Nain nain = new Nain();
		ChefNain chefNain = new ChefNain();
		Elfe elfe = new Elfe();
		ChefElfe chefElfe = new ChefElfe();
		
		System.out.println("Etat de base des guerriers\n"
				+ "Chef nain : \n"
					+ "\tVie : " + chefNain.getPointDeVie() + "\n"
					+ "\tForce : " + chefNain.getForce() + "\n"
				+ "Nain : \n"
					+ "\tVie : " + nain.getPointDeVie() + "\n"
					+ "\tForce : " + nain.getForce() + "\n"
				+ "Chef Elfe : \n"
					+ "\tVie : " + chefElfe.getPointDeVie() + "\n"
					+ "\tForce : " + chefElfe.getForce() + "\n"
				+ "Elfe : \n"
					+ "\tVie : " + elfe.getPointDeVie() + "\n"
					+ "\tForce : " + elfe.getForce() + "\n"
		);
		
		System.out.println("Combat !");
		System.out.println("");
		try {
			elfe.attaquer(nain);
			nain.attaquer(elfe);
			chefElfe.attaquer(chefNain);
			chefNain.attaquer(chefElfe);
		} catch (CoupDivinException e) {
			e.printStackTrace();
		}

		
	}

}
