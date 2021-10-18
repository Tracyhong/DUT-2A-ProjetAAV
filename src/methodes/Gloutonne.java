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
     * fonction qui prend un tableau pour effectuer un tri rapide dessus
     * @return un tableau trié dans l'ordre decroissant
     */
    private Objet[] triRapideRec(Objet[] listeObjets, int premier, int dernier) {
        if (premier < dernier) {
            TabEtNewPivot tabEtNewPivot = new TabEtNewPivot();
            int p = choixPivot(listeObjets, premier, dernier);

            tabEtNewPivot = repartition(listeObjets, premier, dernier, p);
            triRapideRec(tabEtNewPivot.getTabObjets(), premier, tabEtNewPivot.getNewPivot() - 1);
            triRapideRec(tabEtNewPivot.getTabObjets(), tabEtNewPivot.getNewPivot() + 1, dernier);
        }
        return listeObjets;
    }

    /*
     * choix du pivot
     * @return indice du pivot
     */
    private int choixPivot(Objet[] listeObjets, int premier, int dernier) {
        return (premier + dernier) / 2;
    }

    /*
     * fonction de répartition (met les éléments < pivot a gauchet et > pivot à droite
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

