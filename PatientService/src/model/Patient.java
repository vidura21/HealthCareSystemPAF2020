package model;
import java.sql.*;

public class Patient {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/patient", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
		
		public String insertPatient(String name, String address, String phoneNO, String year, String email)
		
		{
			
			String output = ""; 
			
			try {
				
				Connection con = connect();
				
				if(con == null)
				
				{return "Error while connecting to the database for inserting."; }
				 
				 String query = " insert into patient (`pid`,`name`,`address`,`phoneNO`,`year`,`email`)"
						 		+ " values (?, ?, ?, ?, ?, ?)";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, name);
				 preparedStmt.setString(3, address);
				 preparedStmt.setString(4, phoneNO);
				 preparedStmt.setString(5, year); 
				 preparedStmt.setString(5, email); 
				 preparedStmt.execute();
				 con.close(); 
				 
				 output = "Inserted successfully";
			}
			 catch (Exception e)
			 {
			 output = "Error while inserting the patient.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
		public String readpatient() 
		{
			String output = "";
			try {
				Connection con = connect();
				if(con == null)
				{return "Error while connecting to the database for reading."; }
				
				output =  "<table border=\"1\">"
						+ "<tr>"
						+ "<th>Name</th>"
						+ "<th>Adress</th>"
						+ "<th>Phone number</th>"
						+ "<th>Year</th>"
						+ "<th>Email</th>"
						+ "<th>Update</th>"
						+ "<th>Remove</th>"
						+ "</tr>"; 
				String query = "select * from patient";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while(rs.next())
				{
					
					String pid = Integer.toString(rs.getInt("pid"));
					String name = rs.getString("name");
					String address = rs.getString("address");
					String phoneNO = rs.getString("phoneNO");
					String year = rs.getString("year");
					String email = rs.getString("email");
					
					output += "<tr><td>" + name + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + phoneNO + "</td>";
					output += "<td>" + year + "</td>";
					output += "<td>" + email + "</td>";
					 
					// buttons
					 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
					+ "<td><form method=\"post\" action=\"patient.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\""
					 		+ "class=\"btn btn-danger\">"
							 + "<input name=\"pid\" type=\"hidden\" value=\"" + pid	
					 + "\">" + "</form></td></tr>";
					 } 
					
					con.close();
					
					output += "</table>";
			}
			 catch (Exception e)
			 {
			 output = "Error while reading the patient.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 } 
			
		public String updatepatient(String pid, String name, String address, String phoneNO, String year, String email)
		 {
		 String output = "";
		 try	
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 
		 String query = "UPDATE patient SET name=?,address=?,phoneNO=?,year=?,email=? WHERE pid=?";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 preparedStmt.setString(1, name);
		 preparedStmt.setString(2, address);
		 preparedStmt.setString(3, phoneNO);
		 preparedStmt.setString(4, year);
		 preparedStmt.setString(5, email);
		 preparedStmt.setInt(6, Integer.parseInt(pid));
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the patient.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		public String deletepatient(String pid)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from patient where pid=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(pid)); 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the patient.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }		
}
