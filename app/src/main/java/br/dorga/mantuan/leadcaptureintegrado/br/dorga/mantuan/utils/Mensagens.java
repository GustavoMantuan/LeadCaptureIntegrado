package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils;

import android.content.Context;
import android.widget.Toast;

import br.dorga.mantuan.leadcaptureintegrado.R;

/**
 * Created by Dorga on 05/05/2015.
 */
public class Mensagens {
    public static void msgBanco(long r, Context x){
        if (r != -1){
            Toast.makeText(x, R.string.cadastroSucesso, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(x, R.string.cadastroErro,Toast.LENGTH_SHORT).show();
        }
    }

}
