package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import java.util.List;

import br.dorga.mantuan.leadcaptureintegrado.R;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Curso;

/**
 * Created by Dorga on 03/05/2015.
 */
public class CursoDAO extends Connection<Curso> {

    @Override
    public long save(Curso c) {
        ContentValues cv = new ContentValues();
        cv.put("nome",c.getNome());
        cv.put("aberto",c.getAberto());
        return getDatabase().insert("Curso",null,cv);

    }

    @Override
    public long delete(Curso curso) {
return 0;
    }

    @Override
    public long update(Curso curso) {
return 0;
    }

    @Override
    public List<Curso> list() {
        return null;
    }
}
