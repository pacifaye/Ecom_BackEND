package EJBLOCAL;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import model.Commande;
import model.Produit;

/**
 * Session Bean implementation class CommandeDao
 */
@Stateless
@LocalBean
public class CommandeDao implements CommandeDaoLocal {
	   @PersistenceContext(unitName = "Myecommerce")
	   	private EntityManager em;
	
    public CommandeDao() { }
       
    @Override
    @Transactional 
    public Commande update(final Commande t) {
        return this.em.merge(t);
    }
    
    
    @Override
    @Transactional 
    public void delete(final int id) {
    	Commande c = em.find(Commande.class, id);
		if(c!= null){
			System.out.println("Commande à supprimer trouvé");
			// Proceder à la suppression de la commande
			em.remove(c);
		}
    }
    
    @Override
    @Transactional 
    public Commande create (Commande t) {
        this.em.persist(t);
        return t;
    }


	@Override
	@Transactional
	public Commande getCommandeInfo(int idCom) {
		return em.find(Commande.class, idCom);
	}
	
	@Override
	public List<Commande> getMyCommands(int id) {
		List<Commande> commands = null;
 		String sql = "SELECT u FROM Commande u WHERE u.idus="+id+"";
 		Query query = this.em.createQuery(sql);	 
 		
 	    commands = query.getResultList();
 		 
 		 
		return commands;
	}

	public List<Commande> getListOffers(int idPR) {
		List<Commande> commands = null;
 		String sql = "SELECT u FROM Commande u WHERE u.idpr="+idPR+"";
 		Query query = this.em.createQuery(sql);	 
 		
 	    commands = query.getResultList();
 		 
 		 
		return commands;
	}
    
    
    
    
    
}
