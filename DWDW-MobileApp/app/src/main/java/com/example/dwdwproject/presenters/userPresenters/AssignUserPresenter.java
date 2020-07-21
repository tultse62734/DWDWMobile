package com.example.dwdwproject.presenters.userPresenters;
import android.content.Context;
import com.example.dwdwproject.ResponseDTOs.AssignUserDTO;
import com.example.dwdwproject.repositories.userRepositories.UserRepositories;
import com.example.dwdwproject.repositories.userRepositories.UserRepositotiesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.userViews.AssignUserView;
public class AssignUserPresenter {
    private Context context;
    private AssignUserView mAssignUserView;
    private UserRepositories mUserRepositories;
    public AssignUserPresenter(Context context, AssignUserView mAssignUserView) {
        this.context = context;
        this.mAssignUserView = mAssignUserView;
        this.mUserRepositories  = new UserRepositotiesImpl();
    }
    public void assignUser(String token, AssignUserDTO mAssignUserDTO){
        mUserRepositories.assignUserToLocation(context, token, mAssignUserDTO, new CallBackData<AssignUserDTO>() {
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
