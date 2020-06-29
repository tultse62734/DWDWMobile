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
    public class RecordsController : ApiController
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
        [HttpGet]
        [Route("GetAllRecords")]
        public IHttpActionResult GetAdminAllRecords()
        {
            try
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
            catch (Exception)
            {
                return BadRequest(ErrorMessage.GET_LIST_FAIL);
            }
        }

        //Get record for account
        //[Authorize(Roles = Constant.MANAGER_ROLE)]
        [HttpGet]
        [Route("GetRecordsManager")]
        public IHttpActionResult GetRecordManager(int managerId)
        {
            var recordList = new List<RecordViewModel>();
            try
            {
                var records = recordService.GetAllAdminRecords();
                if (records != null)
                {
                    for (int i = 0; i < records.Count; i++)
                    {
                        bool checkRecord = recordService.validateRecord(managerId, records.ElementAt(i));
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
            catch (Exception)
            {
                return BadRequest(ErrorMessage.GET_LIST_FAIL);
            }
        }

        //Search role for  admin
        //[Authorize(Roles = Constant.ADMIN_ROLE)]
        [HttpGet]
        [Route("GetRecords/{id}")]
        public IHttpActionResult GetRecordsByIDAdmin(int id)
        {
            try
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
            catch (Exception)
            {
                return BadRequest(ErrorMessage.SEARCH_FAIL);
            }
        }


        //Create new record
        [HttpPost]
        [Route("PostRecords/{deviceID}")]
        public IHttpActionResult PostDevices(int deviceID, RecordPostModel rm)
        {
            try
            {
                rm.deviceId = deviceID;
                rm.recordDate = DateTime.Now;
                recordService.CreateRecord(rm);
                recordService.Save();
                recordService.sendNotify(deviceID);
                return Ok();
            }
            catch (Exception)
            {
                return BadRequest(ErrorMessage.CREATE_FAIL);
            }
        }
    }
}