package com;

import model.Appointment;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointment")
public class AppointmentService {
	Appointment AppointmentObj = new Appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment() {
		return AppointmentObj.readAppointment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("appointmentID") String appointmentID,
			@FormParam("appointmentCode") String appointmentCode, @FormParam("appointmentName") String appointmentName,
			@FormParam("appointmentDesc") String appointmentDesc) {
		String output = AppointmentObj.insertAppointment(appointmentID, appointmentCode, appointmentName,
				appointmentDesc);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appointmentData) { // Convert the input string to a JSON object
		JsonObject AppointmentObject = new JsonParser().parse(appointmentData).getAsJsonObject();

		// Read the values from the JSON object
		String appointmentID = AppointmentObject.get("appointmentID").getAsString();
		String appointmentCode = AppointmentObject.get("appointmentCode").getAsString();
		String appointmentName = AppointmentObject.get("appointmentName").getAsString();
		String appointmentDesc = AppointmentObject.get("appointmentDesc").getAsString();

		String output = AppointmentObj.updateAppointment(appointmentID, appointmentCode, appointmentName,
				appointmentDesc);

		return output;
	}



	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String appointmentData) { // Convert the input string to an XML document
		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser()); // Read the value from the element
		String appointmentID = doc.select("appointmentID").text();

		String output = AppointmentObj.deleteAppointment(appointmentID);

		return output;
	}
}
