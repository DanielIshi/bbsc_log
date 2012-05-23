using System;
using System.Collections.Generic;
using System.Text;
using System.Net;
//using System.Windows.Browser;

namespace MM.Silverlight.GoogleChart
{
    public class UriBuilder : IUriBuilder
    {
        public Uri Build(string baseUri, string page, IDictionary<string, string> queryString)
        {
            if(string.IsNullOrEmpty(baseUri) && string.IsNullOrEmpty(page))
                throw new Exception("baseUri and/or page should be provided.");

            UriKind kind;
            if (string.IsNullOrEmpty(baseUri))
                kind = UriKind.Relative;
            kind = UriKind.Absolute;

            var uriBuilder = new StringBuilder(string.Concat(baseUri,page));
            if(queryString!=null && queryString.Count >0)
            {
                char separatorCharacter = '?';
                foreach (var queryPart in queryString)
                {
                    uriBuilder.Append(separatorCharacter);
                    uriBuilder.Append(queryPart.Key);
                    uriBuilder.Append('=');
                    uriBuilder.Append(HttpUtility.UrlEncode(queryPart.Value));

                    separatorCharacter = '&';
                }
            }
            return new Uri(uriBuilder.ToString(), kind);
        }
    }
}