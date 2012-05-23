using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using System.Runtime.Serialization.Json;
using System.IO;
using System.Xml;
using System.Text;
using System.Xml.Linq;

namespace BBSC_Trainings_Log
{
    public partial class Liste : PhoneApplicationPage
    {

        public Liste()
        {
            InitializeComponent();       
        }

        protected override void OnNavigatedTo(System.Windows.Navigation.NavigationEventArgs e)
        {
            string lat = Uri.UnescapeDataString(NavigationContext.QueryString["lat"]);
            string longit = Uri.UnescapeDataString(NavigationContext.QueryString["longit"]);

            MyViewModel s = (MyViewModel)this.MyList.DataContext;
            s.LoadAsync(lat, longit);

            base.OnNavigatedTo(e);
        }


        private void PhoneApplicationPage_Loaded(object sender, RoutedEventArgs e)
        {
            
        }


        private void MyList_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            
            System.Diagnostics.Debug.WriteLine(e.ToString());
            ListBox L = (ListBox) sender;
            EinzelnerEintragVM t = (EinzelnerEintragVM) L.SelectedItem;
            System.Diagnostics.Debug.WriteLine( t.Name);

            this.NavigationService.Navigate(new Uri("/hidden.xaml?Name=" + t.Name + "&Reference=" + t.Reference +"&Latitude="+t.Latitude+"&Longitude="+t.Longitude, UriKind.Relative));
        }

    }
}