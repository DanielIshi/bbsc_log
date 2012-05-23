package training.log.Zusatzklassen;

import java.util.ArrayList;
import java.util.List;

import WP7toAndroid.FileMode;
import WP7toAndroid.IsolatedStorageFile;
import WP7toAndroid.IsolatedStorageFileStream;
import WP7toAndroid.XmlWriter;
import WP7toAndroid.XmlWriterSettings;
import WP7toAndroid.Xml_Serializer;
import android.util.Log;
	

    public class DateiOperationen
    {
       

        public DateiOperationen()
        {

        }


        public void Save(List<Optionen> pref)
        {
            // Write to the Isolated Storage
            XmlWriterSettings xmlWriterSettings = new XmlWriterSettings();
            xmlWriterSettings.Indent = true;

            IsolatedStorageFile myIsolatedStorage = IsolatedStorageFile.GetUserStoreForApplication();
            
            	IsolatedStorageFileStream stream = myIsolatedStorage.OpenFile("Optionen.xml", FileMode.Create);
                
                    Xml_Serializer serializer = new Xml_Serializer(ListOptionen.class.getClass());
                    XmlWriter xmlWriter = XmlWriter.Create(stream, xmlWriterSettings);
                    
                    serializer.Serialize(xmlWriter, pref);
        }              
          
        

        public ArrayList<Optionen> Load()
        {
            if (IsolatedStorageFile.GetUserStoreForApplication().FileExists("Optionen.xml"))
            {
               
                	IsolatedStorageFile myIsolatedStorage = IsolatedStorageFile.GetUserStoreForApplication();
                    
                		IsolatedStorageFileStream stream = myIsolatedStorage.OpenFile("Optionen.xml", FileMode.Open);
                        
                            Xml_Serializer serializer = new Xml_Serializer(ListOptionen.class.getClass());
                            ArrayList<Optionen> data = (ArrayList<Optionen>)serializer.Deserialize(stream);
                            Log.e("","geladene Daten: " + data.get(0).fitnesscenter);
                            //gewaehlteLocation.Text = data.ElementAt(0).FitnessCenter;
                            return data;
                      
               
                
            }
            else
            {
                Log.e("","Optionen.xml existiert nicht. Lege neu an.");
                Optionen opt = new Optionen();
                opt.fitnesscenter = "keines gewählt";
                opt.reference = "keines gewählt";
                opt.aktuellespalte = 1;
                
                ArrayList<Optionen> pref = new ArrayList<Optionen>();		// = new List<Optionen>();
                pref.add(opt);				//.Add(opt);
                Save(pref); 
                return pref;
            }
        }
        
        public class ListOptionen{
        	public List<Optionen> Inhalt;
        }
        
 }

