package Service;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import EJBLOCAL.Connection;
import EJBLOCAL.ProduitDao;
import model.Utilisateur;
import model.Formation;
import model.Produit;

/**
 * Servlet implementation class product
 */
@WebServlet("/product")
public class product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public product() {  super(); }

    
    @EJB
	private ProduitDao data;
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");
         
		JSONObject obj = new JSONObject();
		int current_user_id;
		
		Produit produit; 
		int choice =0; 
		int ID = 0; 
		// 0> getListProdOfUser
		// 1> create 
		// 2> update 
		// 3> delete 
		// 4> getInfo 
		// 5> getlist
		
		if(request.getParameter("choice") != null) { choice = Integer.parseInt(request.getParameter("choice")) ; }
		
		switch( choice ) {
		
		case(0) : 
			// Afficher tous les produits de l'utilisateur courant s il en a
			Utilisateur User0 = (Utilisateur) request.getSession().getAttribute("UserInfo");
		    current_user_id = User0.getIdus();
		    
		    List<Produit> p = data.getProductsByUser(current_user_id);
		    
		    int j=1; 
			for(Produit Pr : p ) {
				obj.put(j, Pr.toJson() );	
				j++; 	
			}
			
			response.getWriter().append(obj.toString());
			
			// Test Navigateur -> http://127.0.0.1:8080/JPAEJB/product
			
		break;
		
		case(1) : 
			//Creation d un nouveau produit
			Utilisateur User = (Utilisateur) request.getSession().getAttribute("UserInfo");
		    current_user_id = User.getIdus();
			
			String titre = request.getParameter("title");
			String description = request.getParameter("desc");
			int prix_min = Integer.parseInt(request.getParameter("Pmin"));
			int prix_max = Integer.parseInt(request.getParameter("Pmax"));
			int code_postal = Integer.parseInt(request.getParameter("BP"));
			String categorie = request.getParameter("categorie");
			
			Produit prod = new Produit(current_user_id,titre,description,"none",prix_min,prix_max,code_postal);
			data.create(prod);
			
			// Mettre à jour la variable de session UserProduct pour prendre en compte le nouvel ajout
			request.getSession().setAttribute("UserProducts", data.getProductsByUser(current_user_id));
	
			// Test Navigateur : 127.0.0.1:8080/JPAEJB/product?choice=1&title=Merry&desc=kjkjkj&Pmin=23&Pmax=50&BP=38000		
			break; 
			
		case(2) : 
			// Mise a jour d une produit
			
			
			Utilisateur User2 = (Utilisateur) request.getSession().getAttribute("UserInfo");
			current_user_id = User2.getIdus();
			int idProd = Integer.parseInt(request.getParameter("IDProd"));
			Produit current_product = data.getProductInfo(idProd);
			String titreMAJ = request.getParameter("title");
			String descriptionMAJ = request.getParameter("desc");
			String don = request.getParameter("don");
			Date expire_date = Date.valueOf(LocalDate.now());
			
			// Gestion de la date
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsed = null;
			try {
				 parsed = format.parse(request.getParameter("DateEXP"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(parsed != null){
				expire_date = new java.sql.Date(parsed.getTime());
			}
			else
				System.out.println("Expire date null ");
			
			// Gestion du Cas d une mise en don d un produit
			if(don !=null){
				if(don.equals("on")){
					current_product.setPriceMin(0);
					current_product.setPriceMax(0);		
				}
			}
			
			/* Stockage des infos recuperées au niveau de la base  */
			current_product.setTitle(titreMAJ);
			current_product.setDescription(descriptionMAJ);
			current_product.setExpirationdate(expire_date);
			data.update(current_product);
			// Mettre à jour la variable de session UserProduct pour prendre en compte le nouvel ajout
			request.getSession().setAttribute("UserProducts", data.getProductsByUser(current_user_id));
			
			// change Idprod a un id existant dans ta base sinon erreur lol		  
			// Test Navigateur : 127.0.0.1:8080/JPAEJB/product?choice=2&IDProd=89&title=Guili&desc=bof&don=&DateEXP=2017-10-28		    
			// Test Navigateur avec don activé : 127.0.0.1:8080/JPAEJB/product?choice=2&IDProd=89&title=Guili&desc=bof&don=on&DateEXP=2017-10-28			 
			break;
		
		case(3) : 
			// Supprimer un produit
			
			
			Utilisateur User3 = (Utilisateur) request.getSession().getAttribute("UserInfo");
			current_user_id = User3.getIdus();
			
			int idProdrmv = Integer.parseInt(request.getParameter("IDProd"));
			
			/* suppression des infos recuperées au niveau de la base  */
			data.delete(idProdrmv);
			
			// Mettre à jour la variable de session UserProduct pour prendre en compte le nouvel ajout
			request.getSession().setAttribute("UserProducts", data.getProductsByUser(current_user_id));
			
			// change Idprod a un id existant dans ta base sinon erreur lol		  
			// Test Navigateur : 127.0.0.1:8080/JPAEJB/product?choice=3&IDProd=89
			
			break;	
		case(4) : 
			// Recevoir les infos relatives à un produit (j ai rien touché le tien marche deja)
			
			 ID = Integer.parseInt(request.getParameter("ID")) ;
			 produit = data.getProductInfo(ID);
			
			if( produit !=null  ) {    
				obj.put("idus", produit.getIdus());
				obj.put("idpr", produit.getIdpr());
				obj.put("title", produit.getTitle());
				obj.put("description", produit.getDescription());
				obj.put("linkpicture", produit.getlinkpicture());
				obj.put("pricemin", produit.getPriceMin());
				obj.put("pricemax", produit.getPriceMax());
				obj.put("zipcode", produit.getZipcode());
				obj.put("expiration_date", produit.getExpirationdate());
				
			}else {
				obj.put("idus", null);
				obj.put("idpr", null);
				obj.put("title", null);
				obj.put("description", null);
				obj.put("linkpicture", null);
				obj.put("pricemin", null);
				obj.put("pricemax", null );
				obj.put("zipcode", null );
				obj.put("expiration_date", null);
			}
				
				response.getWriter().append(obj.toString());	
			
				//Test Navigateur http://127.0.0.1:8080/JPAEJB/product?choice=4&ID=88
				
			break;
			
		case(5) : 
			// Affichage de tous les produits (pas touché)
			
			List<Produit> produits = data.getProducts(); 
		
			int i=1; 
			for(Produit Pr : produits ) {
				obj.put(i, Pr.toJson() );	
				i++; 	
			}
			
			response.getWriter().append(obj.toString());	
		
		
			break;
		
		}	
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	doGet(request, response);
	}

}
