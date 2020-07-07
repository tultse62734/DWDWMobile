package com.example.dwdwproject.rooms.managements;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.dwdwproject.rooms.daos.UserDAO;
import com.example.dwdwproject.rooms.databases.DWDWDatabase;
import com.example.dwdwproject.rooms.entities.UserItemEntities;
public class DWDWManagement {
    private UserDAO mUserDAO;
    private Application mApplication;
    public DWDWManagement(Application mApplication) {
        this.mApplication = mApplication;
        DWDWDatabase dwdwDatabase = DWDWDatabase.getDatabase(mApplication);
        mUserDAO = dwdwDatabase.userDAO();
    }
    public LiveData<UserItemEntities> getAccount() {
        return mUserDAO.getAccount();
    }
    public void getAllUserItemEntity(OnDataCallBackUserData listener) {
        GetAllUserItemAsync getUserAsync = new GetAllUserItemAsync(mUserDAO, listener);
        getUserAsync.execute();
    }
    public void addUserItem(UserItemEntities orderItem,OnDataCallBackUserData mDataCallBackUserData) {
        AddUserItemAsyn addUserItemAsyn = new AddUserItemAsyn(mUserDAO,mDataCallBackUserData);
        addUserItemAsyn.execute(orderItem);
    }

    public void updateUserItem(UserItemEntities orderItem,OnDataCallBackUserData mDataCallBackUserData) {
        UpdateUserItemAsyn updateUserItemAsyn = new UpdateUserItemAsyn(mUserDAO,mDataCallBackUserData);
        updateUserItemAsyn.execute(orderItem);
    }
    public void deleteUserItem(UserItemEntities orderItem ,OnDataCallBackUserData mDataCallBackUserData) {
        DeleteUserItemAsyn deleteUserItemAsyn = new DeleteUserItemAsyn(mUserDAO,mDataCallBackUserData);
        deleteUserItemAsyn.execute(orderItem);
    }
    public void deleteAllUserItems(OnDataCallBackUserData mDataCallBackUserData){
        DeleteAllUserItemAsyn deleteAllUserItemAsyn = new DeleteAllUserItemAsyn(mUserDAO,mDataCallBackUserData);
        deleteAllUserItemAsyn.execute();
    }
    public void getAccessToken(OnDataCallBackAccessToken listener) {
        GetAccessTokenAsync getAccessTokenAsync = new GetAccessTokenAsync(mUserDAO, listener);
        getAccessTokenAsync.execute();
    }
    private class AddUserItemAsyn extends AsyncTask<UserItemEntities, Void, Void> {
        private UserDAO userDAO;
        private OnDataCallBackUserData mListener;

        public AddUserItemAsyn(UserDAO userDAO, OnDataCallBackUserData mListener) {
            this.userDAO = userDAO;
            this.mListener = mListener;
        }
        @Override
        protected Void doInBackground(UserItemEntities... orderItem) {
            try{
                userDAO.insertAccount(orderItem);
            }catch (SQLiteConstraintException e){
                mListener.onDataFail(e.getMessage());
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListener.onDataSuccess(null);
        }
    }
    private class UpdateUserItemAsyn extends AsyncTask<UserItemEntities, Void, Void> {
        private UserDAO userDAO;
        private OnDataCallBackUserData mListener;

        public UpdateUserItemAsyn(UserDAO userDAO, OnDataCallBackUserData mListener) {
            this.userDAO = userDAO;
            this.mListener = mListener;
        }

        @Override
        protected Void doInBackground(UserItemEntities... orderItem) {
            try{
                userDAO.updateAccount(orderItem);
            }catch (SQLiteConstraintException e){
                mListener.onDataFail(e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListener.onDataSuccess(null);
        }
    }

    private class DeleteUserItemAsyn extends AsyncTask<UserItemEntities, Void, Void> {
        private UserDAO userDAO;
        private OnDataCallBackUserData mListener;

        public DeleteUserItemAsyn(UserDAO userDAO, OnDataCallBackUserData mListener) {
            this.userDAO = userDAO;
            this.mListener = mListener;
        }

        @Override
        protected Void doInBackground(UserItemEntities... orderItem) {
            try{
                userDAO.deleteAccount(orderItem);
            }catch (SQLiteConstraintException e){
                mListener.onDataFail(e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListener.onDataSuccess(null);
        }
    }
    private class DeleteAllUserItemAsyn extends AsyncTask<Void, Void, Void> {
        private UserDAO userDAO;
        private OnDataCallBackUserData mListener;

        public DeleteAllUserItemAsyn(UserDAO userDAO, OnDataCallBackUserData mListener) {
            this.userDAO = userDAO;
            this.mListener = mListener;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                userDAO.deleleAllAccount();
            }catch (SQLiteConstraintException e){
                 mListener.onDataFail(e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListener.onDataSuccess(null);
        }
    }

    private class GetAllUserItemAsync extends AsyncTask<UserItemEntities, Void, Void>{
        private UserDAO userDAO;
        private UserItemEntities itemEntities;
        private OnDataCallBackUserData mListener;

        public GetAllUserItemAsync(UserDAO mUserDAO, OnDataCallBackUserData listener){
            this.userDAO= mUserDAO;
            this.mListener= listener;
        }
        @Override
        protected Void doInBackground(UserItemEntities... lists) {
            try{
                itemEntities= userDAO.getAccountEntities();
            }catch (SQLiteConstraintException e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(itemEntities!= null){
                mListener.onDataSuccess(itemEntities);
            }else {
                mListener.onDataFail("Lấy thông tin giỏ hàng thất bại!");
            }
        }
    }
    private class GetAccessTokenAsync extends AsyncTask<UserItemEntities, Void, Void> {
        private UserDAO userDAO;
        private String mAccessToken;
        private OnDataCallBackAccessToken mListener;

        public GetAccessTokenAsync(UserDAO mUserDAO, OnDataCallBackAccessToken mListener) {
            this.userDAO = mUserDAO;
            this.mListener = mListener;
        }


        @Override
        protected Void doInBackground(UserItemEntities... userItemEntities) {
            try {
                mAccessToken = userDAO.getAccessToken();
            } catch (SQLiteConstraintException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mAccessToken != null) {
                mListener.onDataSuccess(mAccessToken);
            } else {
                mListener.onDataFail();
            }
        }
    }

    public interface OnDataCallBackUserData {
        void onDataSuccess(UserItemEntities mItemEntities);

        void onDataFail(String message);
    }

    public interface OnDataCallBackAccessToken {
        void onDataSuccess(String accessToken);

        void onDataFail();
    }

}
