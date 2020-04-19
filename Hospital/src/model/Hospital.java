package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

}
	
	public String insertHospital(String Code, String Name, String Address, String PhoNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into hospitals(`HospitalID`,`HospitalCode`,`HospitalName`,`HospitalAddress`,`HospitalPhoNo`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Code);
			preparedStmt.setString(3, Name);
			preparedStmt.setString(4, Address);
			preparedStmt.setString(5, PhoNo);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting dataset.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readHospital()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>HospitalCode</th><th>HospitalName</th><th>HospitalAddress</th><th>HospitalPhoNo</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from hospitals";
	 Statement stmt = (Statement) con.createStatement();
	 ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String HospitalID = Integer.toString(rs.getInt("HospitalID"));
	 String HospitalCode = rs.getString("HospitalCode");
	 String HospitalName = rs.getString("HospitalName");
	 String HospitalAddress = rs.getString("HospitalAddress");
	 String HospitalPhoNo = Integer.toString(rs.getInt("HospitalPhoNo"));
	 
	 // Add into the html table
	 
	 
	 output += "<tr><td>" + HospitalCode + "</td>";
	 output += "<td>" + HospitalName + "</td>";
	 output += "<td>" + HospitalAddress + "</td>";
	 output += "<td>" + HospitalPhoNo + "</td>";
	
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
	 + "<input name=\"HospitalID\" type=\"hidden\" value=\"" + HospitalID
	 + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the Hospital.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	public String updateHospital(String ID,String Code, String Name, String Address, String PhoNo)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE hospitals SET HospitalCode=?, HospitalName=?,HospitalAddress=?,HospitalPhoNo=? WHERE HospitalID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, Code);
	 preparedStmt.setString(2, Name);
	 preparedStmt.setString(3, Address);
	 preparedStmt.setString(4, PhoNo);
	 preparedStmt.setInt(5, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the Hospital Dataset.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteHospital(String HospitalID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from hospitals where HospitalID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(HospitalID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the hospital Dataset.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 

}
