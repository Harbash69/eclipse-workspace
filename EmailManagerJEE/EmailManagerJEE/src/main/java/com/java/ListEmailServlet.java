package com.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListEmailServlet
 */
@WebServlet("/ListEmailServlet")
public class ListEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Model> file= new ArrayList<Model>(); 
	private String path="C:\\Users\\TOPINFORAMTIQUE\\eclipse-workspace\\EmailManagerJEE\\EmailManagerJEE\\src\\main\\webapp\\email.txt";
	//Implimentation de la method Substring()
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEmailServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void init(ServletConfig config) throws ServletException{
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(path));
			file=(List<Model>) in.readObject() ; 
		}catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);            
		session.setAttribute("file", file);
		RequestDispatcher rd= request.getRequestDispatcher("/ListEmail.jsp"); 
	    rd.forward(request, response);	
	}



	//Implimentation de la methode Save()
		private void Save(List<Model> adresse) throws IOException
		{
			ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path));
			out.writeObject(adresse);
			out.close();
		}
		
	private void Subscribe(Model adresse)
	{
		
		file.add(adresse);
		
		try{
			Save(file);
			}
		catch(Exception e) 
		{e.printStackTrace();
		}
	}
	
	//Implimentation de la methode Unsubscribe() 
	
	
	int Unsubscribe(Model adresse) throws IOException, ServletException{
		if(file.contains(adresse))
		{
			file.remove(adresse);
			Save(file);
			return 1;
			
		}
		else
			return 0;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model adresse = new Model((String)request.getParameter("adrs")) ;
		String taper=(String)request.getParameter("button");
		String etat = "";
		Model vide= new Model("");
		if(adresse.equals(vide))response.sendError(HttpServletResponse.SC_BAD_REQUEST, "champ email vide ");
	      else {
	      if(taper.compareTo("Subscribe")==0)
	      {
	      	  Subscribe(adresse);
	      	  etat="inscrite";
	  		
	      }
	      else {
	      	try
	      	{
	         int x=Unsubscribe(adresse);
	      	if(x==1) 
	      	{         
	      		etat="supprime�";
	    		

	      	}
	      	else 
	      response.sendError(HttpServletResponse.SC_BAD_REQUEST, " Adresse email n existe pas dans le fichier ");
	      	}
	      	catch(Exception e) {e.printStackTrace();}
	      }
	      
	      }
		HttpSession session = request.getSession(true);
		session.setAttribute("email", adresse);
		session.setAttribute("etat", etat);
		RequestDispatcher rd= request.getRequestDispatcher("add_del.jsp"); 
	      rd.forward(request, response);
		}
}
