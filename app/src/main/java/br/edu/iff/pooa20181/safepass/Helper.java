package br.edu.iff.pooa20181.safepass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class Helper {

    public static boolean verifyEmpty(ViewGroup layout){
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);

            if (child instanceof EditText) {

                EditText editText = (EditText) child;

                if(editText.getText().toString().trim().isEmpty() || editText.getText().toString().trim().equals("")){
                    return true;
                }
            }
        }

        return false;
    }

    public static void launchMessage(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    public static void launchMessage(Context ctx, int message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

}
