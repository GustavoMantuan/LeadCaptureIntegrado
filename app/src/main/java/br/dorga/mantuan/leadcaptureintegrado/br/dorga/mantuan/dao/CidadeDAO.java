package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Cidade;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Cidade;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils.Conversor;

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
        return getDatabase().delete("Cidade", "id = ?", new String[]{String.valueOf(cidade.get_id())});
    }


    @Override
    public long update(Cidade cidade) {
        ContentValues cv = new ContentValues();
        cv.put("nome",cidade.getNome());
        cv.put("estado",cidade.getEstado());
        return getDatabase().update("Cidade",cv, "id = ?", new String[]{String.valueOf(cidade.get_id())});

    }
    @Override
    public List<Cidade> list() {
        Cursor c = getDatabase().query(false,"Cidade",new String[]{"id,nome,estado"},null,null,null,null,null,null);
        ArrayList<Cidade> cidades = new ArrayList<Cidade>();
        while (c.moveToNext()){
            Cidade cidade = new Cidade(c.getLong(0),c.getString(1), c.getString(2));
            cidades.add(cidade);
        }
        c.close();
        return cidades;
    }

    public List<Map<String, Object>> list(List<Cidade> cidades){
        List<Map<String, Object>> listaCidades = new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        for (Cidade e : cidades){
            item = new HashMap<String, Object>();
            item.put("id",e.get_id());
            item.put("nome",e.getNome());
            item.put("estado", e.getEstado());
            listaCidades.add(item);
        }
        return listaCidades;
    }

    public Cidade getCidadeById(long ide) {
      Cursor c = getDatabase().rawQuery("SELECT id, nome, estado FROM Cidade WHERE id = ?", new String[]{String.valueOf(ide)});
        Cidade selecionada = new Cidade();
        if (c.moveToNext()){
            selecionada.set_id(c.getLong(0));
            selecionada.setNome(c.getString(1));
            selecionada.setEstado(c.getString(2));
        }
        return selecionada;

    }
}
