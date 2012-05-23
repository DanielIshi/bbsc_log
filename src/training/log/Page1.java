package training.log;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import WP7toAndroid.DownloadStringCompletedEventArgs;
import WP7toAndroid.RoutedEventArgs;
import WP7toAndroid.XElement;
import android.widget.TextView;

import training.log.Zusatzklassen.*;

/*
using System;
using System.Linq;
using System.Xml.Linq;
using System.Net;
using System.Windows;
using Microsoft.Phone.Controls;
using System.Collections.Generic;
using System.IO.IsolatedStorage;
using System.IO;
using System.Xml;
using System.Xml.Serialization;
*/


public class Page1 
{
        public ArrayList<Optionen> preferences;
        
        public DateiOperationen FileOps = new DateiOperationen();
        public String Placename = "";
        public String refer = "";
        public String latitude = "";
        public String longitude = "";

		

		private TextView gewaehlteLocation;		
		private TextView AdressenFeld;
		private TextView NamensFeld;
        
        
        public void Page1()
        {
            //InitializeComponent();
            Laden();
        }

        private void Laden()
        {

            preferences = FileOps.Load();
            //System.Diagnostics.Debug.WriteLine("UI geladenen Daten: "+preferences[0].FitnessCenter);
            gewaehlteLocation.setText(preferences.get(0).fitnesscenter);
            NamensFeld.setText(preferences.get(0).spielername);       
        }

        private void Button_SucheStarten(Object sender, RoutedEventArgs e)
        {
            String s= (String) AdressenFeld.getText(); //.Text;
            performAdressRecherche(s);
            
        }

/*
        protected override void OnNavigatedTo(System.Windows.Navigation.NavigationEventArgs e)
        {
            /*
            
            if (NavigationContext.QueryString.ContainsKey("Name"))
            { 
                Placename = Uri.UnescapeDataString(NavigationContext.QueryString["Name"]);
                gewaehlteLocation.Text = Placename;
            }
            
            if (NavigationContext.QueryString.ContainsKey("Reference"))
            {
                refer = Uri.UnescapeDataString(NavigationContext.QueryString["Reference"]); 
            }

            if (NavigationContext.QueryString.ContainsKey("Latitude"))
            {
                latitude = Uri.UnescapeDataString(NavigationContext.QueryString["Latitude"]);
                KoordinatenAnzeige.Text = latitude;
            }

            if (NavigationContext.QueryString.ContainsKey("Longitude"))
            {
                longitude = Uri.UnescapeDataString(NavigationContext.QueryString["Longitude"]);
                KoordinatenAnzeige.Text += " , "+ longitude;
            }

           
           
            
            
            //base.OnNavigatedTo(e);
        }
*/



        public void performAdressRecherche(String adresse)  {            
		
			////System.Diagnostics.Debug.WriteLine("Perform Adress Recherche....");
			////System.Diagnostics.Debug.WriteLine("-------------------");

            String requesturl = "http://maps.google.com/maps/geo?";

            ////requesturl += "q=" + HttpUtility.UrlEncode(adresse);
			requesturl +=   "&key=AIzaSyBpso8l96kJ7GiGbVw-1sfeAvJZQe_nnEs";
			requesturl +=   "&sensor=true_or_false";
			requesturl +=   "&output=xml";    

            ////System.Diagnostics.Debug.WriteLine(requesturl);

/*////
            Uri linksUri = new URI(requesturl);
            WebClient client = new WebClient();
            client.DownloadStringCompleted += new DownloadStringCompletedEventHandler(client_DownloadStringCompleted);
            client.DownloadStringAsync(linksUri);					
		*/
	}

        void client_DownloadStringCompleted(Object sender, DownloadStringCompletedEventArgs e)
        {
            // Back to the UI thread
           
        	////((WebClient)sender).DownloadStringCompleted -= client_DownloadStringCompleted;
            

            if (e.Error == null && e.Cancelled == false)
            {                
                
                XElement xel = XElement.Parse(e.Result);
                Object xml = XElement.Parse(e.Result);
                Object ns = "{http://earth.google.com/kml/2.0}";
                ////Object extendedData = xml.Descendants(ns + "LatLonBox").First();
                
               
                class locationBox 
                {
                    public  String North = "";////extendedData.Attribute("north").Value,
                    public  String South = "";////extendedData.Attribute("south").Value,
                    public  String East = "";////extendedData.Attribute("east").Value,
                    public  String West = "";////extendedData.Attribute("west").Value,
                };

                //System.Diagnostics.Debug.WriteLine("Recherchierte Koordinaten: "+locationBox.North + " , " + locationBox.East);
//                String lat = URI.EscapeUriString(locationBox.North);
//                String longit = URI.EscapeUriString(locationBox.East);

                
                ////this.NavigationService.Navigate(new Uri("/Liste.xaml?lat="+lat+"&longit="+longit, UriKind.Relative));
            }
        }


        private void Button_EinstellungenSpeichern(Object sender, RoutedEventArgs e)
        {
        	
        	
        	Optionen opt = new Optionen();
            opt.fitnesscenter = Placename;
            opt.reference = refer;
            opt.spielername = NamensFeld.getText().toString();
            opt.latitude = latitude;
            opt.longitude = longitude;

            ArrayList<Optionen> pref = new ArrayList<Optionen>();
            pref.add(opt);
            
            FileOps.Save(pref);            
            ////this.NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
        }

        private void Button_Zurueck(Object sender, RoutedEventArgs e)
        {
            ///this.NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
        }

    }

