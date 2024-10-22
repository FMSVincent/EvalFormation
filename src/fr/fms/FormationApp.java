package fr.fms;

import java.util.Scanner;

import fr.fms.business.IBuisinessImpl;

public class FormationApp {

	private static Scanner scan = new Scanner(System.in); 
	private static IBuisinessImpl business = new IBuisinessImpl();
	
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	private static final String COLUMN_DURATION = "DUREE EN JOUR";
	private static final String COLUMN_PRICE = "PRIX";
	private static final String COLUMN_NAME = "NAME";
	
	private static int idUser = 0;
	private static String login = null; 
	
	public static void main(String[] args) {

		displayFormations();
		
	}
	
	/**
	 * Méthode qui affiche toutes les formations en base en centrant le texte 
	 */
	public static void displayFormations() { 				
		System.out.printf("-----------------------------------------------------------------------------------------%n");
		System.out.printf("%-20s | %-20s | %-40s | %-20s %n",COLUMN_NAME,COLUMN_DURATION,COLUMN_DESCRIPTION,COLUMN_PRICE);
		System.out.printf("-----------------------------------------------------------------------------------------%n");
		business.readFormations().forEach( a -> System.out.printf("%-20s | %-20s | %-40s | %-20s%n",a.getName(), a.getDuration(), a.getDescription(), a.getUnitaryPrice()));
	}

}
