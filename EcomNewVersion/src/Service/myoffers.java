package Service;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Commande;
import model.Produit;
import model.Utilisateur;

import org.json.simple.JSONObject;

import EJBLOCAL.CommandeDao;
import EJBLOCAL.ProduitDao;

/**
 * Servlet implementation class myoffers
 */
@WebServlet("/myoffers")
public class myoffers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	@EJB
	private CommandeDao data;
	
	@EJB
	private ProduitDao dataP;
	
	
	
    public myoffers() {super();}
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");
         
		JSONObject obj = new JSONObject();
		int current_user_id;
		Utilisateur User;
		int choice =0;
		// 0> getListOffers
		// 1> DecideOnOffer 
		// 2> getOfferInfo
		
		if(request.getParameter("choice") != null) { choice = Integer.parseInt(request.getParameter("choice")) ; }
		
		switch( choice ) {
			case(0):
				// Recevoir liste de toutes les Offres recues sur ses produits
				User = (Utilisateur) request.getSession().getAttribute("UserInfo");
		    	current_user_id = User.getIdus();
		    	int index = 1;
		    	
		    	// Charger tous nos produits en vente
		    	List<Produit> products = dataP.getProductsByUser(current_user_id);
		    	
		    	for(Produit prod : products ) {
		    		// Pour chaque produit, charger les commandes qui le concerne avec IDPR
			    	List<Commande> commands = data.getListOffers(prod.getIdpr());
			    	// Et enfin mettre dans Json le resultat de chacun de ces commandes
					for(Commande com : commands ) {
						obj.put(index, com.toJson() );	
						index++; 	
					}
		    	}
				response.getWriter().append(obj.toString());
				
				// Test Navigateur : http://127.0.0.1:8080/JPAEJB/myoffers
		    	
			break;
		
			case(1):
				// Prendre une decision sur une offre donnée
			    User = (Utilisateur) request.getSession().getAttribute("UserInfo");
			    current_user_id = User.getIdus();
			
				int idCom = Integer.parseInt(request.getParameter("idCom"));
				int state = Integer.parseInt(request.getParameter("state")); 
				
				Commande com1 = data.getCommandeInfo(idCom);
				// Donner sa décision (1 = accepté et 2 = refusé)
				if(state == 1 || state == 2)
					com1.setState(state);
				
				//Mettre à jour au niveau de la base
					data.update(com1);
			
				// Test Navigateur : http://127.0.0.1:8080/JPAEJB/myoffers?choice=1&idCom=2&state=1
				
			break;
			
			case(2):
				//getInfo sur une offre
			    User = (Utilisateur) request.getSession().getAttribute("UserInfo");
			    current_user_id = User.getIdus();
			    
			    int idCom2 = Integer.parseInt(request.getParameter("idCom"));
				
			    //recevoir les données depuis la base
			    Commande c = data.getCommandeInfo(idCom2);

				if( c !=null  ) {    
					obj.put("idcm", c.getIdcm());
					obj.put("idus", c.getIdus());
					obj.put("idpr", c.getIdpr());
					obj.put("date", c.getDate());
					obj.put("price", c.getPrice());
					obj.put("state", c.getState());
				}else{
					obj.put("idcm", null);
					obj.put("idus", null);
					obj.put("idpr", null);
					obj.put("date", null);
					obj.put("price", null);
					obj.put("state", null);
				}
			
				response.getWriter().append(obj.toString());	
			
				// Test Navigateur -> http://127.0.0.1:8080/JPAEJB/myoffers?choice=2&idCom=2
				
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
