package EJBLOCAL;

import java.util.List;

import javax.ejb.Local;

import model.Commande;

@Local
public interface CommandeDaoLocal {
	
	
	 public Commande update(final Commande t) ;
	 public void delete(final int id);
	 public Commande create (Commande t);
	 public Commande getCommandeInfo(int idCom);
	 public List<Commande> getMyCommands(int id);
	 public List<Commande> getListOffers(int idPR);
}
