package model;

import java.sql.*;

public class Doctor {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/doctor", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
//########################################################################################
//###################### OPERATION Management By - Doctor ##################################
//########################################################################################

	public String inserOperation(String code, String name, String date, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into operations(`operID`,`operCode`,`operName`,`operDate`,`operDesc`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, desc);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting.";
			System.err.println(e.getMessage());
		}
		return output;
	}

//----------------------------------------------------------------------------------------

	public String readOperation() {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"2\"><tr><th>Operation Code</th><th>Operation Name</th><th>Operation Date</th><th>Operation Description</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from operations";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String operID = Integer.toString(rs.getInt("operID"));
				String operCode = rs.getString("operCode");
				String operName = rs.getString("operName");
				String operDate = rs.getString("operDate");
				String operDesc = rs.getString("operDesc");

				// Add into the html table
				output += "<tr><td>" + operCode + "</td>";
				output += "<td>" + operName + "</td>";
				output += "<td>" + operDate + "</td>";
				output += "<td>" + operDesc + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"doctors.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"operID\" type=\"hidden\" value=\"" + operID + "\">" + "</form></td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}

//-----------------------------------------------------------------------------------------

		public String updateOperation(String ID, String code, String name, String date, String desc)
		{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{return "Error while connecting to the database for updating."; }
	 
							// create a prepared statement
							String query = "UPDATE operations SET operCode=?,operName=?,operDate=?,operDesc=?WHERE operID=?";
	 
							PreparedStatement preparedStmt = con.prepareStatement(query);
	 
							// binding values
							preparedStmt.setString(1, code);
							preparedStmt.setString(2, name);
							preparedStmt.setString(3, date);
							preparedStmt.setString(4, desc);
							preparedStmt.setInt(5, Integer.parseInt(ID));
	 
							// execute the statement
							preparedStmt.execute();
							con.close();
	 
							output = "Updated successfully";
					}
					catch (Exception e)
					{
		 					output = "Error while updating!";
	 						System.err.println(e.getMessage());
					}
					return output;
	 }
		
		
//-----------------------------------------------------------------------------------------

	public String deleteOperation(String operID) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from operations where operID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(operID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting";
			System.err.println(e.getMessage());
		}
		return output;
	}


//------------------------------------------------------------------------------------------



//########################################################################################
//###################### PRESCRIPTION Management By - Doctor ##################################
//########################################################################################

	public String inserPrescription(String code, String name,String age, String date, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into prescriptions(`presID`,`presCode`,`presName`,`presAge`,`presDate`,`presDesc`)"+ " values (?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, age);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, desc);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while insertinggg.";
			System.err.println(e.getMessage());
		}
		return output;
	}

//----------------------------------------------------------------------------------------

public String readPrescription() {
	String output = "";
	try {
		Connection con = connect();

		if (con == null) {
			return "Error while connecting to the database for reading.";
		}

		// Prepare the html table to be displayed
		output = "<table border=\"2\"><tr><th>Prescription Code</th><th>Prescription Name</th><th>Prescription Age</th><th>Prescription Date</th><th>Prescription Description</th><th>Update</th><th>Remove</th></tr>";

		String query = "select * from prescriptions";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		// iterate through the rows in the result set
		while (rs.next()) {
			String presID = Integer.toString(rs.getInt("presID"));
			String presCode = rs.getString("presCode");
			String presName = rs.getString("presName");
			String presAge = rs.getString("presAge");
			String presDate = rs.getString("presDate");
			String presDesc = rs.getString("presDesc");

			// Add into the html table
			output += "<tr><td>" + presCode + "</td>";
			output += "<td>" + presName + "</td>";
			output += "<td>" + presAge + "</td>";
			output += "<td>" + presDate + "</td>";
			output += "<td>" + presDesc + "</td>";

			// buttons
			output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
					+ "<td><form method=\"post\" action=\"doctors.jsp\">"
					+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
					+ "<input name=\"operID\" type=\"hidden\" value=\"" + presID + "\">" + "</form></td></tr>";

		}

		con.close();

		// Complete the html table
		output += "</table>";
	} catch (Exception e) {
		output = "Error while reading";
		System.err.println(e.getMessage());
	}
	return output;
}

//-----------------------------------------------------------------------------------------

	public String updatePrescription(String ID, String code, String name,String age, String date, String desc)
	{
				String output = "";
				
				try
				{
						Connection con = connect();
						
						if (con == null)
						{return "Error while connecting to the database for updating."; }
 
						// create a prepared statement
						String query = "UPDATE prescriptions SET presCode=?,presName=?,presAge=?,presDate=?,presDesc=?WHERE presID=?";
 
						PreparedStatement preparedStmt = con.prepareStatement(query);
 
						// binding values
						preparedStmt.setString(1, code);
						preparedStmt.setString(2, name);
						preparedStmt.setString(3, age);
						preparedStmt.setString(4, date);
						preparedStmt.setString(5, desc);
						preparedStmt.setInt(6, Integer.parseInt(ID));
 
						// execute the statement
						preparedStmt.execute();
						con.close();
 
						output = "Updated successfully";
				}
				catch (Exception e)
				{
	 					output = "Error while updating!";
 						System.err.println(e.getMessage());
				}
				return output;
 }
	
	
//-----------------------------------------------------------------------------------------

public String deletePrescription(String presID) {
	String output = "";
	try {
		Connection con = connect();

		if (con == null) {
			return "Error while connecting to the database for deleting.";
		}

		// create a prepared statement
		String query = "delete from prescriptions where presID=?";

		PreparedStatement preparedStmt = con.prepareStatement(query);

		// binding values
		preparedStmt.setInt(1, Integer.parseInt(presID));

		// execute the statement
		preparedStmt.execute();
		con.close();

		output = "Deleted successfully";
	} catch (Exception e) {
		output = "Error while deleting";
		System.err.println(e.getMessage());
	}
	return output;
}

}
//------------------------------------------------------------------------------------------

