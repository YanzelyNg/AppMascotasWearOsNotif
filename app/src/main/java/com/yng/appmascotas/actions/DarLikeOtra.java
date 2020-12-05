package com.yng.appmascotas.actions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.yng.appmascotas.MainActivity;
import com.yng.appmascotas.SelCuentaInstagram;

public class DarLikeOtra extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String ACTION_KEY = "DAR_LIKE_OTRA";
        String accion = intent.getAction();

        if(ACTION_KEY.equals(accion))
        {
            intent.putExtra("frgToLoad", 0);
            intent.setClass(context, MainActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, "Diste un toque a la acción: " +ACTION_KEY, Toast.LENGTH_LONG).show();
        }
    }
}
