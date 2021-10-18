package methodes;

import sac.*;

public class Dynamique {
	private final int MULTIPLICATEUR = 10;

	// que des valeurs de float
	public Dynamique(SacADos sac) {
		int[][] matrice = new int[sac.getListeObjets().length][(int) SacADos.poids_maximal * MULTIPLICATEUR + 1]; // x10

		// remplir la 1ere ligne
		for (int j = 0; j < SacADos.poids_maximal * MULTIPLICATEUR; j++) { // x10
			if (sac.getListeObjets()[0].getPoids() * MULTIPLICATEUR > j)
				matrice[0][j] = 0;// x10
			else
				matrice[0][j] = (int) sac.getListeObjets()[0].getValeur();
		}
		// remplir les autres lignes
		for (int i = 1; i < sac.getListeObjets().length; i++) {
			for (int j = 0; j <= SacADos.poids_maximal * MULTIPLICATEUR; j++) {// x10
				if (sac.getListeObjets()[i].getPoids() * MULTIPLICATEUR > j)
					matrice[i][j] = matrice[i - 1][j];// x10
				else
					matrice[i][j] = (int) Math.max(matrice[i - 1][j],
							matrice[i - 1][(int) (j - sac.getListeObjets()[i].getPoids() * MULTIPLICATEUR)]
									+ sac.getListeObjets()[i].getValeur());// x10
			}
		}
		// recuperer la liste d'objets
		int i = sac.getListeObjets().length - 1;
		int j = (int) SacADos.poids_maximal * MULTIPLICATEUR;// x10
		while (matrice[i][j] == matrice[i][j - 1]) {
			j--;
		}
		while (j > 0) {
			while (i > 0 && matrice[i][j] == matrice[i - 1][j]) {
				i--;
			}
			j = j - (int) (sac.getListeObjets()[i].getPoids() * MULTIPLICATEUR);// x10
			if (j >= 0)
				sac.getListeObjets()[i].setEstStocke(1);
			i--;
		}
	}
}
