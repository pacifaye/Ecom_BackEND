package EJBLOCAL;

import javax.ejb.Local;

import model.Commande;
import model.Utilisateur;

@Local
public interface UtilisateurDaoLocal {

	public void setUserInfo(Utilisateur u);
	public void SubscribeRequest(Utilisateur u);
	public Utilisateur getUserInfo(int idUser);
	
}
