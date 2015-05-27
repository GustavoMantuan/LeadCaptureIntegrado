package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import br.dorga.mantuan.leadcaptureintegrado.R;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Evento;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils.Conversor;

/**
 * Created by Dorga on 03/05/2015.
 */
public class EventoDAO extends Connection<Evento> {

    @Override
    public long save(Evento e) {
        ContentValues cv = new ContentValues();
        cv.put("nome",e.getNome());
        cv.put("data",Conversor.dateParaString(e.getData()));
        return getDatabase().insert("Evento",null,cv);
    }

    @Override
    public long delete(Evento evento) {
        return getDatabase().delete("Evento", "id = ?", new String[]{String.valueOf(evento.get_id())});
    }

    @Override
    public long update(Evento evento) {
        ContentValues cv = new ContentValues();
        cv.put("nome",evento.getNome());
        cv.put("data",Conversor.dateParaString(evento.getData()));
        return getDatabase().update("Evento",cv, "id = ?", new String[]{String.valueOf(evento.get_id())});
    }

    @Override
    public List<Evento> list() {
       Cursor c = getDatabase().query(false,"Evento",new String[]{"id,nome,data"},null,null,null,null,null,null);
       ArrayList<Evento> eventos = new ArrayList<Evento>();
       while (c.moveToNext()){
           Evento evento = new Evento(c.getLong(0),c.getString(1),Conversor.stringParaDate(c.getString(2)));
           eventos.add(evento);
       }
        c.close();
        return eventos;
    }

    public List<Map<String, Object>> list(List<Evento> eventos){
        List<Map<String, Object>> listaEventos = new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        for (Evento e : eventos){
            item = new HashMap<String, Object>();
            item.put("id",e.get_id());
            item.put("nome",e.getNome());
            item.put("data", Conversor.dateParaString(e.getData()));
            listaEventos.add(item);
        }
        return listaEventos;
    }

    public Evento getEventoById(long id_evento) {
        Cursor c = getDatabase().rawQuery("SELECT id, nome, data FROM Evento WHERE ID = ?",new String[]{String.valueOf(id_evento)});
        Evento evento = null;
        if (c.moveToNext()){
            evento = new Evento(c.getLong(0),c.getString(1),Conversor.stringParaDate(c.getString(2)));
        }
        return evento;

    }
}
