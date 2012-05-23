using System.Collections.Generic;
using System.Windows;

namespace MM.Silverlight.GoogleChart
{
    public class GoogleChartData
    {
        public GoogleChartData()
        {
            Data = new List<string>();
            Labels = new List<string>();
        }

        public IList<string> Data { get; set; }
        public IList<string> Labels { get; set; }
        public Size Size { get; set; }
        public GoogleChartType Type { get; set; }

        public string Title { get; set; }
    }
}