using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Web;

namespace DWDW_WebAPI.Firebase
{
    public class FirebaseNotification
    {
        private static Uri FireBasePushNotificationsURL = new Uri("https://fcm.googleapis.com/fcm/send");
        private static string authorizationKey = "AAAA2d4Cw1E:APA91bEvIFr0lk6FOBIvwKnIn9qITwnfU7w15j2X0IBoREUCzmcwuLH-TmC93vlhhBlF1XwO170pc7I2HuEEYvHiqAeWr5f2pGiW3AuuNORcn1ikDdyjipHdBHVthG5qdeCoHLw6-v8_";

        public void SendNotification(byte[] byteArray)
        {
            try
            {
                string server_api_key = ConfigurationManager.AppSettings["SERVER_API_KEY"];
                string sender_id = ConfigurationManager.AppSettings["SENDER_ID"];
                WebRequest tRequest = WebRequest.Create("https://fcm.googleapis.com/fcm/send");
                tRequest.Method = "POST";
                //tRequest.ContentType = "";
                tRequest.ContentType = "application/json";
                tRequest.Headers.Add($"Authorization: key={authorizationKey}");
                //tRequest.Headers.Add($"Authorization: key={server_api_key}");
                tRequest.Headers.Add($"Sender: id={sender_id}");

                tRequest.ContentLength = byteArray.Length;
                Stream dataStream = tRequest.GetRequestStream();
                dataStream.Write(byteArray, 0, byteArray.Length);
                dataStream.Close();

                WebResponse tResponse = tRequest.GetResponse();
                dataStream = tResponse.GetResponseStream();
                StreamReader tReader = new StreamReader(dataStream);

                string tResponseFromServer = tReader.ReadToEnd();

                tReader.Close();
                dataStream.Close();
                tResponse.Close();
            }
            catch (Exception)
            {
                throw;
            }
        }
    }
}