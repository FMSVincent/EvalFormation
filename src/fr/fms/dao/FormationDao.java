/**
 * Composant d'accès aux données de la table T_Formations dans la base de données Formation
 * 
 */

package fr.fms.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.Formation;

public class FormationDao implements Dao<Formation> {

	/**
	 * Méthode qui crée une Formation en base sans prendre en compte l'id (généré automatiquement)
	 * @param Formation à ajouter dans la table des formations
	 */
	@Override
	public boolean create(Formation obj) {
		String str = "INSERT INTO T_Formations (Name, Duration, Description, IsRemote, UnitaryPrice, IdCategory) VALUES (?,?,?,?,?,?);";	
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getDuration());
			ps.setString(3, obj.getDescription());	
			ps.setBoolean(4, obj.getIsRemote());
			ps.setFloat(5, obj.getUnitaryPrice());
			ps.setInt(6, obj.getIdCategory());
			if( ps.executeUpdate() == 1)	return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la création d'une formation " + e.getMessage());
		} 	
		return false;
	}

	/**
	 * Méthode qui renvoi toutes les infos d'une formation à partir de son id s'il existe dans la table T_Formations
	 * @param id de la formation 
	 * @return formation si trouvé, null sinon
	 */
	@Override
	public Formation read(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Formations where IdFormation=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) return new Formation(rs.getInt(1) , rs.getString(2) , rs.getInt(3) , rs.getString(4),  rs.getBoolean(5), rs.getFloat(6), rs.getInt(7));
		} catch (SQLException e) {
			logger.severe("pb sql sur la lecture d'une formation " + e.getMessage());
		} 	
		return null;
	}

	/**
	 * Méthode qui met à jour une formation s'il existe (à partir de son id) dans la table T_Formations
	 * @param formation concerné
	 * @return vrai si trouvé, faux sinon
	 */
	@Override
	public boolean update(Formation obj) {
		String str = "UPDATE T_Formations set Name=? , Duration=? , Description=? , IsRemote=? ,  UnitaryPrice=? , IdCategory=? where idFormation=?;";
		try (PreparedStatement ps = connection.prepareStatement(str)){				
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getDuration());
			ps.setString(3, obj.getDescription());
			ps.setBoolean(4, obj.getIsRemote());
			ps.setFloat(5, obj.getUnitaryPrice());
			ps.setInt(6, obj.getIdCategory());
			if( ps.executeUpdate() == 1)	return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la mise à jour d'une formation " + e.getMessage());
		} 	
		return false;
	}

	/**
	 * Méthode qui supprime une formation à partir de son id (s'il existe) dans la table T_Formations
	 * @param formation concerné
	 * @return vrai si suppression ok faux sinon
	 */
	@Override
	public boolean delete(Formation obj) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Formations where IdFormation=" + obj.getIdFormation() + ";";									
			statement.executeUpdate(str);		
			return true;
		} catch (SQLException e) {
			logger.severe("pb sql sur la suppression d'une Formation " + e.getMessage());
		} 	
		return false;
	}
	
	/**
	 * Méthode qui renvoi toutes les formations de la table T_Formations
	 * @return liste formations
	 */
	@Override
	public ArrayList<Formation> readAll() {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		String strSql = "SELECT * FROM T_Formations";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int rsId = resultSet.getInt(1);	
					String rsName = resultSet.getString(2);
					int rsDuration = resultSet.getInt(3);
					String rsDescription = resultSet.getString(4);
					boolean rsIsRemote = resultSet.getBoolean(5);
					float rsPrice = resultSet.getFloat(6);
					int rsCategory = resultSet.getInt(7);
					formations.add((new Formation(rsId, rsName, rsDuration, rsDescription,  rsIsRemote, rsPrice, rsCategory)));		
				}	
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur revoi de toutes les formations " + e.getMessage());
		}	
		return formations;
	}
	
	/**
	 * Méthode qui renvoi toutes les formations d'une catégorie
	 * @param id de la catégorie
	 * @return liste formations
	 */
	public ArrayList<Formation> readAllByCat(int id) {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		String strSql = "SELECT * FROM T_Formations where idCategory=" + id;		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int rsId = resultSet.getInt(1);	
					String rsName = resultSet.getString(2);
					int rsDuration = resultSet.getInt(3);
					String rsDescription = resultSet.getString(4);
					boolean rsIsRemote = resultSet.getBoolean(5);
					float rsPrice = resultSet.getFloat(6);
					int rsCategory = resultSet.getInt(7);
					formations.add((new Formation(rsId, rsName, rsDuration, rsDescription,  rsIsRemote, rsPrice, rsCategory)));				
				}	
			}
		} catch (SQLException e) {
			logger.severe("pb sql sur renvoi des formations d'une catégorie " + e.getMessage());
		}			
		return formations;
	}
	
	public ArrayList<Formation> readByKeyWord(String keyWord) {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		String str = "SELECT * FROM T_Formations WHERE NAME LIKE ?";
		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ps.setString(1, "%" + keyWord + "%");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int rsId = resultSet.getInt(1);	
				String rsName = resultSet.getString(2);
				int rsDuration = resultSet.getInt(3);
				String rsDescription = resultSet.getString(4);
				boolean rsIsRemote = resultSet.getBoolean(5);
				float rsPrice = resultSet.getFloat(6);
				int rsCategory = resultSet.getInt(7);
				formations.add((new Formation(rsId, rsName, rsDuration, rsDescription,  rsIsRemote, rsPrice, rsCategory)));				
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formations;
	}
	
	public ArrayList<Formation> readIsRemote(int id) {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		String str = "SELECT * FROM T_Formations WHERE IsRemote="+id;
		try (PreparedStatement ps = connection.prepareStatement(str)) {
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int rsId = resultSet.getInt(1);	
				String rsName = resultSet.getString(2);
				int rsDuration = resultSet.getInt(3);
				String rsDescription = resultSet.getString(4);
				boolean rsIsRemote = resultSet.getBoolean(5);
				float rsPrice = resultSet.getFloat(6);
				int rsCategory = resultSet.getInt(7);
				formations.add((new Formation(rsId, rsName, rsDuration, rsDescription,  rsIsRemote, rsPrice, rsCategory)));				
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formations;
	}
}
