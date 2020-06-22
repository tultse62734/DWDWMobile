using DWDW_WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DWDW_WebAPI.Services
{
    public interface IShiftService : IDisposable
    {
        IEnumerable<Shift> GetShifts();
        Shift GetShiftById(int shiftId);
        void InsertShift(Shift shift);
    }
}
