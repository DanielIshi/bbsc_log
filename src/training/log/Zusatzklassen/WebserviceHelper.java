package training.log.Zusatzklassen;

import org.codehaus.jackson.annotate.JsonSubTypes.Type;

import WP7toAndroid.DataContractJsonSerializer;

import com.google.gson.internal.Streams;



    public class WebserviceHelper
    {
        public static Object Deserialize(Streams streamObject, Type serializedObjectType)
        {
            if (serializedObjectType == null || streamObject == null)
                return null;

            DataContractJsonSerializer ser = new DataContractJsonSerializer(serializedObjectType);
            return ser.ReadObject(streamObject);
        }
    }
  
      


