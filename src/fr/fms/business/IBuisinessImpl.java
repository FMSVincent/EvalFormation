/**
 * @author El babili - 2024
 * 
 */

package fr.fms.business;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.FormationDao;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
import fr.fms.entities.Formation;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;

public class IBuisinessImpl implements IBusiness {	
	private HashMap<Integer,Formation> cart;
	private Dao<Formation> formationDao = DaoFactory.getFormationDao();
	private Dao<Order> orderDao = DaoFactory.getOrderDao();
	private Dao<OrderItem> orderItemDao = DaoFactory.getOrderItemDao();
	private Dao<Customer> customerDao = DaoFactory.getCustomerDao();
	private Dao<Category> categoryDao = DaoFactory.getCategoryDao();
	
	public IBuisinessImpl() {
		this.cart = new HashMap<Integer,Formation>();
	}

	@Override
	public void addToCart(Formation formation) {
		cart.put(formation.getIdFormation(), formation);
	}

	@Override
	public void rmFromCart(int id) {
		cart.remove(id);
		}				

	@Override
	public ArrayList<Formation> getCart() {
		return new ArrayList<Formation> (cart.values());
	}

	@Override
	public int order(int idCustomer) {	
		if(customerDao.read(idCustomer) != null) {
			double total = getTotal(); 
			Order order = new Order(total, new Date(), idCustomer);
			if(orderDao.create(order)) {	
				for(Formation formation : cart.values()) {	
					orderItemDao.create(new OrderItem(0, formation.getIdFormation(), formation.getUnitaryPrice(), order.getIdOrder()));
				}
				return order.getIdOrder();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Formation> readFormations() {
		return formationDao.readAll();
	}

	@Override
	public Formation readOneFormation(int id) {
		return formationDao.read(id);
	}

	@Override
	public ArrayList<Formation> readFormationByCatId(int id) {
		return ((FormationDao) formationDao).readAllByCat(id);
	}
	
	@Override
	public ArrayList<Category> readCategories() {
		return categoryDao.readAll();
	}

	/**
	 * renvoi le total de la commande en cours
	 * @return total
	 */
	public double getTotal() {
		double [] total = {0};
		cart.values().forEach((a) -> total[0] += a.getUnitaryPrice()); 	
		return total[0];
	}

	public boolean isCartEmpty() {
		return cart.isEmpty();
	}
	
	public void clearCart() {
		cart.clear();		
	}
	
	public Category readOneCategory(int id) {
		return categoryDao.read(id);
	}

	public ArrayList<Formation> getFormationByKeyWord(String keyWord) {
		ArrayList<Formation> getFormations = ((FormationDao) formationDao).readByKeyWord(keyWord);
		return getFormations;
	}
	
	public ArrayList<Formation> readIsRemote(int id) {
		ArrayList<Formation> getFormations = ((FormationDao) formationDao).readIsRemote(id);
		return getFormations;
	}
}
