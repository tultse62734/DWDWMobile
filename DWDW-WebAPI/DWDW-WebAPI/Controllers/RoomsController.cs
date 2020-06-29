using DWDW_WebAPI.Models;
using DWDW_WebAPI.Services;
using DWDW_WebAPI.ViewModel;
using System;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web.Http;
using System.Web.Http.Description;

namespace DWDW_WebAPI.Controllers
{
    [RoutePrefix("v1/api/Rooms")]
    public class RoomsController : ApiController
    {
        private IRoomService roomService;
        private ModelMapping modelMapping;
        public RoomsController()
        {
            this.roomService = new RoomService(new DWDBContext());
            this.modelMapping = new ModelMapping();
        }
        // GET: api/Rooms
        [HttpGet]
        [Route("")]
        [ResponseType(typeof(RoomViewModel))]
        public IHttpActionResult GetRooms()
        {
            try
            {
                var list = roomService.GetRooms();
                if (!list.Any())
                {
                    return NotFound();
                }
                else
                {
                    return Ok(list);
                }
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        // GET: api/Rooms/5
        [HttpGet]
        [Route("{roomId}")]
        [ResponseType(typeof(RoomViewModel))]
        public IHttpActionResult GetRoom(int RoomId)
        {
            var room = roomService.GetRoomById(RoomId);
            if (room == null)
            {
                return NotFound();
            }
            return Ok(room);
        }

        // PUT: api/Rooms/5
        [Route("")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutRoom(int id, RoomViewModel roomViewModel)
        {
            try
            {
                if (!ModelState.IsValid) return BadRequest(ModelState);
                if (roomViewModel.roomId != id) return BadRequest();
                Room room = roomService.GetRoomById(id);
                if (room == null) return NotFound();
                //mapping
                modelMapping.UpdateRoomMapping(roomViewModel, room);
                if (roomService.UpdateRoom(room))
                {
                    return Ok("Update succeed.");
                }
                else
                {
                    return BadRequest("Can not update Room.");
                }
            }
            catch (DbUpdateConcurrencyException)
            {
                return InternalServerError();
            }
        }

        // POST: api/Rooms
        [HttpPost]
        [Route("")]
        [ResponseType(typeof(Room))]
        public IHttpActionResult PostRoom(RoomViewModel roomViewModel)
        {
            try
            {
                if (!ModelState.IsValid) return BadRequest(ModelState);
                //mapping
                Room room = modelMapping.CreateRoomMapping(roomViewModel);
                //check loi locationid null hoac sai

                if (roomService.InsertRoom(room))
                {
                    return Ok("Insert succeed.");
                }
                else
                {
                    return BadRequest("Can not insert Room.");
                }
            }
            catch (DbUpdateConcurrencyException)
            {
                if (roomService.RoomExists(roomViewModel.roomId))
                {
                    return Conflict();
                }
                return InternalServerError();
            }
        }

        [HttpPut]
        [Route("{roomId}/deactive")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutDeactiveRoom(int roomId)
        {
            try
            {
                if (!ModelState.IsValid) return BadRequest(ModelState);
                Room room = roomService.GetRoomById(roomId);
                if (room == null) return NotFound();
                if (room.isActive.Equals(false))
                {
                    return BadRequest("Location already deactivated.");
                }
                if (roomService.DeactiveRoom(room))
                {
                    return Ok("Deactive succeed.");
                }
                else
                {
                    return BadRequest("Can not deactive Room.");
                }
            }
            catch (DbUpdateConcurrencyException)
            {
                return InternalServerError();
            }
        }
    }
}