package training.log.Zusatzklassen;

import WP7toAndroid.INotifyPropertyChanged;
import WP7toAndroid.PropertyChangedEventArgs;
import android.util.EventLog.Event;


    public class EinzelnerEintragVM extends INotifyPropertyChanged
    {



        public String getLatitudeValue() {
        	OnPropertyChanged(LatitudeValue);
        	return LatitudeValue;
		}





		public void setLatitudeValue(String latitudeValue) {
			LatitudeValue = latitudeValue;
		}





		public String getLongitudeValue() {
			
			return LongitudeValue;
		}





		public void setLongitudeValue(String longitudeValue) {
			OnPropertyChanged(LongitudeValue);
			LongitudeValue = longitudeValue;
		}





		public String getNameValue() {
			return NameValue;
		}





		public void setNameValue(String nameValue) {
			OnPropertyChanged(NameValue);
			NameValue = nameValue;
		}





		public String getReferenceValue() {
			return ReferenceValue;
		}





		public void setReferenceValue(String referenceValue) {
			OnPropertyChanged(ReferenceValue);
			ReferenceValue = referenceValue;
		}





		private String LatitudeValue; 
        private String LongitudeValue ;  
        private String NameValue;
        private String ReferenceValue;
		
       



        protected void OnPropertyChanged(String propertyName)
        {
            if (this.PropertyChanged != null){ 
                this.PropertyChanged(this, new PropertyChangedEventArgs(propertyName)); }
        }

        //public Event PropertyChangedEventHandler PropertyChanged;
    }
