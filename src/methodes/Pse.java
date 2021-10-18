package methodes;

import sac.*;
import util.ABR;

public class Pse {

	public Pse(SacADos sac) {
		Objet[] tabObj = new Objet[sac.getListeObjets().length];

		ABR arbre = new ABR(sac.getListeObjets(), tabObj, 0);

		arbre.rechercheMeilleurNoeud();
		Objet[] objets = arbre.getListeObjetFin();
		for (int i = 0; i < sac.getListeObjets().length; ++i) {
			// puisque objets et listeObjets dans le meme ordre, si dans objets, il y a une
			// case vide, passer
			if (objets[i] != null) {
				// mettre l'objet dans le sac
				sac.getListeObjets()[i].setEstStocke(1);
			}
		}
	}
}
