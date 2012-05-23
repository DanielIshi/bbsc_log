package training.log;

import java.util.ArrayList;


import training.log.Zusatzklassen.*;
import System.Diagnostics.*;
import WP7toAndroid.*;

import com.google.api.client.util.DateTime;


/// using GoogleChartSharp;


public class MainPage extends PhoneApplicationPage
    {
        public DateiOperationenTPlan InhaltsSpeicher = new DateiOperationenTPlan();
        public ArrayList<Optionen> preferences = new ArrayList<Optionen>();
        public DateiOperationen FileOps = new DateiOperationen();
        public TabellenInhalteDesLogs Inhalte = new TabellenInhalteDesLogs();
        public int aktSpalte=14;
        public DistanceCheck DistanceChecker = new DistanceCheck();
		
        

        // Konstruktor
        public MainPage() { 


            ////InitializeComponent();
            
            // Laden(); 
            
            SpaltenSperren();

            preferences = FileOps.Load();
            Debug.WriteLine("geladenenes FitnessCenter aus Optionen.xml: " + preferences.get(0).fitnesscenter);

            aktSpalte = preferences.get(0).aktuellespalte;
            DistanceChecker.SetKoordinaten(preferences.get(0).latitude, preferences.get(0).longitude);
                      
        }

    


       
        private void Button_Laden(Object sender, RoutedEventArgs e) { Laden(); }
        private void Speichern(Object sender, RoutedEventArgs e)  { AllesSpeichern(); }
        private void Button_Login(object sender, RoutedEventArgs e) { 
            DistanceChecker.Starter();
            if (DistanceChecker.getIsCheckedIn()) { SpaltenFreigeben(aktSpalte); }
        
        }
        private void Button_Einstellungen(object sender, RoutedEventArgs e)   {  //// this.NavigationService.Navigate(new Uri("/hidden.xaml", UriKind.Relative));
        }
       



        private void Laden(){
            TabellenInhalteDesLogs Inh = InhaltsSpeicher.Load();
            if (Inh != null) { Inhalte = Inh;  Befuellen(Inh); }
            else { 
                AllesSpeichern();
                Inhalte = InhaltsSpeicher.Load();
            }
        }

        private void Befuellen(TabellenInhalteDesLogs I)
        {
//            List<String> myList = new List<String>();            
//            IEnumerator enumerator = I.GesamtInhalt.GetEnumerator();
//            enumerator.Reset();
//            int j = 0;
//            foreach (var canvase in TabellenGrid.Children)
//            {
//                j++;
//                enumerator.MoveNext();
//                myList = (List<String>)enumerator.Current;
//                Canvas c = (Canvas)canvase;
//                int i = 0;
//                foreach (var item in c.Children)
//                {
//                    if (i > 0)  
//                    {
//                        TextBox t = (TextBox)item;                       
//                        t.Text = myList[i - 1];
//                    }
//                    i++;
//                }
//            }
        }


        private void AllesSpeichern() {
            //TabellenInhalteDesLogs Inhalte = new TabellenInhalteDesLogs();
//            Inhalte.GesamtInhalt.Clear();
//            int j = 0;
//            foreach (var canvase in TabellenGrid.Children)
//            {
//                j++;
//                Canvas c = (Canvas)canvase;
//                List<String> EineZeile = new List<String>();
//
//                int i = 0;
//                foreach (var item in c.Children)
//                {
//                    if (i > 0)
//                    {
//                        TextBox t = (TextBox)item;
//                        EineZeile.Add(t.Text);
//                    }
//                    i++;
//                }
//                Inhalte.GesamtInhalt.Add(EineZeile);
//
//            }
//            InhaltsSpeicher.Save(Inhalte);        
        }

        private void AllesSpeichernArchived(int number)
        {            
//            int j = 0;
//            foreach (var canvase in TabellenGrid.Children)
//            {
//                j++;
//                Canvas c = (Canvas)canvase;
//                List<String> EineZeile = new List<String>();
//
//                int i = 0;
//                foreach (var item in c.Children)
//                {
//                    if (i > 0)
//                    {
//                        TextBox t = (TextBox)item;
//                        EineZeile.Add(t.Text);
//                    }
//                    i++;
//                }
//                Inhalte.GesamtInhalt.Add(EineZeile);
//
//            }
//            InhaltsSpeicher.SaveArchived(Inhalte, number);
        }



        private void Button_naechsterZyklus(Object sender, RoutedEventArgs e)
        {
//            if (aktSpalte == 14)
//            {
//
//                AllesSpeichernArchived(1);
//                TabellenInhalteDesLogs I = new TabellenInhalteDesLogs();
//                List<String> EineZeile = new List<String>();
//                List<String> LetzteZeile = new List<String>();
//
//                int j = 0;
//                foreach (var canvase in TabellenGrid.Children)
//                {
//                    j++;
//                    Canvas c = (Canvas)canvase;
//
//
//                    int i = 0;
//                    foreach (var item in c.Children)
//                    {
//                        if (i > 0)
//                        {
//                            TextBox t = (TextBox)item;
//                            EineZeile.Add("");
//                        }
//                        if (i == c.Children.Count - 1)
//                        {
//                            TextBox t = (TextBox)item;
//                            //System.Diagnostics.Debug.WriteLine("Letzer Eintrag: " + t.Text);
//                            LetzteZeile.Add(t.Text);
//                        }
//
//                        i++;
//                    }
//                    I.GesamtInhalt.Add(EineZeile);
//                }
//
//                Befuellen(I);
//
//
//                int k = 0;
//                foreach (var canvase in TabellenGrid.Children)
//                {
//                    k++;
//                    Canvas c = (Canvas)canvase;
//                    int i = 0;
//                    foreach (var item in c.Children)
//                    {
//                        if (i == 1)
//                        {
//                            TextBox t = (TextBox)item;
//                            t.Text = LetzteZeile[k - 1];
//                        }
//                        i++;
//                    }
//                }
//
//                AllesSpeichern();
//            }
//            else //Zykluswechsel verbieten wenn noch nicht alle Trainingstage durchlaufen
//            {
//                MessageBox.Show("Sie müssen erst alle Trainingstage absolviert haben bevor Sie den Zyklus wechseln können.");
//            }
        }


        private void SpaltenSperren()
        {
            
//            int j = 0;
//            foreach (var canvase in TabellenGrid.Children)
//            {
//                j++;
//                Canvas c = (Canvas)canvase;             
//
//                int i = 0;
//                foreach (var item in c.Children)
//                {
//                    if (i > 0)
//                    {
//                        TextBox t = (TextBox)item;                        
//                        t.IsReadOnly = true;
//                    }
//                    i++;
//                }    
//            }            
        }


        private void SpaltenFreigeben(int Spalte){
//            int j = 1;
//            foreach (var canvase in TabellenGrid.Children){
//                j++;
//                Canvas c = (Canvas)canvase;
//                int i = 0;
//                foreach (var item in c.Children){
//                    if (i == Spalte){
//                        TextBox t = (TextBox)item;
//                        t.IsReadOnly = false;
//                        t.Background = new SolidColorBrush(Color.FromArgb(255,255,255,255));                    
//                    }
//                    i++;
//                }
//            }
        }

        private void Button_TrainingsEnde(Object sender, RoutedEventArgs e){            
//            if (aktSpalte == 14){
//                aktSpalte = 1; preferences[0].aktuellespalte = 1;
//                FileOps.Save(preferences);
//                SpaltenSperren(); SpaltenFreigeben(aktSpalte);
//            }
//            else {
//                aktSpalte++; preferences[0].aktuellespalte++; 
//                SpaltenSperren(); SpaltenFreigeben(aktSpalte);                               
//            }           
//            FileOps.Save(preferences);
        }
//
//        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)   {
//            AllesSpeichern();
//            base.OnBackKeyPress(e);           
//            
//        }

        private void TextBox_GotFocus(object sender, RoutedEventArgs e) {
//            if (DistanceChecker.getIsCheckedIn()) { SpaltenFreigeben(aktSpalte); }
//            System.Diagnostics.Debug.WriteLine("Checke Distanz");
//            DistanceChecker.Starter();
//            if (!DistanceChecker.getResult()) {
//                MessageBox.Show("Sie befinden sich nicht in der Nähe ihres Fitnesscenters. Bearbeiten des Logs ist nur im Center erlaubt. Begeben Sie sich zu ihrem Fitness-Center und Checken Sie dort ein.");
//            }
//            if (DistanceChecker.getIsCheckedIn()) {
//                int j = 0;
//                foreach (var canvase in TabellenGrid.Children)
//                {
//                    j++;
//                    if (j==1){
//                            Canvas c = (Canvas)canvase;
//                            int i = 0;
//                            foreach (var item in c.Children)
//                            {
//                                if (i == aktSpalte)
//                                {
//                                    TextBox t = (TextBox)item;
//                                    t.IsReadOnly = true;
//                                    t.Text = DateTime.Today.ToShortDateString();
//                                }
//                                i++;
//                            }
//                    }
//                }
//            }
        }

        private void Button_Diagramm(Object sender, RoutedEventArgs e)
        {
          
            AllesSpeichern();
            this.NavigationService_Navigate(new Uri("/DiagrammAnzeige.xaml", UriKind.Relative)); ////
        
        }


     

        private void Button_vorheriger(object sender, RoutedEventArgs e)
        {

        }

        private void Button_Upload(object sender, RoutedEventArgs e)
        {
           



        	ArrayList<String> data = new ArrayList<String>();
            TabellenInhalteDesLogs Inh = InhaltsSpeicher.Load();


//            int i = 0;
//            foreach (item in Inh.GesamtInhalt)
//            {
//                if (i == 0)
//                {
//                    int j = 0;
//                    foreach (feld in item)  {                      
//                      
//                        DateTime MyDateTime = new DateTime();
//                        try
//                        {
//                            MyDateTime = DateTime.ParseExact(feld, "dd.MM.yy", null);
//                            //System.Diagnostics.Debug.WriteLine(MyDateTime.ToShortDateString());
//                            data.Add(MyDateTime.ToShortDateString());
//
//                        }
//                        catch (Exception e) {                            
//                           //// System.Diagnostics.Debug.WriteLine("Keine gültige Datumsangabe in der textbox enthalten.");                                                       
//                        }
//                        j++;
//                    }
//                }
//                i++;
//            }

            String requesturl = "http://info-volleyball.de/webserviceBBSCLog/add.php?";

            requesturl += "Spielername="+preferences.get(0).spielername;
            requesturl += "&Datumsangaben=";

          //  foreach (String item: data)            
          //      requesturl += item + ":"; 
            for (int i = 0; i < data.size(); i++)
            	requesturl += data.get(i) + ":";
            
            // string dat = "28.05.11:21.05.12:22.05.12:30.05.12";

            ////System.Diagnostics.Debug.WriteLine(requesturl);
            
            Uri linksUri = new Uri(requesturl);
            WebClient client = new WebClient();
            ////client.DownloadStringCompleted += new DownloadStringCompletedEventHandler(client_DownloadStringCompleted);
            client.DownloadStringAsync(linksUri);
            
        }




        void client_DownloadStringCompleted(object sender, DownloadStringCompletedEventArgs e)
        {
//             ((WebClient)sender).DownloadStringCompleted -= client_DownloadStringCompleted;
//
//        
//
//            if (e.Error == null && e.Cancelled == false)
//            {
//                MessageBox.Show("Upload... - Server antwortet: \n" + e.Result); 
//            }
        }




}
