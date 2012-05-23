package training.log.Zusatzklassen;

import java.net.URI;

import WP7toAndroid.DownloadStringCompletedEventArgs;
import WP7toAndroid.INotifyPropertyChanged;
import WP7toAndroid.ObservableCollection;
import WP7toAndroid.PropertyChangedEventArgs;
import WP7toAndroid.PropertyChangedEventHandler;
import android.util.EventLog.Event;




/*
using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.ComponentModel;
using System.Collections.ObjectModel;
using System.Xml.Linq;
using System.Linq;
using System.Text;
using System.IO;
*/


    /**
     * @author Ishi
     *
     */
    public class MyViewModel extends INotifyPropertyChanged
    {
       

        

		public MyViewModel()
        {
            ///this.PlacesListe = new ObservableCollection<EinzelnerEintragVM>();
           
            
        }

      


        public void LoadAsync(String lat, String longit)
        {
           // System.Diagnostics.Debug.WriteLine(lat);
           // System.Diagnostics.Debug.WriteLine(longit);
            
            String requesturl = "https://maps.googleapis.com/maps/api/place/search/xml?";
            
            //double lat = 52.489941; double longit = 13.456213;
           // String s = lat.ToString("0.000000");  String s2 = longit.ToString("0.000000");
            //s = s.Replace(",", ".");   s2 = s2.Replace(",", ".");

            requesturl += "location=" + lat + "," + longit;

            requesturl += "&radius=500";
            requesturl += "&type=Fit";
            requesturl += "&sensor=false";
            requesturl += "&key=AIzaSyBpso8l96kJ7GiGbVw-1sfeAvJZQe_nnEs";

           // System.Diagnostics.Debug.WriteLine(requesturl);

//
//            Uri linksUri = new URI(requesturl);
//            WebClient client = new WebClient();
//            client.DownloadStringCompleted += new DownloadStringCompletedEventHandler(client_DownloadStringCompleted);
//            client.DownloadStringAsync(linksUri);
        }

        void client_DownloadStringCompleted(Object sender, DownloadStringCompletedEventArgs e)
        {
//            // Back to the UI thread
//
//            ((WebClient)sender).DownloadStringCompleted -= client_DownloadStringCompleted;
//
//            System.Diagnostics.Debug.WriteLine(e.Result.ToString());
//
//            if (e.Error == null && e.Cancelled == false)
//            {
//                this.PlacesListe.Clear();
//
//
//                // Probably this needs to be done async??
//                XElement xel = XElement.Parse(e.Result);
//
//                Object items = xel.Descendants(XName.Get("result"));
//                
//              
//                
//                foreach (item in items)
//                {
//                	Object title = item.Descendants(XName.Get("name")).SingleOrDefault();
//                	Object Reference = item.Descendants(XName.Get("reference")).SingleOrDefault();
//
//                    Object geometry = item.Descendants(XName.Get("geometry")).SingleOrDefault();
//                    Object location = geometry.Descendants(XName.Get("location")).SingleOrDefault();
//
//                    Object lat = location.Descendants(XName.Get("lat")).SingleOrDefault();
//                    Object longi = location.Descendants(XName.Get("lng")).SingleOrDefault();
//
//                    if (Reference != null && title != null)
//                    {
//                        // Add  to list
//                        EinzelnerEintragVM vm = new EinzelnerEintragVM();
//                        vm.Name = title.Value; System.Diagnostics.Debug.WriteLine(vm.Name);
//                        vm.Reference = Reference.Value; System.Diagnostics.Debug.WriteLine(vm.Reference);
//                        vm.Latitude = lat.Value; System.Diagnostics.Debug.WriteLine(vm.Latitude);
//                        vm.Longitude = longi.Value; System.Diagnostics.Debug.WriteLine(vm.Longitude);
//
//                        this.PlacesListe.Add(vm);
//
//                    }
//                }
//            }
        }

       
        

     
           
         

       



        private ObservableCollection<EinzelnerEintragVM> PlacesListeValue; 
		public void setPlacesListeValue(ObservableCollection<EinzelnerEintragVM> placesListeValue) {
			OnPropertyChanged(latiduteValue);
			PlacesListeValue = placesListeValue;
		}



		private String latiduteValue;  
		public void setLatiduteValue(String latiduteValue) {
			OnPropertyChanged(latiduteValue);
			this.latiduteValue = latiduteValue;
		}



		private String longitudeValue;
		public void setLongitudeValue(String longitudeValue) {
			OnPropertyChanged(longitudeValue);
			this.longitudeValue = longitudeValue;
		}



     
        
        
       
		 public ObservableCollection<EinzelnerEintragVM> getPlacesListeValue() {
				return PlacesListeValue;
			}


			public String getLatiduteValue() {
				return latiduteValue;
			}

			public String getLongitudeValue() {
				return longitudeValue;
			}


			public PropertyChangedEventHandler getPropertyChanged() {
				return PropertyChanged;
			}
        


        public PropertyChangedEventHandler PropertyChanged;

        protected void OnPropertyChanged(String propertyName)
        {
            if (this.PropertyChanged != null) {
                this.PropertyChanged(this, new PropertyChangedEventArgs(propertyName));}
        }

 }

