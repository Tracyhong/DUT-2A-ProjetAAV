package util;

import sac.Objet;

/**
 * Classe pour la méthode gloutonne qui permet de stocker le tableau d'objet et un nouveau pivot lors du quicksort
 */

public class TabEtNewPivot {
    private Objet[] tabObjets;
    private int newPivot;

    public Objet[] getTabObjets() {
        return this.tabObjets;
    }
    public void setTabObjets(Objet[] tabObjets) {
        this.tabObjets = tabObjets;
    }
    public int getNewPivot() {
        return this.newPivot;
    }
    public void setNewPivot(int newPivot) {
        this.newPivot = newPivot;
    }

}

