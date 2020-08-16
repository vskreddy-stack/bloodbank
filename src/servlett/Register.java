package servlett;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.io.*;
import com.mysql.jdbc.Driver;

import javax.servlet.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	Connection con=null;
	PreparedStatement ps =null;
	int res=0;
	public static String bloodgroup;
	public static String locality;
	public static String firstname;
	public static String phn;
	public static String gender;
	
	private static final long serialVersionUID = 102831973239L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	/*	try {
				Class.forName("com.mysql.jdbc.Driver");
			    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank","root","1234");  

		}catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response,String additionalparameter) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		 response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		
		String firstname=request.getParameter("first_name");  
		String lastname=request.getParameter("last_name");  
		String mailid=request.getParameter("Mailid");		
		String phone=request.getParameter("phone"); 
		String dob=request.getParameter("DOB");
		String gender=request.getParameter("gender");
		String bloodgroup=request.getParameter("blood_group");
		String locality=request.getParameter("locality");
		Long phn=Long.parseLong(phone);
		String sDate1=dob;
		Date date1=null;;
		try {
			date1 = (Date) new SimpleDateFormat("MM/dd/yyyy").parse(sDate1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		
		try {
			  
		    
			String query="insert into registration values(?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(query);
			
			ps.setString(1,firstname );
			ps.setString(2, lastname);
			ps.setString(3, mailid);
			ps.setLong(4, phn);
			ps.setDate(5, date1);
			ps.setString(6, gender);
			ps.setString(7, bloodgroup);
			ps.setString(8, locality);
			
			res=ps.executeUpdate();
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		if(res!=0)
		{
			request.getRequestDispatcher("/login.html").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("/register.html").include(request, response);
		}
		
		
		
		
		
	}

}
