package methodes;

import sac.*;
import util.ABR;

public class Pse {

	public Pse(SacADos sac) {
		Objet[] tabObj = new Objet[sac.getListeObjets().length];
		
		//creer l'abre entier
		ABR arbre = new ABR(sac.getListeObjets(), tabObj, 0);
		
		//cherche le meilleur noeud et stocke la meilleur liste d'objets a mettre dans le sac
		arbre.rechercheMeilleurNoeud();
		Objet[] objetsFin = arbre.getListeObjetFin();
		
		for (int i = 0; i < sac.getListeObjets().length; ++i) {
			// puisque objets et listeObjets dans le meme ordre, si dans objets, il y a une
			// case vide, passer
			if (objetsFin[i] != null) {
				// mettre l'objet dans le sac
				sac.getListeObjets()[i].setEstStocke(1);
			}
		}
	}
}
