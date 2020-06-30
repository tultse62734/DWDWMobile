using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using DWDW_WebAPI.Contants;
using DWDW_WebAPI.Models;
using DWDW_WebAPI.Services;
using DWDW_WebAPI.ViewModel;

namespace DWDW_WebAPI.Controllers
{
    public class RecordsController : BaseController
    {
        private DWDBContext db = new DWDBContext();
        private IRecordService recordService;
        public RecordsController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            recordService = new RecordService();
        }

        //Get all record for admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [Authorize]
        [HttpGet]
        [Route("")]
        public IHttpActionResult GetRecords()
        {
            var user = this.GetIndentiy();

            //For manager
            var recordList = new List<RecordViewModel>();
            try
            {
                if (user.roleId == 1)
                {
                    var records = recordService.GetAllAdminRecords();
                    if (records != null)
                    {
                        return Ok(records);
                    }
                    else
                    {
                        return NotFound();
                    }
                }
                else
                {
                    var records = recordService.GetAllAdminRecords();
                    if (records != null)
                    {
                        for (int i = 0; i < records.Count; i++)
                        {
                            bool checkRecord = recordService.validateRecord(user.userId, records.ElementAt(i));
                            if (checkRecord)
                            {
                                recordList.Add(records.ElementAt(i));
                            }
                        }
                        return Ok(recordList);
                    }
                    else
                    {
                        return NotFound();
                    }
                }              
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }


        //Search record
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [Authorize]
        [HttpGet]
        [Route("{id}")]
        public IHttpActionResult GetRecordsByID(int id)
        {
            var user = this.GetIndentiy();
            var recordList = new List<RecordViewModel>();

            try
            {
                if (user.roleId == 1)
                {
                    var records = recordService.GetIDRecord(id);
                    if (records != null)
                    {
                        return Ok(records);
                    }
                    else
                    {
                        return NotFound();
                    }
                }
                else
                {
                    var records = recordService.GetAllAdminRecords();
                    if (records != null)
                    {
                        for (int i = 0; i < records.Count; i++)
                        {
                            bool checkRecord = recordService.validateRecord(user.userId, records.ElementAt(i));
                            if (checkRecord)
                            {
                                recordList.Add(records.ElementAt(i));
                            }
                        }
                        var recordSearch = recordList.FirstOrDefault(x => x.recordId == id);
                        return Ok(recordSearch);
                    }
                    else
                    {
                        return NotFound();
                    }
                }
                
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }


        //Create new record
        [HttpPost]
        [Route("{id}")]
        public IHttpActionResult PostDevices(int id, RecordPostModel rm)
        {
            try
            {
                rm.deviceId = id;
                rm.recordDate = DateTime.Now;
                recordService.CreateRecord(rm);
                recordService.Save();
                recordService.sendNotify(id);
                return Ok();
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }
    }
}