package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Cidade;

/**
 * Created by Dorga on 02/05/2015.
 */

public class CidadeDAO extends Connection<Cidade> {

    @Override
    public long save(Cidade c) {
        ContentValues cv = new ContentValues();
        cv.put("nome",c.getNome());
        cv.put("estado",c.getEstado());
        return getDatabase().insert("Cidade", null, cv);

    }

    @Override
    public long delete(Cidade cidade) {
        return getDatabase().delete("Cidade","id = ?",new String[]{String.valueOf(cidade.get_id())});
    }

    @Override
    public long update(Cidade cidade) {
    return 0;
    }

    @Override
    public List<Cidade> list() {
        return null;
    }
}
