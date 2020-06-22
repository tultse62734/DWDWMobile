using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.Services
{
    public class ShiftService : IShiftService, IDisposable
    {
        private DWDBContext db;


        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public Shift GetShiftById(int shiftId)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Shift> GetShifts()
        {
            throw new NotImplementedException();
        }

        public void InsertShift(Shift shift)
        {
            throw new NotImplementedException();
        }
    }
}