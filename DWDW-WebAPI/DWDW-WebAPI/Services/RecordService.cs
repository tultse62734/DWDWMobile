using DWDW_WebAPI.Models;
using DWDW_WebAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public interface IRecordService
    {
        RecordViewModel GetRecords();
    }
    public class RecordService : IRecordService
    {
        private DWDBContext db;

        public RecordService()
        {
            this.db = new DWDBContext();

        }

        public RecordViewModel GetRecords()
        {
            throw new NotImplementedException();
        }

        //public RecordViewModel GetRecords()
        //{
        //    var records = db.Records;
        //    var r = records.Select(x => new RecordViewModel
        //    {


        //    });
        //    return r;
        //}
    }
}