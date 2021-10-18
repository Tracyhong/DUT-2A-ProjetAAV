package sac;

import java.util.Arrays;
/**
 * Classe Objet (nom, valeur=prix, poids, rapport, estStocké(dans le sac)
 */

public class Objet {
    private String nom;
    private float valeur;
    private float poids;
    private float rapport;
    private int estStocke;

    public Objet(String nom, float poids, float prix) {
        this.nom=nom;
        this.valeur=prix;
        this.poids=poids;
        this.rapport=this.valeur/this.poids;
        this.estStocke=0;
    }

    public float getPoids() {
        return this.poids;
    }
    public float getValeur() {
        return this.valeur;
    }
    public float getRapport() {
        return this.rapport;
    }
    public int getEstStocke() {
        return this.estStocke;
    }
    public void setEstStocke(int valeur) {
        this.estStocke=valeur;
    }

    public boolean equals(Objet o){
        if(this.nom==o.nom&&this.poids==o.poids&&this.valeur==o.valeur&&this.rapport==o.rapport)
            return true;
        else return false;
    }

    public String toString() {
        return this.nom + " (poids : " + this.poids+" , prix : "+ this.valeur+")";
    }

}
