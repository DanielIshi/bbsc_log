package training.log;

import java.util.Date;

import WP7toAndroid.DateTime;
import WP7toAndroid.PhoneApplicationPage;
import training.log.Zusatzklassen.*;



public  class DiagrammAnzeige extends PhoneApplicationPage
{
        public DateiOperationenTPlan InhaltsSpeicher = new DateiOperationenTPlan();
        

        public DiagrammAnzeige()
        {
            InitializeComponent();


            List<Date> data = new List<Date>();
            TabellenInhalteDesLogs Inh = InhaltsSpeicher.Load();


//            int i = 0;
//            foreach (Object item: Inh.GesamtInhalt)
//            {
//                if (i == 0)
//                {
//                    int j = 0;
//                    foreach (String feld: item) 
//                    {
//                        string str = feld;
//                        Date MyDateTime;
//                        MyDateTime = new Date();
//                        try
//                        {
//                            MyDateTime = Date.ParseExact(str, "dd.MM.yy", null);
//                            System.out.println(MyDateTime.toString());
//                            data.add(MyDateTime);
//
//                        }
//                        catch (Exception e)
//                        {
//                            data.add(new Date());
//                            System.out.println("Keine gültige Datumsangabe in der textbox enthalten.");
//                            //throw;                           
//                        }
//                        j++;
//                    }
//                }
//                i++;
//            }

       

          Date StartTag = data.get(0);


          int[] data2 = new int[14];

//          for (int v = 0; v < 13; v++)
//          {
//              //System.Diagnostics.Debug.WriteLine("Vergleiche mit: " + StartTag.AddDays(v));
//              if (data.Contains(StartTag.AddDays(v)))
//              {
//                  data2[v] = 50; 
//              } else {
//                  data2[v] = 1; 
//              }                        
//          }    
             
            //foreach (var item in data2)     { System.Diagnostics.Debug.WriteLine(item.ToString()); }       


//            BarChart barChart = new BarChart(600, 250, BarChartOrientation.Vertical, BarChartStyle.Grouped);
//            barChart.SetTitle("Vertical Grouped");
//            barChart.AddAxis(new ChartAxis(ChartAxisType.Bottom));
//            barChart.AddAxis(new ChartAxis(ChartAxisType.Left));
//            barChart.SetData(data2);
//            barChart.SetDatasetColors(new string[] { "FF0000", "00AA00" });

            /*
            string myurl = "http://chart.apis.google.com/chart?cht=bvs&chs=250x250&chd=t:";
            myurl += "100,10,100,10,10,10";
            myurl += "&chco=FF3333&chp=0.2&chf=bg,lg,%200,76A4FB,1,ffffff,0|bg,lg,%200,76A4FB,1,ffffff,0&chdl=%C3%9Cbersicht&chxt=x&chxl=0:";
            myurl += "|20.05.|21.|22.|";
            myurl += "&chxr=0,Jan,Dez";
            */

     ////       BitmapImage _bi = new BitmapImage(new Uri(barChart.GetUrl(), UriKind.Absolute));
            
     ////       MeinBild.Source = _bi;
          

          class StatistikEintrag
          {
              public DateTime Datum = new DateTime();
              public Boolean warTrainieren = false;
              public StatistikEintrag(DateTime Dat, Boolean TrainierenGewesen)
              {
                  Datum = Dat; warTrainieren = TrainierenGewesen;
              }
          }

}


		


    


    }


