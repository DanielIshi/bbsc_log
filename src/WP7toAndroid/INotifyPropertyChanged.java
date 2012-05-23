package WP7toAndroid;

public class INotifyPropertyChanged {
	
	public Boolean PropertyChanged;
	 
	public Boolean PropertyChanged(Object Propertie, PropertyChangedEventArgs propertyChangedEventArgs) {
		return false;
	}

}
