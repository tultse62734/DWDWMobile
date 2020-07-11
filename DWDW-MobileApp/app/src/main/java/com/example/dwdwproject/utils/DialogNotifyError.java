package com.example.dwdwproject.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.activities.LoginActivity;

public class DialogNotifyError {

    public static void showErrorLoginDialog(final Context context, String message) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_layout_notify_change_day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_Ok_choose);
        TextView mTxtNotify = dialog.findViewById(R.id.txt_notify_choose_error);
        mTxtNotify.setText(message);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
