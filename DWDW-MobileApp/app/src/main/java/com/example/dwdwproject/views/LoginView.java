package com.example.dwdwproject.views;

import com.example.dwdwproject.models.ReponseDTO;

public interface LoginView extends BaseView {
    void loginSuccessString̣̣̣(String token);
    void loginSuccess(ReponseDTO mReponseDTO);
}
