package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return getDatabase().delete("Curso", "id = ?", new String[]{String.valueOf(curso.get_id())});
    }

    @Override
    public long update(Curso curso) {
        ContentValues cv = new ContentValues();
        cv.put("nome",curso.getNome());
        cv.put("aberto",curso.getAberto());
        return getDatabase().update("Curso",cv, "id = ?", new String[]{String.valueOf(curso.get_id())});
    }

    @Override
    public List<Curso> list() {
        Cursor c = getDatabase().query(false,"Curso",new String[]{"id,nome,aberto"},null,null,null,null,null,null);
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        while (c.moveToNext()){
            Curso curso = new Curso(c.getLong(0),c.getString(1), c.getInt(2));
            cursos.add(curso);
        }
        c.close();
        return cursos;
    }

    public List<Map<String, Object>> list(List<Curso> cursos){
        List<Map<String, Object>> listaCursos = new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        for (Curso e : cursos){
            item = new HashMap<String, Object>();
            item.put("id",e.get_id());
            item.put("nome",e.getNome());
            item.put("aberto", e.getAberto() != 0 ? "Aberto" : "Fechado");
            listaCursos.add(item);
        }
        return listaCursos;
    }

    public Curso getCursoById(Long ide) {
        Cursor c = getDatabase().rawQuery("SELECT id, nome, aberto FROM Curso WHERE id = ?", new String[]{ide.toString()});
        Curso selecionada = new Curso();
        if (c.moveToNext()){
            selecionada.set_id(c.getLong(0));
            selecionada.setNome(c.getString(1));
            selecionada.setAberto(c.getInt(2));
        }
        return selecionada;
    }
}
