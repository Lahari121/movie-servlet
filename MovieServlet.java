import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class MovieServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String sno1= req.getParameter("sno");
        String na = req.getParameter("name");
        String mv = req.getParameter("moviename");
        String ci = req.getParameter("cityname");
        String th = req.getParameter("theater");
        String ph = req.getParameter("phonenumber");
        String da = req.getParameter("moviedate");
        String ti = req.getParameter("movietime");
        String sn =req.getParameter("seatno");
        String sn1 =req.getParameter("seatno1");
        String sn2 =req.getParameter("seatno2");
        String sn3 =req.getParameter("seatno3");
        String sn4 =req.getParameter("seatno4");
        String sn5 =req.getParameter("seatno5");
        String sn6 =req.getParameter("seatno6");
       
        String pay= req.getParameter("payment");
        
        String update1=req.getParameter("p1");
        String update2=req.getParameter("p2");
        int flag=0;
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/details", "lahari", "lahari");
 
            String s2=req.getParameter("s1");
        
           if(s2.equals("bookmyticket"))
           {
            PreparedStatement ps = con
                    .prepareStatement("insert into movies values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, sno1);
            ps.setString(2, na);
            ps.setString(3, mv);
            ps.setString(4, ci);
            ps.setString(5, th);
            ps.setString(6, ph);
            ps.setString(7, da);
            ps.setString(8, ti);
            ps.setString(9,sn+sn1+sn2+sn3+sn4+sn5+sn6);
            ps.setString(10, pay);
            
            int i = ps.executeUpdate();
            if (i > 0)
           out.print("<h2>Successfully booked your ticket</h2>...");
           }
           else if(s2.equals("display"))
           {
        	  
           	PreparedStatement ps3 = con
                       .prepareStatement("select * from movies");
           	 ResultSet ps1 = ps3.executeQuery();
           	while(ps1.next())
           	{
           		if(ps1.getString(2).equals(na))
           		{
           		out.println("<h2>Hello&nbsp"+ps1.getString(2)+"&nbspWELCOME!!!");
           		out.println("<h3>Your Details:</h3>");
           		out.println("<h3>Name:"+ps1.getString(2)+"</h3>");
           		out.println("<h3>Movie Name:"+ps1.getString(3)+"</h3>");
           		out.println("<h3>City Name:"+ps1.getString(4)+"</h3>");
           		out.println("<h3>Theater:"+ps1.getString(5)+"</h3>");
           		out.println("<h3>Phone Number:"+ps1.getString(6)+"</h3>");
           		out.println("<h3>Date:"+ps1.getString(7)+"</h3>");
           		out.println("<h3>Time:"+ps1.getString(8)+"</h3>");
           		out.println("<h3>Seat Number:"+ps1.getString(9)+"</h3>");
           		out.println("<h3>Payment:"+ps1.getString(10)+"</h3>");
           		out.println("<h2>If any Upadations or Changes in your Booking click on Update</h3>");
           		 flag=1;
           		  break;
           		}
           		
           		
           	}
           		if(flag==0)
           		{
           			out.println("NOT FOUND!!!");
           		}
           		
           	
           
           	
           	
           	
           	
           	//Statement stmt=con.createStatement();
                       //ResultSet rs1 = stmt.executeQuery("select * from movie");
            //while(rs1.next())
           //{
           //out.println(rs1.getString(1)+""+rs1.getString(2)+""
           //+ rs1.getString(3)+""+rs1.getString(4)+""+rs1.getString(5)+""+rs1.getString(6)+""+rs1.getString(7)+""+rs1.getString(8+""+rs1.getString(9)+""+rs1.getString(10));
           //}

              } 
           
           else 
           {   	
        	  
        	   
           		String query = "update movies set moviedate='"+update1+"' where name='"+update2+"'";
           		Statement stmt = con.createStatement();
           		int j = stmt.executeUpdate(query);
           		//out.println("query" + query);
           		if(j>0) {
           			out.println("<h2> successfully updated</h2>");
           		}
             
           }
           
                   }catch (Exception e2) {
            System.out.println(e2);
        }
 
        out.close();
    }
}
