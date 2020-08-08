package com.example.dwdwproject.presenters.userPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.AssignUserDTO;
import com.example.dwdwproject.repositories.userRepositories.UserRepositories;
import com.example.dwdwproject.repositories.userRepositories.UserRepositotiesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.userViews.AssignUserView;
import com.example.dwdwproject.views.userViews.GetUserView;

public class DeAssignUsrToLocationPresenter {
    private Context context;
    private AssignUserView mAssignUserView;
    private UserRepositories mUserRepositories;

    public DeAssignUsrToLocationPresenter(Context context, AssignUserView mAssignUserView) {
        this.context = context;
        this.mAssignUserView = mAssignUserView;
        this.mUserRepositories = new UserRepositotiesImpl();
    }
    public void deassginUser(String token,int userId,int locationId){
        this.mUserRepositories.deassginUserToLocatioṇ(context, token, userId, locationId, new CallBackData<AssignUserDTO>() {
            @Override
            public void onSucess(AssignUserDTO assignUserDTO) {
                mAssignUserView.getAssignUserSuccess(assignUserDTO);
            }

            @Override
            public void onFail(String message) {
                mAssignUserView.showError(message);
            }
        });
    }

}
