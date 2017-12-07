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

import model.Produit;

import org.json.simple.JSONObject;

import EJBLOCAL.ProduitDao;
import EJBLOCAL.UtilisateurDao;
import model.Utilisateur;

/**
 * Servlet implementation class users
 */
@WebServlet("/users")
public class users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public users() {
        super();
        // TODO Auto-generated constructor stub
    }

    @EJB
   	private UtilisateurDao data;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");
		
        JSONObject obj = new JSONObject();
		int choice =0;
		
		// Begin 
		if(request.getParameter("choice") != null) { choice = Integer.parseInt(request.getParameter("choice")) ; }
		/*
		 * Choice > 1 : Subscription
		 * Choice > 2 : Load UserInfo */
		
		switch( choice ) {
			case(1) : 
				// Inscription
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				int phone = Integer.parseInt(request.getParameter("phone"));
				String email = request.getParameter("email");
				String password = request.getParameter("mdp");	
				
				/* Stockage des infos recuperées dans le bean d' inscription */
				Utilisateur user = new Utilisateur(nom,prenom,email,phone,Date.valueOf(LocalDate.now()),password);
				data.setUserInfo(user);
				
				/* Effectuer la requête d insertion au niveau de la base */
				if(user != null){
					System.out.println("user not null !!!");
					data.SubscribeRequest(user);
				}
				
				// Test Navigateur : 127.0.0.1:8080/JPAEJB/users?choice=1&nom=Faye&prenom=Pacy&email=pacifaye@yahoo.fr&phone=0784657&mdp=azerty		
			break;
			case(2) :
				// Charger les infos d'un utilisateur donné
				
				int idUser = Integer.parseInt(request.getParameter("IdUser"));
				Utilisateur UserInf = data.getUserInfo(idUser);
				
				if(UserInf != null){
					obj.put("Idus", UserInf.getIdus());
					obj.put("Fname", UserInf.getFname());
					obj.put("Name", UserInf.getName());
					obj.put("Email", UserInf.getEmail());
					obj.put("Phone", UserInf.getPhone());
					obj.put("SubsDate", UserInf.getSubscribdate());
					obj.put("UserState", UserInf.getState());
				}else{
					obj.put("Idus", null);
					obj.put("Fname", null);
					obj.put("Name", null);
					obj.put("Email", null);
					obj.put("Phone", null);
					obj.put("SubsDate", null);
					obj.put("UserState", null);
				}
				
				response.getWriter().append(obj.toString());
					
			//Test Navigateur->	http://127.0.0.1:8080/JPAEJB/users?choice=2&IdUser=1994	
			break;
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
