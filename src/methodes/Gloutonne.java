package methodes;

import sac.*;
import util.TabEtNewPivot;

public class Gloutonne {

    public Gloutonne(SacADos sac){
        sac.setListeObjets(triRapideRec(sac.getListeObjets(), 0, sac.getListeObjets().length - 1));

        for (int i = 0; i < sac.getListeObjets().length; ++i) {
            // mettre l'objet dans le sac
            sac.getListeObjets()[i].setEstStocke(1);

            // tester si l'objet peut rentrer : si oui laisser(estStocke=1) sinon le
            // retirer(estStocke=0)
            if (sac.poidsTotal() > sac.poids_maximal) {
                sac.getListeObjets()[i].setEstStocke(0);
            }
        }

    }
    /*
     * Methode recursive qui prend un tableau pour effectuer un tri rapide dessus
     * @return un tableau trié dans l'ordre decroissant
     */
    private Objet[] triRapideRec(Objet[] listeObjets, int premier, int dernier) {
        if (premier < dernier) {
            TabEtNewPivot tabEtNewPivot = new TabEtNewPivot();
            int p = choixPivot(listeObjets, premier, dernier);
            //repartir toutes les petites valeurs à gauche et les grandes à droite par rapport au pivot
            tabEtNewPivot = repartition(listeObjets, premier, dernier, p);
            //REFAIRE
            //gauche
            triRapideRec(tabEtNewPivot.getTabObjets(), premier, tabEtNewPivot.getNewPivot() - 1);
            //droite
            triRapideRec(tabEtNewPivot.getTabObjets(), tabEtNewPivot.getNewPivot() + 1, dernier);
        }
        return listeObjets;
    }

    /*
     * choix du pivot : l'objet au milieu
     * @return indice du pivot
     */
    private int choixPivot(Objet[] listeObjets, int premier, int dernier) {
        return (premier + dernier) / 2;
    }

    /*
     * Méthode de répartition (met les éléments < pivot à gauche et > pivot à droite)
     * @return un struct (tableau + pivot)
     */
    private TabEtNewPivot repartition(Objet[] listeObjets, int premier, int dernier, int pivot) {
        TabEtNewPivot tabEtNewPivot = new TabEtNewPivot();
        listeObjets = echanger(listeObjets, pivot, dernier);
        int j = premier;
        for (int i = premier; i < dernier; ++i) {
            if (listeObjets[i].getRapport() > listeObjets[dernier].getRapport()) { // tri décroissant
                listeObjets = echanger(listeObjets, i, j);
                j++;
            }
        }
        listeObjets = echanger(listeObjets, dernier, j);
        tabEtNewPivot.setTabObjets(listeObjets);
        tabEtNewPivot.setNewPivot(j);
        return tabEtNewPivot;
    }

    /*
     * fonction pour échanger deux valeurs dans le tableau
     */
    private Objet[] echanger(Objet[] listeObjets, int i, int j) {
        Objet tmp = listeObjets[i];
        listeObjets[i] = listeObjets[j];
        listeObjets[j] = tmp;
        return listeObjets;
    }

}

