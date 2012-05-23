using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Media.Imaging;

namespace MM.Silverlight.GoogleChart
{
    public class GoogleChartBuilder : IGoogleChartBuilder
    {
        private readonly IUriBuilder uriBuilder;

        public GoogleChartBuilder(IUriBuilder uriBuilder)
        {
            this.uriBuilder = uriBuilder;
        }

        #region IGoogleChartBuilder Members

        public BitmapImage ConvertFrom(GoogleChartData googleChartData)
        {
            if (googleChartData.Type != GoogleChartType.Pie3d) //We don't support others yet.
                throw new NotSupportedException(
                    string.Format("The charttype {0} is not supported yet, only {1} is supported", googleChartData.Type,
                                  GoogleChartType.Pie3d));

            IDictionary<string, string> queryString = new Dictionary<string, string>();

            //ChartSize
            queryString.Add("chs", string.Format("{0}x{1}", googleChartData.Size.Width, googleChartData.Size.Height));

            //ChartType
            if (googleChartData.Type == GoogleChartType.Pie3d)
                queryString.Add("cht", "p3");

            //Labels
            if (googleChartData.Labels != null && googleChartData.Labels.Count > 0)
                queryString.Add("chl", string.Join("|", googleChartData.Labels.ToArray()));

            //Data
            if (googleChartData.Data != null && googleChartData.Data.Count > 0)
                queryString.Add("chd", string.Concat("t:", string.Join(",", googleChartData.Data.ToArray())));
            //Title
            if (!string.IsNullOrEmpty(googleChartData.Title))
                queryString.Add("chtt", googleChartData.Title.Replace(" ", "+").Replace(Environment.NewLine, "|"));
            return new BitmapImage(uriBuilder.Build("http://chart.apis.google.com/", "chart", queryString));
        }

        #endregion
    }
}