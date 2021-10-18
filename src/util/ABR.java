package util;

import java.util.Arrays;

import sac.*;
/**
 * Classe ABR pour la méthode PSE.
 */
public class ABR {

    private Objet[] objetsNoeud;
    private ABR sousAbrGauche;
    private ABR sousAbrDroit;

    private int profondeur;

    private static float borneInf=0;
    private float borneSup;

    private static Objet[] listeObjetFin;

    //constructor récursif pour creer l'arbre entier
    public ABR(Objet[] objets, Objet[] tabObj, int profondeur) {
        if (profondeur <= objets.length) {

            // copie du tabObj dans this.objets

            this.objetsNoeud = new Objet[tabObj.length];

            for (int i=0; i<objets.length; ++i){
                if (tabObj[i] != null){
                    this.objetsNoeud[i] = tabObj[i];
                }
            }

            this.profondeur = profondeur;
            this.setBorneSup(objets);
            this.setBorneInf();

            if (profondeur != objets.length) {

                //copie + ajout de l'objet suivant
                //ajout de l'objet suivant dans tabObj
                tabObj[profondeur] = objets[profondeur];
                //verifier avant d'explorer les noeuds descendants inutiles
                if (this.poidsObjetsNoeud(tabObj) <= SacADos.poids_maximal && this.borneSup > ABR.borneInf) {
                    this.sousAbrDroit = new ABR(objets, tabObj, profondeur + 1);
                } else {
                    this.sousAbrDroit = null;
                }

                tabObj[profondeur] = null;
                //copie du noeud
                this.sousAbrGauche = new ABR(objets, tabObj, profondeur + 1);

            } else {
                this.sousAbrGauche = null;
                this.sousAbrDroit = null;
            }
        }
    }
    public float getBorneInf(){
        return borneInf;
    }

    /**
     * méthode pour rechercher le meilleur noeud avec la liste d'objets à mettre dans le sac
     * par rapport à la borne inferieure car la borne inferieure est modifiée dès qu'on trouve le meilleur noeud.
     * Ici il suffit de rechercher le noeud equivalent à la borne inferieure.
     */
    public void rechercheMeilleurNoeud(){
        //on commence par les sous arbres droits où les objets sont mis dans le sac a chaque fois
        //pour potentiellement trouver le noeud des le début.
        if(this.sousAbrDroit!=null) {
            this.sousAbrDroit.rechercheMeilleurNoeud();
        }
        //verifier si on a trouvé le meilleur noeud par rapport à la borne inferieure
        //puis stocker les objets dans un tableau static et quitter
        if(this.valeurObjetsNoeud()==ABR.borneInf) {
            this.listeObjetFin=this.objetsNoeud;
            return;
        }
        if(this.sousAbrGauche!=null) {
            this.sousAbrGauche.rechercheMeilleurNoeud();
        }
    }
    public Objet[] getListeObjetFin(){
        return this.listeObjetFin;
    }
    /*
     * mis à jour de l'attribut statique borneInferieure lorsqu'une meilleure valeur (correspondant à une combinaison) est trouvée
     * mis à jour lors de la construction de l'arbre
     */
    public void setBorneInf(){
        if (this.valeurObjetsNoeud() > ABR.borneInf){
            ABR.borneInf = this.valeurObjetsNoeud();
        }
    }

    /*
     * calcul pour chaque noeud (ABR) la valeur max que pourra avoir la combinaison finale à partir d'un noeud
     */
    public void setBorneSup(Objet[] objets){
        float res = 0;
        res += this.valeurObjetsNoeud(); // valeur totale du noeud courant
        for (int i=this.profondeur; i<objets.length; ++i){
            res += objets[i].getValeur(); // ajout des valeurs des objets restants
        }
        this.borneSup = res;
    }
    public float valeurObjetsNoeud(){
        float res = 0;
        for(int i=0; i<this.objetsNoeud.length; ++i){
            if (this.objetsNoeud[i] != null){
                res += this.objetsNoeud[i].getValeur();
            }
        }
        return res;
    }
    public float poidsObjetsNoeud(Objet[] tabObj){
        float res=0;
        for(int i=0; i<tabObj.length; ++i){
            if (tabObj[i] != null){
                res += tabObj[i].getPoids();
            }
        }
        return res;
    }


}


