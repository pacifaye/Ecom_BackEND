package Service;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import EJBLOCAL.Connection;
import EJBLOCAL.ProduitDao;
import model.Utilisateur;


/**
 * Servlet implementation class connect
 */
@WebServlet("/connect")
public class connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 
    public connect() {  super(); }
 
    
    @EJB
	private Connection data;
    @EJB
	private ProduitDao dataV;
    
    
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");
         
		JSONObject obj = new JSONObject();
	 
		//String Login = request.getParameter("login");
		//String password = request.getParameter("mdp");
	 
		 
		String Login = "amine@gmail.com";
		String password = "admin";
				
		boolean checking = data.isAuthGood(Login,password);
		if(checking){
			HttpSession UserSession = request.getSession();
			Utilisateur u =  data.getUserInfo(Login);
			
			obj.put("Auth","true");	
			obj.put("idus", u.getIdus());
			obj.put("name", u.getName());
			obj.put("fname", u.getFname());
			obj.put("phone", u.getPhone());
			obj.put("subscribdate", u.getSavedate());
			
			// Sauvegarder dans la session toutes les infos relatives à l utilisateur courant
			UserSession.setAttribute("UserInfo", u);
			UserSession.setAttribute("UserName", u.getName());
			UserSession.setAttribute("UserFname", u.getFname());
			UserSession.setAttribute("UserProducts", dataV.getProductsByUser(u.getIdus()));
		  
			response.getWriter().append(obj.toString());	
		}else{
			obj.put("Auth","false");
			obj.put("idus", null);
			obj.put("name", null);
			obj.put("fname", null);
			obj.put("phone", null);
			obj.put("subscribdate", null);
			
			response.getWriter().append(obj.toString());
		}
		
		
	}
 
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
