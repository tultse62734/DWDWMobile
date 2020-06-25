using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DWDW_WebAPI.Contants
{
    public static class Constant
    {
        public const string ADMIN_ROLE = "1";
        public const string MANAGER_ROLE = "2";
        public const string WORKER_ROLE = "3";
    }
    public static class ErrorMessage
    {
        public const string LOGIN_FAIL = "Invalid username password!";
        public const string GET_LIST_FAIL = "Failed to get list!";
        public const string SEARCH_FAIL = "Failed to search!";
        public const string CREATE_FAIL = "Failed to create!";
        public const string UPDATE_FAIL = "Failed to update!";
        public const string DELETE_FAIL = "Failed to delete!";

        public const string UPDATE_ERROR = "Update failed!";
        public const string INSERT_ERROR = "Insert failed!";
        public const string DEACTIVE_ERROR = "Deactive failed!";
    }
    public static class SuccessMessage
    {
        public const string UPDATE_SUCCESS = "Update succeed!";
        public const string INSERT_SUCCESS = "Insert succeed!";
        public const string DEACTIVE_SUCCESS = "Deactive succeed!";
    }
}