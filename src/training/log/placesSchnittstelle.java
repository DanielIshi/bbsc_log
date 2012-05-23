package training.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kxml2.kdom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ecs.googleplaces.sample.model.Place;
import com.ecs.googleplaces.sample.model.PlacesList;

import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class placesSchnittstelle{
	
		private static final String API_KEY = "AIzaSyBpso8l96kJ7GiGbVw-1sfeAvJZQe_nnEs";	
		// The different Places API endpoints.
		private static final String PLACES_SEARCH_URL =  "https://maps.googleapis.com/maps/api/place/search/json?";
		private static final String PLACES_AUTOCOMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
		private static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?";
		private static final String ADRESS_TO_GEODATE = "http://maps.google.com/maps/geo?";
		private static final boolean PRINT_AS_STRING = false;

		//public List<String> references = new ArrayList<String>();
		public List<RechercheErgebnis> references = new ArrayList<RechercheErgebnis>();
		
		//Fitness am Park GmbH, Elsenstraﬂe 113, 12435 Berlin
		double latitude = 52.489941;
		double longitude = 13.456213;
		

		//Fitness & Health Club
		//performDetails("CoQBdgAAACu0SCmFXzFsuFUQQRasoyVk5_M8unIbtfoZh7jlO1ArXwls3yyWKTfelXL_dtD0WSLwTv0Mz4QBWsmgXxEB4XjaklbYj_EUsRkRKoXf45Lx5HRYddLlTqErc9lf8fNk6sPuymKl76a7A7e7JZNeImfhqbovLMi8K4lNaczGEzwFEhAc7gK0JkhC0R1LkYesAH7xGhSvmuaBYs8UGYZ-b940GswKmdOBWA");
		//McFit Adlershof
		//performDetails("CnRoAAAARyTxqlddGYxEgr3zBiN5iVqXCwLBWEMdxJxP_I3BaW0fKaID7hz0T59WDCJDi-LOxn2dqGymGpuPKiZZFh7M7MiDh7ls0Tbn0SMhSluCJo971kj7tDHHhIeRF-DCi0_9kIQeO_cISHeRAShmNMwSbxIQEnXfcbuUPjVGAbOHUO0ouhoUZXwicWpo0KfOR_im3n1eUN1vXog");
				
	
	

	public void performSearch() throws Exception{			 
		String result = "";
		JSONObject jArray = null;
		
	     //String requesturl= "https://maps.googleapis.com/maps/api/place/search/json?location=50.936364,5.873566&radius=500&type=ziekenhuis&sensor=false&key=AIzaSyBpso8l96kJ7GiGbVw-1sfeAvJZQe_nnEs";
	     HttpClient client=new DefaultHttpClient();       
	     String requesturl= "https://maps.googleapis.com/maps/api/place/search/json?";
	     requesturl+="location=50.936364,5.873566";
	     requesturl+="&radius=500";
	     requesturl+="&type=Fit";
	     requesturl+="&sensor=false";
	     requesturl+="&key=AIzaSyBpso8l96kJ7GiGbVw-1sfeAvJZQe_nnEs";
	              
        HttpPost post=new HttpPost(requesturl);
        
        try {
            HttpResponse response=client.execute(post);
            HttpEntity entity=response.getEntity();
            if (entity != null) {
                InputStream is = entity.getContent();
               String val = convertStreamToString(is);               
           	   Log.e("",val);          	   

           	   
            }
        } catch (ClientProtocolException e) {   
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static String convertStreamToString(InputStream is) throws Exception {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	      sb.append(line + "\n");
	    }
	    is.close();
	    return sb.toString();
	  }
	
	public String[] Mein(double lat, double longit){
		
		if (!references.isEmpty()) {references.clear();}
		
		//Get the data (see above)
		//JSONObject json = getJSONfromURL("https://maps.googleapis.com/maps/api/place/search/json?location=50.936364,5.873566&radius=500&type=ziekenhuis&sensor=false&key=AIzaSyBpso8l96kJ7GiGbVw-1sfeAvJZQe_nnEs");

		 String requesturl= "https://maps.googleapis.com/maps/api/place/search/json?";
	     
		 DecimalFormat df = new DecimalFormat( "00.000000" );
	     String s = df.format( lat );String s2 = df.format( longit );	
		 
		 
	     requesturl+="location="+s+","+s2;
	     requesturl+="&radius=500";
	     requesturl+="&type=Fit";
	     requesturl+="&sensor=false";
	     requesturl+="&key=AIzaSyBpso8l96kJ7GiGbVw-1sfeAvJZQe_nnEs";
		
	     JSONObject json = getJSONfromURL(requesturl);
		    try{
			//Get the element that holds the earthquakes ( JSONArray )
			JSONArray  results = json.getJSONArray("results");
			//RechercheErgebnis[] mylis= new RechercheErgebnis [results.length()]; 
			
			String[] myStrings= new String [results.length()]; 
			
		      	       	//Loop the Array
		        for(int i=0;i < results.length();i++){		

		        	JSONObject e = results.getJSONObject(i);
		        	RechercheErgebnis Erg = new RechercheErgebnis();
		        	myStrings[i] = e.getString("name");
		        	
		        	Erg.Name=e.getString("name");
		        	Erg.reference=e.getString("reference");
		        	Erg.id=e.getString("id");
		        	Erg.Anschrift=e.getString("vicinity");
		        	
		        	references.add(Erg);
		        	
		        	//mylis[i].reference=e.getString("reference"); 
		        	//mylis[i].id =e.getString("id");
		        	//mylis[i].address =e.getString("vicinity");
		        	
		        }
		       /*
		        for(int i=0;i < results.length();i++){	
		        	 mylis[i].Bezeichner = myStrings[i];
		        	//System.out.println(mylis[i]);     
		       } 
		       */ 
		         
		       
		        return myStrings;
		   }catch(JSONException e)        {
		       	 Log.e("log_tag", "Error parsing data "+e.toString());
		   }
		
		   return null; 
		      
	}
	
	
	
	
	public static JSONObject getJSONfromURL(String url){

		//initialize
		InputStream is = null;
		String result = "";
		JSONObject jArray = null;

		//http post
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		}catch(Exception e){
			Log.e("log_tag", "Error in http connection "+e.toString());
		}

		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();
		}catch(Exception e){
			Log.e("log_tag", "Error converting result "+e.toString());
		}

		//try parse the string to a JSON object
		try{
	        	jArray = new JSONObject(result);
		}catch(JSONException e){
			Log.e("log_tag", "Error parsing data "+e.toString());
		}

		return jArray;
	}
	
	
	
	

	
	
	public void performDetails(String reference) throws Exception {
		
		System.out.println("Perform Place Detail....");
		System.out.println("-------------------");
		
		 HttpClient client=new DefaultHttpClient();       
	     String requesturl= PLACES_DETAILS_URL;	         
	     requesturl+="reference="+reference;
	     requesturl+="&sensor=false";
	     requesturl+="&key=AIzaSyBpso8l96kJ7GiGbVw-1sfeAvJZQe_nnEs";
	              
        HttpPost post=new HttpPost(requesturl);
        
        try {
            HttpResponse response=client.execute(post);
            HttpEntity entity=response.getEntity();
            if (entity != null) {
                InputStream is = entity.getContent();
               String val = convertStreamToString(is);
               
           	 Log.e("",val);
            }
        } catch (ClientProtocolException e) {   
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	 
	 
	 
	public void performAutoComplete() throws Exception {
		
			System.out.println("Perform Autocomplete ....");
			System.out.println("-------------------------");
			
			DecimalFormat df = new DecimalFormat( "00.000000" );
		     String s = df.format( latitude );String s2 = df.format( longitude );	     
			
			 HttpClient client=new DefaultHttpClient();       
		     String requesturl= PLACES_AUTOCOMPLETE_URL;
		     requesturl+="input=Fit";	        
		     requesturl+="&location="+s+","+s2;
		     requesturl+="&radius=300";
		     requesturl+="&sensor=false";
		     requesturl+="&key=AIzaSyBpso8l96kJ7GiGbVw-1sfeAvJZQe_nnEs";
		 
			
			   HttpPost post=new HttpPost(requesturl);
		         
		         try {
		             HttpResponse response=client.execute(post);
		             HttpEntity entity=response.getEntity();
		             if (entity != null) {
		                 InputStream is = entity.getContent();
		                String val = convertStreamToString(is);
		                
		            	 Log.e("",val);
		             }
		         } catch (ClientProtocolException e) {   
		             e.printStackTrace();
		         } catch (IOException e) {
		             e.printStackTrace();
		         }		
		
		}	
	
		
	
	
	

}
