package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import br.dorga.mantuan.leadcaptureintegrado.R;

/**
 * Created by Dorga on 02/05/2015.
 */
public class CidadeDAO extends ConectaBanco {

    public void cadastraCidade(Cidade c, Context x){
        ContentValues cv = new ContentValues();
        cv.put("nome",c.getNome());
        cv.put("estado",c.getEstado());
        long r = getDatabase().insert("CIDADE",null,cv);
        if (r != -1){
            Toast.makeText(x, R.string.cadastroSucesso,Toast.LENGTH_SHORT);
        }
    }

}
