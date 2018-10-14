package com.example.shirokuma.whatsdish;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.util.Objects;

public class ButtonDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        String dialogMessage;

        switch (getArguments().getInt("message")){
            case 0:
                dialogMessage = "一致する料理データが\n見つかりませんでした";
                break;
            case 1:
                dialogMessage = "解析完了";
                break;
             default:
                 dialogMessage = "予期せぬメッセージです";
                 break;
        }

        builder.setMessage(dialogMessage)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Dialogの縦幅と横幅を指定
        Dialog dialog = getDialog();
        WindowManager.LayoutParams layoutParams = Objects.requireNonNull(dialog.getWindow()).getAttributes();

        DisplayMetrics metrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float dialogwidth = 300 * metrics.scaledDensity;
        float dialogheight = 300 * metrics.scaledDensity;
        layoutParams.width = (int)dialogwidth;
        layoutParams.height = (int)dialogheight;

        dialog.getWindow().setAttributes(layoutParams);
    }
}