package training.log.Zusatzklassen;

import WP7toAndroid.IsolatedStorageFile;
import WP7toAndroid.XmlWriterSettings;

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
using System.Collections.Generic;
using System.IO.IsolatedStorage;
using System.IO;
using System.Xml;
using System.Xml.Serialization;
*/



    public class DateiOperationenTPlan
    {
        public String Filename = "Inhalte.xml";

        public DateiOperationenTPlan()
        {

        }


        public void Save(TabellenInhalteDesLogs pref)
        {
            // Write to the Isolated Storage
            XmlWriterSettings xmlWriterSettings = new XmlWriterSettings();
            xmlWriterSettings.Indent = true;
            
            /*
            using (IsolatedStorageFile myIsolatedStorage = IsolatedStorageFile.GetUserStoreForApplication())
            {
                using (IsolatedStorageFileStream stream = myIsolatedStorage.OpenFile("Inhalte.xml", FileMode.Create))
                {
                    XmlSerializer serializer = new XmlSerializer(typeof(TabellenInhalteDesLogs));
                    using (XmlWriter xmlWriter = XmlWriter.Create(stream, xmlWriterSettings))
                    {
                        serializer.Serialize(xmlWriter, pref);
                    }
                }
            }*/
        }

        public void SaveArchived(TabellenInhalteDesLogs pref, int number)
        {
            // Write to the Isolated Storage
            XmlWriterSettings xmlWriterSettings = new XmlWriterSettings();
            xmlWriterSettings.Indent = true;
            
            /*
            using (IsolatedStorageFile myIsolatedStorage = IsolatedStorageFile.GetUserStoreForApplication())
            {
                using (IsolatedStorageFileStream stream = myIsolatedStorage.OpenFile("Inhalte" +number.ToString()+ ".xml", FileMode.Create))
                {
                    XmlSerializer serializer = new XmlSerializer(typeof(TabellenInhalteDesLogs));
                    using (XmlWriter xmlWriter = XmlWriter.Create(stream, xmlWriterSettings))
                    {
                        serializer.Serialize(xmlWriter, pref);
                    }
                }
            }*/
        }

        public TabellenInhalteDesLogs Load()
        {

            if (IsolatedStorageFile.GetUserStoreForApplication().FileExists("Inhalte.xml"))
            {
            	TabellenInhalteDesLogs data = new TabellenInhalteDesLogs();
            	try
                {
                	/*
                    using (IsolatedStorageFile myIsolatedStorage = IsolatedStorageFile.GetUserStoreForApplication())
                    {
                        using (IsolatedStorageFileStream stream = myIsolatedStorage.OpenFile("Inhalte.xml", FileMode.Open))
                        {
                            XmlSerializer serializer = new XmlSerializer(typeof(TabellenInhalteDesLogs));
                             = (TabellenInhalteDesLogs)serializer.Deserialize(stream);
                      
							 return data;
                           
                        }
                    }*/
            		 return data;
                }
                catch (Exception e){
                	return null; // new TabellenInhalteDesLogs { };
                	
                }


            }
            else {
                //MessageBox.Show("Inhalte.xml existiert nicht. Lege neu an.");
                       
                return null;
            }

        }
    }

