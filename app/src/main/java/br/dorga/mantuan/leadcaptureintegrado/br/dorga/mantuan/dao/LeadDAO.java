package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Lead;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils.Conversor;

/**
 * Created by Dorga on 17/05/2015.
 */
public class LeadDAO extends  Connection<Lead> {

    @Override
    public long save(Lead lead) {
        ContentValues cv = new ContentValues();
        cv.put("nome",lead.getNome());
        cv.put("email",lead.getEmail());
        cv.put("telefoneRes",lead.getTelefonetel());
        cv.put("telefoneCel",lead.getTelefonecel());
        cv.put("nascimento",lead.getNascimento().toString());
        cv.put("endereco",lead.getLougradouro());
        cv.put("numero",lead.getNumeroEnd());
        cv.put("bairro",lead.getBairro());
        cv.put("fies",lead.getFies());
        cv.put("prouni",lead.getProuni());
        cv.put("percProuni",lead.getPercProuni());
        cv.put("face",lead.getFacebook());
        cv.put("enen",lead.getEnem());
        cv.put("notaEnen",lead.getNotaEnem());
        cv.put("dataLead",lead.getDataLead().toString());
        cv.put("idCidade",lead.getCidade());
        cv.put("idCurso1",lead.getCurso1());
        cv.put("idCurso2",lead.getCurso2());
        cv.put("idEvento",lead.getEvento());
        return getDatabase().insert("Lead",null,cv);
    }

    @Override
    public long delete(Lead lead) {
        return 0;
    }

    @Override
    public long update(Lead lead) {
        return 0;
    }
    
    @Override
    public List<Lead> list() {
        Cursor c = getDatabase().query(false,"Lead",new String[]{"id,nome,email,telefoneRes,telefoneCel,nascimento,endereco,numero,bairro," +
                "fies,prouni,percProuni,face,enen,notaEnen,dataLead,idCidade,idCurso1,idCurso2,idEvento" +
                ""},null,null,null,null,null,null);
        ArrayList<Lead> leads = new ArrayList<Lead>();
        while (c.moveToNext()){
            Lead lead = new Lead(c.getLong(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),Conversor.stringParaDate(c.getString(5)),Conversor.stringParaDate(c.getString(6)),c.getString(7),c.getString(8),c.getString(9),c.getString(10),c.getLong(11),c.getLong(12),c.getInt(13),c.getInt(14),c.getDouble(15),c.getInt(16),c.getLong(17),c.getLong(18),c.getDouble(19));
            leads.add(lead);
        }
        c.close();
        return leads;
    }

    public List<Map<String, Object>> list(List<Lead> eventos){
        List<Map<String, Object>> listaLeads = new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        for (Lead e : eventos){
            item = new HashMap<String, Object>();
            item.put("id",e.get_id());
            item.put("nome",e.getNome());
            item.put("email", e.getEmail());
            listaLeads.add(item);
        }
        return listaLeads;
    }

    public Lead getLeadById(long id_lead) {
        Cursor c = getDatabase().rawQuery("SELECT id, nome, email, telefoneRes, telefoneCel, nascimento, endereco, numero, bairro, fies," +
                "prouni, percProuni, face, enen, notaEnen, dataLead, idCurso1, idCurso2, idEvento FROM Lead WHERE id = ?",new String[]{String.valueOf(id_lead)});
        Lead lead = null;
        if (c.moveToNext()){
            lead = new Lead(c.getLong(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),Conversor.stringParaDate(c.getString(5)),Conversor.stringParaDate(c.getString(6)),c.getString(7),c.getString(8),c.getString(9),c.getString(10),c.getLong(11),c.getLong(12),c.getInt(13),c.getInt(14),c.getDouble(15),c.getInt(16),c.getLong(17),c.getLong(18),c.getDouble(19));
        }
        return lead;

    }



}
