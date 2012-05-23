using System;
using System.Collections.Generic;

namespace MM.Silverlight.GoogleChart
{
    public interface IUriBuilder
    {
        Uri Build(string baseUri, string page, IDictionary<string, string> queryString);
    }
}