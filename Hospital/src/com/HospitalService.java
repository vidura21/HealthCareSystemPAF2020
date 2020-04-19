package com;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Hospital;


@Path("/Hos")

public class HospitalService {
	
	Hospital HosOb = new Hospital();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospital()
	{
	return HosOb.readHospital();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital
			(@FormParam("HospitalCode") String HospitalCode,
			@FormParam("HospitalName") String HospitalName,
			@FormParam("HospitalAddress") String HospitalAddress,
			@FormParam("HospitalPhoNo") String HospitalPhoNo)
	{
	 String output = HosOb.insertHospital(HospitalCode, HospitalName, HospitalAddress, HospitalPhoNo);
	return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(String HospitalData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(HospitalData).getAsJsonObject();
	//Read the values from the JSON object
	 String HospitalID = itemObject.get("HospitalID").getAsString();
	 String HospitalCode = itemObject.get("HospitalCode").getAsString();
	 String HospitalName = itemObject.get("HospitalName").getAsString();
	 String HospitalAddress = itemObject.get("HospitalAddress").getAsString();
	 String HospitalPhoNo = itemObject.get("HospitalPhoNo").getAsString();
	 String output = HosOb.updateHospital(HospitalID,HospitalCode,HospitalName,HospitalAddress,HospitalPhoNo);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String HospitalData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(HospitalData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String HospitalID = doc.select("HospitalID").text();
	 String output = HosOb.deleteHospital(HospitalID);
	return output;
	}
	

}
