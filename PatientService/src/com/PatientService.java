package com;
import model.Patient;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/patient")

public class PatientService {
	
Patient patientObj = new Patient(); 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readpatient()
	 {
		return patientObj.readpatient();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("name") String name,
	 @FormParam("address") String address,
	 @FormParam("phoneNo") String phoneNO,
	 @FormParam("year") String year,
	 @FormParam("email") String email)
	{
	 String output = patientObj.insertPatient(name, address, phoneNO, year, email);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String patientData)
	{
	//Convert the input string to a JSON object
	 JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
	//Read the values from the JSON object
	 String pid = patientObject.get("pid").getAsString();
	 String name = patientObject.get("name").getAsString();
	 String address = patientObject.get("address").getAsString();
	 String phoneNo = patientObject.get("phoneNo").getAsString();
	 String year = patientObject.get("year").getAsString();
	 String email = patientObject.get("email").getAsString();
	 String output = patientObj.updatepatient(pid, name, address, phoneNo, year,email);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletepatient(String patientdata)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(patientdata, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String pid = doc.select("pid").text();
	 String output = patientObj.deletepatient(pid);
	return output;
	}


}
