using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Web;

namespace DWDW_WebAPI.Firebase
{
    public class FirebaseNotification
    {
        public void SendNotification(byte[] byteArray)
        {
            //string serverAPIKey = "AIzaSyARa1aKCJ1gQH9ZYsFFjuqK9YH4tVvSCg8";
            string senderID = "935732626257";
            string authorizationKey = "AAAA2d4Cw1E:APA91bEvIFr0lk6FOBIvwKnIn9qITwnfU7w15j2X0IBoREUCzmcwuLH-TmC93vlhhBlF1XwO170pc7I2HuEEYvHiqAeWr5f2pGiW3AuuNORcn1ikDdyjipHdBHVthG5qdeCoHLw6-v8_";

            WebRequest tRequest = WebRequest.Create("https://fcm.googleapis.com/fcm/send");
            tRequest.Method = "post";
            tRequest.ContentType = "";
            tRequest.ContentType = "application/json";
            tRequest.Headers.Add($"Authorization: key={authorizationKey}");

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
    }
}