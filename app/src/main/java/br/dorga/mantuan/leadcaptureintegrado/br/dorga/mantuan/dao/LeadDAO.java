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
        cv.put("telefoneRes",lead.getTelefoneRes());
        cv.put("telefoneCel",lead.getTelefoneRes());
        cv.put("nascimento",lead.getNascimento().toString());
        cv.put("endereco",lead.getEndereco());
        cv.put("numero",lead.getNumero());
        cv.put("bairro",lead.getBairro());
        cv.put("fies",lead.getFies());
        cv.put("prouni",lead.getProuni());
        cv.put("percProuni",lead.getPercProuni());
        cv.put("face",lead.getFacebook());
        cv.put("enen",lead.getEnem());
        cv.put("notaEnen",lead.getNotaEnem());
        cv.put("dataLead",lead.getDataLead().toString());
        cv.put("idCidade",lead.getCidade().get_id());
        cv.put("idCurso1",lead.getCurso1().get_id());
        cv.put("idCurso2",lead.getCurso2().get_id());
        cv.put("idEvento",lead.getEvento().get_id());
        return getDatabase().insert("Lead",null,cv);
    }

    @Override
    public long delete(Lead lead) {
        return getDatabase().delete("Lead", "id = ?", new String[]{String.valueOf(lead.get_id())});
    }

    @Override
    public long update(Lead lead) {
        ContentValues cv = new ContentValues();
        cv.put("nome",lead.getNome());
        cv.put("email",lead.getEmail());
        cv.put("telefoneRes",lead.getTelefoneRes());
        cv.put("telefoneCel",lead.getTelefoneRes());
        cv.put("nascimento",lead.getNascimento().toString());
        cv.put("endereco",lead.getEndereco());
        cv.put("numero",lead.getNumero());
        cv.put("bairro",lead.getBairro());
        cv.put("fies",lead.getFies());
        cv.put("prouni",lead.getProuni());
        cv.put("percProuni",lead.getPercProuni());
        cv.put("face",lead.getFacebook());
        cv.put("enen",lead.getEnem());
        cv.put("notaEnen",lead.getNotaEnem());
        cv.put("dataLead",lead.getDataLead().toString());
        cv.put("idCidade",lead.getCidade().get_id());
        cv.put("idCurso1",lead.getCurso1().get_id());
        cv.put("idCurso2",lead.getCurso2().get_id());
        cv.put("idEvento",lead.getEvento().get_id());
        return getDatabase().update("Lead",cv, "id = ?", new String[]{String.valueOf(lead.get_id())});
    }
    
    @Override
    public List<Lead> list() {
        Cursor c = getDatabase().query(false,"Lead",new String[]{"id,nome,email,telefoneRes,telefoneCel,nascimento,endereco,numero,bairro," +
                "fies,prouni,percProuni,face,enen,notaEnen,dataLead,idCidade,idCurso1,idCurso2,idEvento" +
                ""},null,null,null,null,null,null);
        ArrayList<Lead> leads = new ArrayList<Lead>();
        while (c.moveToNext()){
            Lead lead = new Lead(c.getLong(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),Conversor.stringParaDate(c.getString(5)),c.getString(6),c.getString(7),c.getString(8),c.getInt(9),c.getInt(10),c.getDouble(11),c.getString(12),c.getInt(13),c.getDouble(14),Conversor.stringParaDate(c.getString(15)),new CidadeDAO().getCidadeById(c.getLong(16)),new CursoDAO().getCursoById(c.getLong(17)),new CursoDAO().getCursoById(c.getLong(18)),new EventoDAO().getEventoById(c.getLong(19)));
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
                "prouni, percProuni, face, enen, notaEnen, dataLead, idCidade, idCurso1, idCurso2, idEvento FROM Lead WHERE id = ?",new String[]{String.valueOf(id_lead)});
        Lead lead = null;
        if (c.moveToNext()){
            lead = new Lead(c.getLong(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),Conversor.stringParaDate(c.getString(5)),c.getString(6),c.getString(7),c.getString(8),c.getInt(9),c.getInt(10),c.getDouble(11),c.getString(12),c.getInt(13),c.getDouble(14),Conversor.stringParaDate(c.getString(15)),new CidadeDAO().getCidadeById(c.getLong(16)),new CursoDAO().getCursoById(c.getLong(17)),new CursoDAO().getCursoById(c.getLong(18)),new EventoDAO().getEventoById(c.getLong(19)));
        }
        return lead;

    }


    public Lead getLeadByEmail(String email) {
        Cursor c = getDatabase().rawQuery("SELECT id, nome, email, telefoneRes, telefoneCel, nascimento, endereco, numero, bairro, fies," +
                "prouni, percProuni, face, enen, notaEnen, dataLead, idCidade, idCurso1, idCurso2, idEvento FROM Lead WHERE email = ?",new String[]{email});
        Lead lead = null;
        if (c.moveToNext()){
            lead = new Lead(c.getLong(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),Conversor.stringParaDate(c.getString(5)),c.getString(6),c.getString(7),c.getString(8),c.getInt(9),c.getInt(10),c.getDouble(11),c.getString(12),c.getInt(13),c.getDouble(14),Conversor.stringParaDate(c.getString(15)),new CidadeDAO().getCidadeById(c.getLong(16)),new CursoDAO().getCursoById(c.getLong(17)),new CursoDAO().getCursoById(c.getLong(18)),new EventoDAO().getEventoById(c.getLong(19)));
        }
        return lead;
    }

    public Lead getLeadByFone(String fone) {
        Cursor c = getDatabase().rawQuery("SELECT id, nome, email, telefoneRes, telefoneCel, nascimento, endereco, numero, bairro, fies," +
                "prouni, percProuni, face, enen, notaEnen, dataLead, idCidade, idCurso1, idCurso2, idEvento FROM Lead WHERE telefoneRes = ? or telefoneCel = ?",new String[]{fone,fone});
        Lead lead = null;
        if (c.moveToNext()){
            lead = new Lead(c.getLong(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),Conversor.stringParaDate(c.getString(5)),c.getString(6),c.getString(7),c.getString(8),c.getInt(9),c.getInt(10),c.getDouble(11),c.getString(12),c.getInt(13),c.getDouble(14),Conversor.stringParaDate(c.getString(15)),new CidadeDAO().getCidadeById(c.getLong(16)),new CursoDAO().getCursoById(c.getLong(17)),new CursoDAO().getCursoById(c.getLong(18)),new EventoDAO().getEventoById(c.getLong(19)));
        }
        return lead;
    }
}
