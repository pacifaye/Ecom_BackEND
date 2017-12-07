package Service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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
 * Servlet implementation class commande
 */
@WebServlet("/commande")
public class commande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public commande() { super(); }
    @EJB
   	private CommandeDao data;
    
    
	 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
	 		response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
	        response.setCharacterEncoding("utf-8");
	         
			JSONObject obj = new JSONObject();
			int current_user_id;
			Utilisateur User;
			
			// Faire une soumission/commande sur un produit
			User = (Utilisateur) request.getSession().getAttribute("UserInfo");
			current_user_id = User.getIdus();
				
			int idProd = Integer.parseInt(request.getParameter("idProd"));
			int price = Integer.parseInt(request.getParameter("prix"));
					
			Commande com1 = new Commande(current_user_id, idProd, Date.valueOf(LocalDate.now()), price, 0);
			data.create(com1);
					
			// cree minimum 2 utilisateurs (1 vendeur et un acheteur pour faire tes tests)
			// Test Navigateur -> http://127.0.0.1:8080/JPAEJB/commande?idProd=91&prix=40
						
				 	 
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	 }

}
