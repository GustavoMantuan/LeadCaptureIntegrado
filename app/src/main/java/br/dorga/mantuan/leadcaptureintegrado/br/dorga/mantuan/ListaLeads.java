package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;
import java.util.Map;

import br.dorga.mantuan.leadcaptureintegrado.MainActivity_;
import br.dorga.mantuan.leadcaptureintegrado.R;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.LeadDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Lead;

@EActivity(R.layout.activity_lista_leads)
public class ListaLeads extends Activity {

    @ViewById
    ListView listView;

    @AfterViews
    public void listagem(){
        final LeadDAO leadDAO = new LeadDAO();
        final List<Map<String, Object>> leads = leadDAO.list(leadDAO.list());

        String[] de = {"nome", "email"};
        int[] para = {R.id.p1, R.id.p2};
        SimpleAdapter adapter = new SimpleAdapter(this, leadDAO.list(leadDAO.list()), R.layout.listagem, de, para);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = leads.get(position);
                Long ide = (Long) item.get("id");
                Lead lead = leadDAO.getLeadById(ide);
                Intent alteraLead = new Intent(getApplicationContext(), MainActivity_.class);
                alteraLead.putExtra("lead", lead);
                startActivity(alteraLead);
            }
        });
    }

}
