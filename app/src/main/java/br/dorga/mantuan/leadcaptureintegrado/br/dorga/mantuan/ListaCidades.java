package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;
import java.util.Map;

import br.dorga.mantuan.leadcaptureintegrado.MainActivity_;
import br.dorga.mantuan.leadcaptureintegrado.R;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.CidadeDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Cidade;

@EActivity(R.layout.lista_cidades)
public class ListaCidades extends Activity {

    @ViewById
    ListView listView;

    @AfterViews
    void listagem(){
        final CidadeDAO cidadeDAO = new CidadeDAO();
        final List<Map<String, Object>> cidades = cidadeDAO.list(cidadeDAO.list());

        String[] de = {"estado", "nome"};
        int[] para = {R.id.p1, R.id.p2};
        SimpleAdapter adapter = new SimpleAdapter(this, cidadeDAO.list(cidadeDAO.list()), R.layout.listagem, de, para);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = cidades.get(position);
                Long ide = (Long) item.get("id");
                Cidade cidade = cidadeDAO.getCidadeById(ide);
                Intent alteraCidade = new Intent(getApplicationContext(), MainActivity_.class);
                alteraCidade.putExtra("cidade", cidade);
                startActivity(alteraCidade);
               }
        });
    }
}
