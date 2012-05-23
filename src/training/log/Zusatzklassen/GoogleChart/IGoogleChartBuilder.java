using System.Windows.Media.Imaging;

namespace MM.Silverlight.GoogleChart
{
    public interface IGoogleChartBuilder
    {
        BitmapImage ConvertFrom(GoogleChartData googleChartData);
    }
}