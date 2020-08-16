package servlett;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Requirement
 */
@WebServlet("/Requirement")
public class Requirement extends HttpServlet {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	int res=0;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Requirement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank","root","1234"); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		
		String ptname=request.getParameter("ptname");  
		String plasma=request.getParameter("plasma");  
		String bloodgroups=request.getParameter("blood_group");  
		String bloodplate=request.getParameter("blood_platelets"); 
		String reqdate=request.getParameter("Requirement_date");
		String localitys=request.getParameter("locality");
		String pthospital=request.getParameter("Patient_hospital");	
		String ptphnum=request.getParameter("Contact_person_name");
		Long phns=Long.parseLong(ptphnum);
		String sDate1=reqdate;
		Date date1=null;;
		try {
			date1 = (Date) new SimpleDateFormat("MM/dd/yyyy").parse(sDate1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			String query="insert into requriment values(?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(query);
			
			ps.setString(1,ptname);
			ps.setString(2, plasma);
			ps.setString(3, bloodgroups);
			ps.setString(4, bloodplate);
			ps.setDate(5, date1);
			ps.setString(6, localitys);
			ps.setString(7, pthospital);
			ps.setLong(8, phns);
			
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
	String sql="select firstname,phn,gender,bloodgroup,locality from registration where bloodgroup=? and locality=?"; 
					
			ps=con.prepareStatement(sql);
			ps.setString(7,bloodgroups );
			ps.setString(8,localitys );
			rs=ps.executeQuery();
			pw.println("<html><body bgcolor='wheat'><center> ");
			if(rs.next()) {
				if(rs.getString(7).equals(Register.bloodgroup) && rs.getString(8).equals(Register.locality))
				{
					pw.println(Register.firstname+"--"+Register.phn+"----"+Register.gender+"---"+bloodgroups+"---"+localitys);
					//request.getRequestDispatcher("/result.html").forward(request, response);
				}
				else
				{
					pw.print(" NO STOCK AVAILABLE");
				}				
			}
			pw.println("</center></body></html>");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
