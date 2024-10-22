package fr.fms;

import java.sql.Connection;

import fr.fms.dao.BddConnection;

public class FormationApp {

	public static void main(String[] args) {

		Connection test = BddConnection.getConnection();
		
		System.out.println(test);
		
	}

}
