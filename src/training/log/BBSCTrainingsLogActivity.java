package training.log;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
//import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
//import android.widget.ListAdapter;
import android.widget.ListView;
//import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;






public class BBSCTrainingsLogActivity
extends Activity implements OnClickListener, LocationListener, OnItemClickListener
{
	public ArrayList<EditText> test = new ArrayList<EditText>();
	private TextView latituteField;
	private TextView longitudeField;
	private LocationManager locationManager;
	private String provider;
	
	public  placesSchnittstelle places;	
	public TextView gewCenter;
	public TextView refCenter;
	public String Name="",Referenz="";

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
		
		
		 Config.context = this;		 		 
		 
		 places = new  placesSchnittstelle();	 
		 
	        Button myLadenButton = (Button) this.findViewById(R.id.Laden);         myLadenButton.setOnClickListener(this);        
	        Button mySpeichernButton = (Button) this.findViewById(R.id.Speichern); mySpeichernButton.setOnClickListener(this);
	        Button myOptionenButton = (Button) this.findViewById(R.id.Optionen); myOptionenButton.setOnClickListener(this);
	        Button myEinloggenButton = (Button) this.findViewById(R.id.Einloggen); myEinloggenButton.setOnClickListener(this);
	        	        
	        FelderIDszuordnen();
	     
		try {
			
				//performAdressRecherche();	
				//places.performSearch();
				//places.Mein();
				//performAutoComplete();
				//performDetails("CnRtAAAATVNQ9blAE2j2yw_1hQrNfPkYAiM1Kbd37xvhjCPxINsxm4BzOtYcT6s1HGI5JvjEry7fg69XXnfw4geL5UWvWb_0p8aExgc-G0VUneooC4VwcjjkKS9Dm-NZyvJdceatvjEofKjpw35XqDZ70Ci4mxIQilbODkOJSygBW2ivXLJwzRoU2-hIM5QDIfHuJcebETCO3j4RCqs");
			} catch (Exception e) {	e.printStackTrace();}
			
		
		latituteField = (TextView) findViewById(R.id.TextView02);
		longitudeField = (TextView) findViewById(R.id.TextView04);

		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);

		// Initialize the location fields
		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			int lat = (int) (location.getLatitude());
			int lng = (int) (location.getLongitude());
			latituteField.setText(String.valueOf(lat));
			longitudeField.setText(String.valueOf(lng));
		} else {
			latituteField.setText("Provider not available");
			longitudeField.setText("Provider not available");
		}
		
		try {	LadeEinstellungen();} catch (Exception e) {	e.printStackTrace();}
		
	}
	
	public Address AdressRecherche(String Zeichenkette){		
		Geocoder myLocation = new Geocoder(getApplicationContext(), Locale.getDefault());   
		try {			
			
			List<Address> myCoordinates = myLocation.getFromLocationName(Zeichenkette, 2);
				System.out.println(myCoordinates.get(0).getLatitude());	System.out.println(myCoordinates.get(0).getLongitude());
			return  myCoordinates.get(0);
			
			} catch (IOException e) {				
				e.printStackTrace();
			}	
		 return null;		
	}
	
	
	
	public String KoordinatenRecherche(double latPoint, double lngPoint){				
		Geocoder myLocation = new Geocoder(getApplicationContext(), Locale.getDefault());   
		try {
			List<Address> myList = myLocation.getFromLocation(latPoint, lngPoint, 1);									
			System.out.println(myList.toString());
			return myList.get(0).getPostalCode()+myList.get(0).getLocality();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	//@Override
	public void onLocationChanged(Location location) {
		int lat = (int) (location.getLatitude());
		int lng = (int) (location.getLongitude());
		latituteField.setText(String.valueOf(lat));
		longitudeField.setText(String.valueOf(lng));
	}

	
	public void onStatusChanged(String provider, int status, Bundle extras) {}

	
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	
		   
    
    
	public void onClick(View v) {
		
		switch (v.getId()) {
		 	case R.id.Einloggen: Einloggen();          break;        
	        case R.id.Optionen:  	GeheZuEinstellungen();	 break;    
	        case R.id.Speichern:  	SetColumnEditable(0);	 break;  
	        case R.id.Laden:  		Laden();	 break;
	        case R.id.SearchAdress:  	AdresseSuchen();	 break; 
	        case R.id.speicher:  	try {SpeichereEinstellungen();	} catch (Exception e) {	e.printStackTrace();}	 break;  
	        case R.id.gehezurueck:  	geheZurueck();	 break;
	        case R.id.placesList2: System.out.println("geklickt");
	        }
				
	}
    
	
	private void AdresseSuchen() {		
		TextView Adresse = (TextView) findViewById(R.id.AdressFeld);
		Address a = AdressRecherche(Adresse.getText().toString());
				
		TextView Koord = (TextView) findViewById(R.id.TextKoordinaten);
		
		DecimalFormat df = new DecimalFormat( "00.00000" );
		String s = df.format( a.getLongitude() );		String s2 = df.format( a.getLatitude() );
		
		Koord.setText(s2+" , "+s);
		LoadingTheSpinner(a.getLatitude(), a.getLongitude());
	}



	private void SpeichereEinstellungen() {
		Name = gewCenter.getText().toString();
		Referenz = refCenter.getText().toString();
		Abspeichern("Name.txt",Name ); // );
		Abspeichern("Referenz.txt", Referenz );// );
	
	}
	
	
	private void LadeEinstellungen() throws Exception {
		FileInputStream inputStream = openFileInput("Name.txt");
		try {
			Log.i("TAG","Inhalt der Datei:");
			String s = places.convertStreamToString(inputStream);
			Log.i("TAG", s);
			Name=s;
			
		} finally {
		if( inputStream != null ) {
				inputStream.close();				
			}
		}
		
		FileInputStream inputStream2 = openFileInput("Referenz.txt");
		try {
			Log.i("TAG","Inhalt der Datei:");
			String s = places.convertStreamToString(inputStream2);
			Log.i("TAG", s);
			Referenz=s;
			
		} finally {
		if( inputStream != null ) {
				inputStream.close();				
			}
		}
		
		
	 		
	}
	
	
	public void Abspeichern(String Name, String Inhalt){
		FileOutputStream outputStream=null;   
			try {
				 outputStream = openFileOutput(Name, Context.MODE_PRIVATE);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				outputStream.write(Inhalt.getBytes());
				outputStream.flush();
			} catch (IOException e) {				
				e.printStackTrace();
			}
	}
	
	
	
	public void DateiLoeschen(String Name ){
		FileOutputStream outputStream=null; 
		try {
			outputStream = openFileOutput(Name, Context.MODE_APPEND);
		} catch (FileNotFoundException e) {
			System.out.println("Keine Datei zum Löschen vorhanden. Verzichte auf Löschvorgang.");
		}	
		try{
						deleteFile(Name);
					} finally {
							if ( outputStream != null ) {
							try {
								outputStream.close();
							} catch (IOException e) {
								Log.e("TAG",e.getMessage(),e);
							}
					}
			}
	}			

	
	private void geheZurueck() {
		//LoadingTheSpinner();
	}

	private void Einloggen() {
		Log.d("onClick", "BEGIN" );	
		AdressRecherche("Baumschulenstrasse 6, 12437 Berlin");
		Log.d("onClick", "END" );
		
	}

	private void GeheZuEinstellungen() {
			
		View myView = findViewById(R.id.linearLayout1);
        ViewGroup parent = (ViewGroup) myView.getParent();
        parent.removeView(myView);
	 
        
        
        //Check if the Layout already exists
        LinearLayout hiddenLayout = (LinearLayout)findViewById(R.id.hiddenLayout);
        if(hiddenLayout == null){
            //Inflate the Hidden Layout Information View 
            LinearLayout myLayout = (LinearLayout)findViewById(R.id.linearLayout2);
            View hiddenInfo = getLayoutInflater().inflate(R.layout.hidden, myLayout, false);
            myLayout.addView(hiddenInfo);
        }

              
        Button mySpeicherButton = (Button) this.findViewById(R.id.speicher);         mySpeicherButton.setOnClickListener(this); 
        Button myZurueckButton = (Button) this.findViewById(R.id.gehezurueck);         myZurueckButton.setOnClickListener(this); 
        Button myRechercheButton = (Button) this.findViewById(R.id.SearchAdress);         myRechercheButton.setOnClickListener(this); 
        
               
        gewCenter = (TextView) findViewById (R.id.TextFeldGewaehltesCenter);
		refCenter = (TextView) findViewById (R.id.TextfeldAnzeigeReferenz);
		
		gewCenter.setText(Name);
		refCenter.setText(Referenz);	
		
		
	}



	public void Laden(){		
		EditText MeineTextbox = (EditText) this.findViewById(R.id.EditText01);		 
		
	   for (int i=0;i<=195;i++){
		   Log.d("onClick", test.get(i).getText().toString() );
	   }
	  		
	}
	
	
	public void SetColumnEditable(int spalte){
		Log.d("onClick", "BEGIN" );
		for (int j=1;j<=13;j++){
		   test.get((j*14)).setEnabled(false);
	   }  
		Log.d("onClick", "END" );
	}
    
	
	
	
	public void LoadingTheSpinner(double lat, double longit){
		
		View myView = findViewById(R.id.hiddenLayout);
        ViewGroup parent = (ViewGroup) myView.getParent();
        parent.removeView(myView);            
        //Check if the Layout already exists
        LinearLayout ListeLay = (LinearLayout)findViewById(R.id.listenlayout);
        if(ListeLay == null){            
            LinearLayout myLayout = (LinearLayout)findViewById(R.id.linearLayout2);
            View hiddenInfo = getLayoutInflater().inflate(R.layout.liste, myLayout, false);
            myLayout.addView(hiddenInfo);
        }  	     
		
        	String[] Liste = places.Mein(lat,longit );
        	
        	
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			//		android.R.layout.simple_list_item_single_choice, android.R.id.text1, Liste);
			//Spinner spinner = (Spinner) this.findViewById(R.id.placesList);
			//spinner.setAdapter(adapter);
			
			ListView listView = (ListView) findViewById(R.id.placesList2);
				
			ArrayAdapter stauberichtAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_2, android.R.id.text1, Liste);
			listView.setAdapter(stauberichtAdapter);			
			listView.setOnItemClickListener(this);
	}
	
	
	
	
	
	public void FelderIDszuordnen(){
		    test.add((EditText) this.findViewById(R.id.EditText01));
		    test.add((EditText) this.findViewById(R.id.EditText02));
		    test.add((EditText) this.findViewById(R.id.EditText03));
		    test.add((EditText) this.findViewById(R.id.EditText04));
		    test.add((EditText) this.findViewById(R.id.EditText05));
		    test.add((EditText) this.findViewById(R.id.EditText06));
		    test.add((EditText) this.findViewById(R.id.EditText07));
		    test.add((EditText) this.findViewById(R.id.EditText08));
		    test.add((EditText) this.findViewById(R.id.EditText09));
		    
		    test.add((EditText) this.findViewById(R.id.EditText10));
		    test.add((EditText) this.findViewById(R.id.EditText11));
		    test.add((EditText) this.findViewById(R.id.EditText12));
		    test.add((EditText) this.findViewById(R.id.EditText13));
		    test.add((EditText) this.findViewById(R.id.EditText14));
		    test.add((EditText) this.findViewById(R.id.EditText15));
		    test.add((EditText) this.findViewById(R.id.EditText16));
		    test.add((EditText) this.findViewById(R.id.EditText17));
		    test.add((EditText) this.findViewById(R.id.EditText18));
		    test.add((EditText) this.findViewById(R.id.EditText19));
		    
		       
		    test.add((EditText) this.findViewById(R.id.EditText20));
		    test.add((EditText) this.findViewById(R.id.EditText21));
		    test.add((EditText) this.findViewById(R.id.EditText22));
		    test.add((EditText) this.findViewById(R.id.EditText23));
		    test.add((EditText) this.findViewById(R.id.EditText24));
		    test.add((EditText) this.findViewById(R.id.EditText25));
		    test.add((EditText) this.findViewById(R.id.EditText26));
		    test.add((EditText) this.findViewById(R.id.EditText27));
		    test.add((EditText) this.findViewById(R.id.EditText28));
		    test.add((EditText) this.findViewById(R.id.EditText29));
		    
		    test.add((EditText) this.findViewById(R.id.EditText30));
		    test.add((EditText) this.findViewById(R.id.EditText31));
		    test.add((EditText) this.findViewById(R.id.EditText32));
		    test.add((EditText) this.findViewById(R.id.EditText33));
		    test.add((EditText) this.findViewById(R.id.EditText34));
		    test.add((EditText) this.findViewById(R.id.EditText35));
		    test.add((EditText) this.findViewById(R.id.EditText36));
		    test.add((EditText) this.findViewById(R.id.EditText37));
		    test.add((EditText) this.findViewById(R.id.EditText38));
		    test.add((EditText) this.findViewById(R.id.EditText39));
		    
		    test.add((EditText) this.findViewById(R.id.EditText40));
		    test.add((EditText) this.findViewById(R.id.EditText41));
		    test.add((EditText) this.findViewById(R.id.EditText42));
		    test.add((EditText) this.findViewById(R.id.EditText43));
		    test.add((EditText) this.findViewById(R.id.EditText44));
		    test.add((EditText) this.findViewById(R.id.EditText45));
		    test.add((EditText) this.findViewById(R.id.EditText46));
		    test.add((EditText) this.findViewById(R.id.EditText47));
		    test.add((EditText) this.findViewById(R.id.EditText48));
		    test.add((EditText) this.findViewById(R.id.EditText49));
		    
		    test.add((EditText) this.findViewById(R.id.EditText50));
		    test.add((EditText) this.findViewById(R.id.EditText51));
		    test.add((EditText) this.findViewById(R.id.EditText52));
		    test.add((EditText) this.findViewById(R.id.EditText53));
		    test.add((EditText) this.findViewById(R.id.EditText54));
		    test.add((EditText) this.findViewById(R.id.EditText55));
		    test.add((EditText) this.findViewById(R.id.EditText56));
		    test.add((EditText) this.findViewById(R.id.EditText57));
		    test.add((EditText) this.findViewById(R.id.EditText58));
		    test.add((EditText) this.findViewById(R.id.EditText59));
		    
		    test.add((EditText) this.findViewById(R.id.EditText60));
		    test.add((EditText) this.findViewById(R.id.EditText61));
		    test.add((EditText) this.findViewById(R.id.EditText62));
		    test.add((EditText) this.findViewById(R.id.EditText63));
		    test.add((EditText) this.findViewById(R.id.EditText64));
		    test.add((EditText) this.findViewById(R.id.EditText65));
		    test.add((EditText) this.findViewById(R.id.EditText66));
		    test.add((EditText) this.findViewById(R.id.EditText67));
		    test.add((EditText) this.findViewById(R.id.EditText68));
		    test.add((EditText) this.findViewById(R.id.EditText69));
		    
		    test.add((EditText) this.findViewById(R.id.EditText70));
		    test.add((EditText) this.findViewById(R.id.EditText71));
		    test.add((EditText) this.findViewById(R.id.EditText72));
		    test.add((EditText) this.findViewById(R.id.EditText73));
		    test.add((EditText) this.findViewById(R.id.EditText74));
		    test.add((EditText) this.findViewById(R.id.EditText75));
		    test.add((EditText) this.findViewById(R.id.EditText76));
		    test.add((EditText) this.findViewById(R.id.EditText77));
		    test.add((EditText) this.findViewById(R.id.EditText78));
		    test.add((EditText) this.findViewById(R.id.EditText79));
		    
		    test.add((EditText) this.findViewById(R.id.EditText80));
		    test.add((EditText) this.findViewById(R.id.EditText81));
		    test.add((EditText) this.findViewById(R.id.EditText82));
		    test.add((EditText) this.findViewById(R.id.EditText83));
		    test.add((EditText) this.findViewById(R.id.EditText84));
		    test.add((EditText) this.findViewById(R.id.EditText85));
		    test.add((EditText) this.findViewById(R.id.EditText86));
		    test.add((EditText) this.findViewById(R.id.EditText87));
		    test.add((EditText) this.findViewById(R.id.EditText88));
		    test.add((EditText) this.findViewById(R.id.EditText89));
		    
		    test.add((EditText) this.findViewById(R.id.EditText90));
		    test.add((EditText) this.findViewById(R.id.EditText91));
		    test.add((EditText) this.findViewById(R.id.EditText92));
		    test.add((EditText) this.findViewById(R.id.EditText93));
		    test.add((EditText) this.findViewById(R.id.EditText94));
		    test.add((EditText) this.findViewById(R.id.EditText95));
		    test.add((EditText) this.findViewById(R.id.EditText96));
		    test.add((EditText) this.findViewById(R.id.EditText97));
		    test.add((EditText) this.findViewById(R.id.EditText98));
		    test.add((EditText) this.findViewById(R.id.EditText99));
		    
		    test.add((EditText) this.findViewById(R.id.EditText101));
		    test.add((EditText) this.findViewById(R.id.EditText102));
		    test.add((EditText) this.findViewById(R.id.EditText103));
		    test.add((EditText) this.findViewById(R.id.EditText104));
		    test.add((EditText) this.findViewById(R.id.EditText105));
		    test.add((EditText) this.findViewById(R.id.EditText106));
		    test.add((EditText) this.findViewById(R.id.EditText107));
		    test.add((EditText) this.findViewById(R.id.EditText108));
		    test.add((EditText) this.findViewById(R.id.EditText109));
		    test.add((EditText) this.findViewById(R.id.EditText110));
		    
		  		    
		    test.add((EditText) this.findViewById(R.id.EditText110));
		    test.add((EditText) this.findViewById(R.id.EditText111));
		    test.add((EditText) this.findViewById(R.id.EditText112));
		    test.add((EditText) this.findViewById(R.id.EditText113));
		    test.add((EditText) this.findViewById(R.id.EditText114));
		    test.add((EditText) this.findViewById(R.id.EditText115));
		    test.add((EditText) this.findViewById(R.id.EditText116));
		    test.add((EditText) this.findViewById(R.id.EditText117));
		    test.add((EditText) this.findViewById(R.id.EditText118));
		    test.add((EditText) this.findViewById(R.id.EditText119));
		    
		    test.add((EditText) this.findViewById(R.id.EditText120));
		    test.add((EditText) this.findViewById(R.id.EditText121));
		    test.add((EditText) this.findViewById(R.id.EditText122));
		    test.add((EditText) this.findViewById(R.id.EditText123));
		    test.add((EditText) this.findViewById(R.id.EditText124));
		    test.add((EditText) this.findViewById(R.id.EditText125));
		    test.add((EditText) this.findViewById(R.id.EditText126));
		    test.add((EditText) this.findViewById(R.id.EditText127));
		    test.add((EditText) this.findViewById(R.id.EditText128));
		    test.add((EditText) this.findViewById(R.id.EditText129));
		    
		    test.add((EditText) this.findViewById(R.id.EditText130));
		    test.add((EditText) this.findViewById(R.id.EditText131));
		    test.add((EditText) this.findViewById(R.id.EditText132));
		    test.add((EditText) this.findViewById(R.id.EditText133));
		    test.add((EditText) this.findViewById(R.id.EditText134));
		    test.add((EditText) this.findViewById(R.id.EditText135));
		    test.add((EditText) this.findViewById(R.id.EditText136));
		    test.add((EditText) this.findViewById(R.id.EditText137));
		    test.add((EditText) this.findViewById(R.id.EditText138));
		    test.add((EditText) this.findViewById(R.id.EditText139));
		    
		    test.add((EditText) this.findViewById(R.id.EditText140));
		    test.add((EditText) this.findViewById(R.id.EditText141));
		    test.add((EditText) this.findViewById(R.id.EditText142));
		    test.add((EditText) this.findViewById(R.id.EditText143));
		    test.add((EditText) this.findViewById(R.id.EditText144));
		    test.add((EditText) this.findViewById(R.id.EditText145));
		    test.add((EditText) this.findViewById(R.id.EditText146));
		    test.add((EditText) this.findViewById(R.id.EditText147));
		    test.add((EditText) this.findViewById(R.id.EditText148));
		    test.add((EditText) this.findViewById(R.id.EditText149));
		    
		    test.add((EditText) this.findViewById(R.id.EditText150));
		    test.add((EditText) this.findViewById(R.id.EditText151));
		    test.add((EditText) this.findViewById(R.id.EditText152));
		    test.add((EditText) this.findViewById(R.id.EditText153));
		    test.add((EditText) this.findViewById(R.id.EditText154));
		    test.add((EditText) this.findViewById(R.id.EditText155));
		    test.add((EditText) this.findViewById(R.id.EditText156));
		    test.add((EditText) this.findViewById(R.id.EditText157));
		    test.add((EditText) this.findViewById(R.id.EditText158));
		    test.add((EditText) this.findViewById(R.id.EditText159));
		    
		    test.add((EditText) this.findViewById(R.id.EditText160));
		    test.add((EditText) this.findViewById(R.id.EditText161));
		    test.add((EditText) this.findViewById(R.id.EditText162));
		    test.add((EditText) this.findViewById(R.id.EditText163));
		    test.add((EditText) this.findViewById(R.id.EditText164));
		    test.add((EditText) this.findViewById(R.id.EditText165));
		    test.add((EditText) this.findViewById(R.id.EditText166));
		    test.add((EditText) this.findViewById(R.id.EditText167));
		    test.add((EditText) this.findViewById(R.id.EditText168));
		    test.add((EditText) this.findViewById(R.id.EditText169));
		    
		    test.add((EditText) this.findViewById(R.id.EditText170));
		    test.add((EditText) this.findViewById(R.id.EditText171));
		    test.add((EditText) this.findViewById(R.id.EditText172));
		    test.add((EditText) this.findViewById(R.id.EditText173));
		    test.add((EditText) this.findViewById(R.id.EditText174));
		    test.add((EditText) this.findViewById(R.id.EditText175));
		    test.add((EditText) this.findViewById(R.id.EditText176));
		    test.add((EditText) this.findViewById(R.id.EditText177));
		    test.add((EditText) this.findViewById(R.id.EditText178));
		    test.add((EditText) this.findViewById(R.id.EditText179));
		    
		    test.add((EditText) this.findViewById(R.id.EditText180));
		    test.add((EditText) this.findViewById(R.id.EditText181));
		    test.add((EditText) this.findViewById(R.id.EditText182));
		    test.add((EditText) this.findViewById(R.id.EditText183));
		    test.add((EditText) this.findViewById(R.id.EditText184));
		    test.add((EditText) this.findViewById(R.id.EditText185));
		    test.add((EditText) this.findViewById(R.id.EditText186));
		    test.add((EditText) this.findViewById(R.id.EditText187));
		    test.add((EditText) this.findViewById(R.id.EditText188));
		    test.add((EditText) this.findViewById(R.id.EditText189));
		    
		    test.add((EditText) this.findViewById(R.id.EditText190));
		    test.add((EditText) this.findViewById(R.id.EditText191));
		    test.add((EditText) this.findViewById(R.id.EditText192));
		    test.add((EditText) this.findViewById(R.id.EditText193));
		    test.add((EditText) this.findViewById(R.id.EditText194));
		    test.add((EditText) this.findViewById(R.id.EditText195));
		    test.add((EditText) this.findViewById(R.id.EditText196));
	}

	

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		System.out.println("Element gewählt"+arg0.toString()+"  "+arg2+" "+ arg3);
		System.out.println(arg0.getAdapter().getItem(arg2).toString());
		System.out.println(places.references.get(arg2).Anschrift);
		
		
		View myView = findViewById(R.id.listenlayout);
        ViewGroup parent = (ViewGroup) myView.getParent();
        parent.removeView(myView);            
        //Check if the Layout already exists
        LinearLayout hiddenLayout = (LinearLayout)findViewById(R.id.hiddenLayout);
        if(hiddenLayout == null){            
            LinearLayout myLayout = (LinearLayout)findViewById(R.id.linearLayout2);
            View hiddenInfo = getLayoutInflater().inflate(R.layout.hidden, myLayout, false);
            myLayout.addView(hiddenInfo);
        }  	
		
	
		gewCenter.setText(places.references.get(arg2).Name);
		refCenter.setText(places.references.get(arg2).reference);
		
	}
	
}












