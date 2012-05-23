package training.log.Zusatzklassen;

import WP7toAndroid.GeoPositionChangedEventArgs;
import WP7toAndroid.GeoPositionStatusChangedEventArgs;


public class DistanceCheck
    {
    	
    	
        GeoCoordinateWatcher watcher = null;
        public Boolean IstInDerNaehe = false;
        public Boolean eingecheckt = false;

        double latitudeF = 52.489941;
        double longitudeF = 13.456213;

       
        public void Starter() {
        	/*
            // The watcher variable was previously declared as type GeoCoordinateWatcher. 
            if (watcher == null)
            {
                watcher = new GeoCoordinateWatcher(GeoPositionAccuracy.High); // using high accuracy
                watcher.MovementThreshold = 20; // use MovementThreshold to ignore noise in the signal

                watcher = new GeoCoordinateWatcher(GeoPositionAccuracy.Default);
                watcher.PositionChanged += new EventHandler<GeoPositionChangedEventArgs<GeoCoordinate>>(watcher_PositionChanged);
                watcher.StatusChanged += new EventHandler<GeoPositionStatusChangedEventArgs>(watcher_StatusChanged);
                watcher.Start();
            }
            */
        }

        public void SetKoordinaten(String lat, String longi){
           /* latitudeF = ToDouble(lat, 52.489941);
             System.Diagnostics.Debug.WriteLine("Konvertiere " + lat + " zu " + latitudeF);
             longitudeF = ToDouble(longi, 13.456213);
             System.Diagnostics.Debug.WriteLine("Konvertiere " + longi + " zu " + longitudeF);*/
        }


        public void Stopper()
        {
            watcher.Stop();
        }

        void watcher_PositionChanged(Object sender, GeoPositionChangedEventArgs<GeoCoordinate> e)
        {
//            if (watcher.Status != GeoPositionStatus.Disabled) {
//                watcher.Stop();                
//            }
//            
//            //startLocationButton.IsEnabled = true;
//            //double Latitude = e.Position.Location.Latitude;
//            //double Longitude = e.Position.Location.Longitude;
//            double Latitude = 52.4687;
//            double Longitude = 13.49113;
//
//            double erg = Berechnungen(Latitude, Longitude, latitudeF, longitudeF);
//
//            System.Diagnostics.Debug.WriteLine("Wir befinden uns an folgenden Geokoordinaten: " + Latitude + " , " + Longitude);
//            System.Diagnostics.Debug.WriteLine("Entfernung ist: " + erg.ToString());
//
//            if (erg < 5 )
//            {
//                IstInDerNaehe = true;
//                if (!eingecheckt){
//                        MessageBoxResult m = MessageBox.Show("Sie befinden sich in der Nähe Ihres gespeicherten Fitnesscenters. Wollen Sie einchecken und trainieren (der Traininings-Log wird aktiviert und Sie können in der aktuellen Spalte Eintragungen vornehmen) ?", "Einchecken?", MessageBoxButton.OKCancel);
//                        if (m == MessageBoxResult.OK)
//                        {
//                            eingecheckt = true;
//                            //SpaltenFreigeben(aktSpalte);
//                        }
//                        else { 
//                            eingecheckt = false;                            
//                        }
//                }
//            }
//            else { 
//                IstInDerNaehe = false; eingecheckt = false;
//                MessageBox.Show("Sie befinden sich zu weit entfernt von ihrem gespeichertem Fitnesscenter.\n Sie können nicht einchecken bzw. wurden ausgecheckt.\n Editieren ist nun deaktiviert.");
//            }
//           

        }

        public Boolean getResult() {  return IstInDerNaehe;  }
        public Boolean getIsCheckedIn() { return eingecheckt; }


        void watcher_StatusChanged(Object sender, GeoPositionStatusChangedEventArgs e)
        {
//            switch (e.Status)
//            {
//                case GeoPositionStatus.Disabled:
//                    if (watcher.Permission == GeoPositionPermission.Denied) { System.Diagnostics.Debug.WriteLine("you have this application access to location."); }
//                    else { System.Diagnostics.Debug.WriteLine("location is not functioning on this device"); }
//                    break;
//                case GeoPositionStatus.Initializing:
//                    //startLocationButton.IsEnabled = false;
//                    break;
//                case GeoPositionStatus.NoData:
//                    System.Diagnostics.Debug.WriteLine( "location data is not available.");
//                    //stopLocationButton.IsEnabled = true;
//                    break;
//                case GeoPositionStatus.Ready:
//                    System.Diagnostics.Debug.WriteLine( "location data is available.");
//                   // stopLocationButton.IsEnabled = true;
//                    break;
//            }
        }


        public double Berechnungen(double x1, double y1, double x2, double y2)
        {

            double r = 6378.137;
            //double e = Math.Acos(Math.Sin(x1) * Math.Sin(x2) + Math.Cos(x1) * Math.Cos(x2) * Math.Cos(y2 - y1));
            double e = Math.acos(Math.sin(x1 / 57.2958) * Math.sin(x2 / 57.2958) + Math.cos(x1 / 57.2958) * Math.cos(x2 / 57.2958) * Math.cos((y2 / 57.2958) - (y1 / 57.2958)));
            double Entfernung = e * r;
            return Entfernung;
        }




        public static double ToDouble(String In, double Default)
        {
            double dblOut = 0;
            dblOut = Default;
            
            In = In.replaceAll(",", ".");

            try
            {
                ////dblOut = double.Parse(In, System.Globalization.CultureInfo.InvariantCulture);
            	
            }
            catch (Exception e)
            {
                dblOut = Default;
            }

            return dblOut;
        }

        /// <summary>
        /// Wandelt einen String in Double um (Default = 0 oder es wird eine Exception ausgelöst)
        /// </summary>
        /// <param name="In">String</param>
        /// <param name="UseExceptions">True (Exception wird ausgelöst) oder False (Default 0 wird verwendet)</param>
        /// <returns></returns>
        
        public static double ToDouble(String In, Boolean UseExceptions)
        {
            double dblOut=0;

//            In = In.Replace(",", ".");
//
//            try
//            {
//                dblOut = double.Parse(In, System.Globalization.CultureInfo.InvariantCulture);
//            }
//            catch (Exception ex)
//            {
//                if (UseExceptions)
//                    throw ex;
//                else
//                    dblOut = 0;
//            }

            return dblOut;
        }

 }

