package sac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import methodes.*;

public class SacADos {

	public static float  poids_maximal;
    private Objet[] listeObjets;

    //CONSTRUCTEUR
    public SacADos(String chemin, float poidsMax) throws FileNotFoundException {
        SacADos.poids_maximal = poidsMax;

        // lire le fichier, recuperer le nb d'objets pour initialiser le tableau
        // d'objets
        int nbObjet = 0;
        try {
            Scanner sc = new Scanner(new File(chemin));
            while (sc.hasNextLine()) {
                ++nbObjet;
                sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Fichier introuvable");
        }
        this.listeObjets = new Objet[nbObjet];

        // remplir le tableau d'objets
        String ligne;
        String[] donneeObjet = new String[3];
        int i = 0;
        try {
            Scanner sc2 = new Scanner(new File(chemin));
            while (sc2.hasNextLine()) {
                ligne = sc2.nextLine();
                donneeObjet = ligne.split("\\s+" + ";" + "\\s+"); // "\\s+" pour les espaces
                this.listeObjets[i] = new Objet(donneeObjet[0], Float.parseFloat(donneeObjet[1]),
                        Float.parseFloat(donneeObjet[2]));
                ++i;
            }
            sc2.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Fichier introuvable");
        }
    }
    
    public Objet[] getListeObjets() {
    	return this.listeObjets;
    }
    public void setListeObjets(Objet[] o) {
    	this.listeObjets=o;
    }
    
    
    public void resoudre(String methode) {
    	
        switch(methode){
            case "gloutonne":
                //this.gloutonne();
                //System.out.println("Méthode gloutonne : "+this.toString());
            	Gloutonne gloutonne=new Gloutonne(this);
            	System.out.println("Méthode gloutonne : "+this.toString());
                break;

            case "dynamique":
                //this.dynamique();
            	Dynamique dynamique=new Dynamique(this);
                System.out.println("Méthode dynamique : "+this.toString());
                break;

            case "pse":
                //this.pse();
            	Pse pse=new Pse(this);
                System.out.println("Méthode pse : "+this.toString());
                break;

            default:
                System.out.println("Mauvaise saisie. Réexecuter.");
                break;
        }
    }
    public float valeurTotale() {
        float total = 0;
        for (Objet o : listeObjets) {
            total += o.getEstStocke() * o.getValeur();
        }
        return total;
    }

    public float poidsTotal() {
        float total = 0;
        for (Objet o : listeObjets) {
            total += o.getEstStocke() * o.getPoids();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sac=new StringBuilder();
        sac.append("Sac à dos (poids maximal = ").append(this.poids_maximal).append("kg) :\n");
        // afficher le poids du sac
        sac.append(" Poids total : ").append(this.poidsTotal()).append("\n");
        // afficher la valeur
        sac.append(" Prix total : ").append(this.valeurTotale()).append("\n");
        // afficher les objets
        sac.append(" Objets : \n");
        for(Objet o : listeObjets) {
            if(o.getEstStocke()==1) {
                sac.append("  - ").append(o).append("\n");
            }
        }
        return sac.toString();
    }
    //---------------------SAISIE-------------------
    /**
     * Methode pour verifier si la valeur saisie pour le poids est un float
     * @param s
     * @return
     */
    private static boolean isFloat(String s){
        try {
            Float f = Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static boolean verifMethode(String s){
        if(s.equalsIgnoreCase("gloutonne")||s.equalsIgnoreCase("dynamique")||s.equalsIgnoreCase("pse"))
            return true;

        return false;
    }

    /**
     * Méthode qui vérifie la saisie. Si le chemin est faux, le poids n'est pas un float,
     * ou le nom de la méthode n'existe pas alors return false sinon true.
     * @param saisie
     * @return boolean
     */
    private static boolean verifSaisie(String[] saisie){
        File f=new File(saisie[0]);
        if(!f.isFile())
            return false;
        if(!isFloat(saisie[1]))
            return false;
        if(!verifMethode(saisie[2]))
            return false;
        return true;
    }
    
    
    //-----------------------MAIN -----------------------------
    public static void main(String[] args) {
        Date dateDebut = new Date();
        int NB_ARG=3;
        String[] saisie;
        
        //tant que la saisie n'est pas correcte, demander le chemin, le poids max et la methode
        do {
            System.out.println("Ecrire : chemin poids_max methode (gloutonne, dynamique ou pse)");
            System.out.println("exemple : ./items.txt 10 pse");
            Scanner sc = new Scanner(System.in);
            saisie=new String[NB_ARG];
            for(int i=0; i<NB_ARG;i++) {
                saisie[i]= sc.next();
            }
        }
        while(!verifSaisie(saisie));

        try {
            SacADos sac=new SacADos(saisie[0],Float.parseFloat(saisie[1]));
            sac.resoudre(saisie[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //afficher la duree pour resoudre le sac
        Date dateFin = new Date();
        long duree = dateFin.getTime() - dateDebut.getTime();
        
        System.out.println("Durée pour résoudre le sac: " + TimeUnit.MILLISECONDS.convert(duree, TimeUnit.MILLISECONDS) + "ms");
    }

}
