package Service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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

/**
 * Servlet implementation class myshop
 */
@WebServlet("/myshop")
public class myshop extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 @EJB
	private CommandeDao data;
	
    public myshop() { super();  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");
         
		JSONObject obj = new JSONObject();
		int current_user_id;
		Utilisateur User;
		int choice =0;
		// 0> getListCommande
		// 1> update 
		// 2> delete 
		// 3> getcommandInfo 
		
		if(request.getParameter("choice") != null) { choice = Integer.parseInt(request.getParameter("choice")) ; }
		
		switch( choice ) {
			case(0):
				// Recevoir liste de toutes les commandes/soumissions faites
				User = (Utilisateur) request.getSession().getAttribute("UserInfo");
		    	current_user_id = User.getIdus();
		    	List<Commande> commands = data.getMyCommands(current_user_id);
		    	
		    	int i=1; 
				for(Commande com : commands ) {
					obj.put(i, com.toJson() );	
					i++; 	
				}
				
				response.getWriter().append(obj.toString());
				
				// Test Navigateur : http://127.0.0.1:8080/JPAEJB/myshop
		    	
			break;
		
			case(1):
				// Modifier sa soumission/commande sur un produit
			    User = (Utilisateur) request.getSession().getAttribute("UserInfo");
			    current_user_id = User.getIdus();
			
				int idCom = Integer.parseInt(request.getParameter("idCom"));
				int price2 = Integer.parseInt(request.getParameter("prix"));
				
				Commande com2 = data.getCommandeInfo(idCom);
				// Modifier le prix de sa soumission
				com2.setPrice(price2);
				//Mettre à jour au niveau de la base s il s agit du meme user qui l a passé
				if(com2.getIdus()== current_user_id)
					data.update(com2);
			
				//Test Navigateur -> http://127.0.0.1:8080/JPAEJB/myshop?choice=1&idCom=2&prix=35
				
			break;
			
			case(2):
				//Supprimer sa soumission
			    User = (Utilisateur) request.getSession().getAttribute("UserInfo");
			    current_user_id = User.getIdus();
			    
			    int idCom2 = Integer.parseInt(request.getParameter("idCom"));
				
			    //Supprimer au niveau de la base
			    data.delete(idCom2);
			    
			    // Test Navigateur : http://127.0.0.1:8080/JPAEJB/myshop?choice=2&idCom=3
				
			break;
			
			case(3):
				//getInfo sur une soumission
			    User = (Utilisateur) request.getSession().getAttribute("UserInfo");
			    current_user_id = User.getIdus();
			    
			    int idCom3 = Integer.parseInt(request.getParameter("idCom"));
				
			    //recevoir les données depuis la base
			    Commande c = data.getCommandeInfo(idCom3);

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
			
				// Test Navigateur -> http://127.0.0.1:8080/JPAEJB/myshop?choice=3&idCom=4
			break; 
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
