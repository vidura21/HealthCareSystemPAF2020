package model;
import java.sql.*;

public class Appointment {
	private Connection connect()
	{
		Connection con  = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appoint","root", "");
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return con;
	}
	public String insertAppointment(String ID,String code ,String name,String Desc) {
			String output="";
			try {
				Connection con =connect();
				if(con == null)
				{return "Error while connecting to the database for inserting. ";}
				String query = "insert into appointments ('appointmentID','appointmentCode','appointmentName','appointmentDesc')"+"values(?,?,?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				preparedStmt.setInt(0,1);
				preparedStmt.setString(2,code);
				preparedStmt.setString(3,name);
				preparedStmt.setString(4,Desc);
				preparedStmt.execute();
				con.close();
				
				output = "inserted successfully";
				
					
				}
			catch(Exception e) {
				output = "Error while inserting items.";
				System.err.println(e.getMessage());
			}
			return output;
	}
	
	
	public String readAppointment() {
		String output="";
		try {
			Connection con = connect();
			if(con == null) 
				{return "Error while connecting to the database for reading";}
				output = "<table border=\"1\"><tr><th>AppointmentID</th><th>AppointmentCode</th><th>Appointment Name</th><th>Appointment Desc </th><th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from appointments";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					String appointmentID = Integer.toString(rs.getInt("appointmentID"));
					String appointmentCode = rs.getString("appointmentCode");
					String appointmentName = rs.getString("appointmentName");
					String appointmentDesc = rs.getString("appointmentDesc");
					
					output += "<tr><td>"+appointmentID+"</td>";
					output += "<tr><td>"+appointmentCode+"</td>";
					output += "<tr><td>"+appointmentName+"</td>";
					output += "<tr><td>"+appointmentDesc+"</td>";
								
				
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"doctors.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
							+ "<input name=\"operID\" type=\"hidden\" value=\"" + appointmentID + "\">" + "</form></td></tr>";
					
				}		
				con.close();
				output +="</table>";
			
			}
			
			catch(Exception e) {
				output ="Error while reading items.";
				System.err.println(e.getMessage());
			}
			return output;
	}
public String updateAppointment(String ID,String code,String name,String desc) {
	String output ="";
	
	try {
		Connection con = connect();
		if(con == null)
		{return "Error while connect to the database for updating.";}
		String query = "UPDATE Appointments SET appointmentID=?,appointmentCode=?,appointmentName=?,appointmentDesc=?"
				+ "WHERE appointmentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		preparedStmt.setString(1, ID);
		preparedStmt.setString(1, code);
		preparedStmt.setString(2,name);
		preparedStmt.setString(3,desc);
		
		preparedStmt.execute();
		con.close();
		
		output = "Update successfully";
	}
	catch(Exception e) {
		output = "Error while updating the item.";
	}
	return output;
}
public String deleteAppointment(String appointmentID)
{
	String output ="";
	try {
		Connection con = connect();
		if(con == null)
		{return "Error while connecting to the database for deleting .";}
		String query ="delete from appointments where appointmentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,Integer.parseInt(appointmentID));
		
		preparedStmt.execute();
		con.close();
		
		output = "deleted Successfully";
	}
	catch(Exception e) {
		output = "Error while deleting the item.";
		System.err.println(e.getMessage());
	}
	return output;
}


}
	

		
	

	

		
		
	


