package EJBLOCAL;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Utilisateur;

/**
 * Session Bean implementation class UtilisateurDao
 */
@Stateless
@LocalBean
public class UtilisateurDao implements UtilisateurDaoLocal {

	private Utilisateur UserInfo;
	// Gestion de la persistance des données
			@PersistenceContext(unitName="Myecommerce")
			 private EntityManager EM; 

	
    public UtilisateurDao() {}
    
    @Override
	public void setUserInfo(Utilisateur u) {
		this.UserInfo = u;
		
	}

	@Override
	public void SubscribeRequest(Utilisateur u) {
		// Requete d' insert au niveau de la base
			String s = "'"+u.getName()+"','"+u.getFname()+"','"+u.getEmail()+"',"+u.getPhone()+",'"+u.getSubscribdate()+"',"+u.getState()+",'"+u.getPassword()+"'";
			String sql = "INSERT INTO Utilisateur (NAME,FNAME,EMAIL,PHONE,SUSCRIBDATE,STATE,PASSWORD)VALUES("+s+");";
			Query query = EM.createNativeQuery(sql);
			query.executeUpdate();
				
		// Directement utiliser la methode persistance du Entity Manager
		//EM.persist(UserInfo);
		
	}
	
	@Override
	public Utilisateur getUserInfo(int idUser){
		return EM.find(Utilisateur.class, idUser);
	}

}
