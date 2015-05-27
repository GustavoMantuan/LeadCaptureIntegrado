package br.dorga.mantuan.leadcaptureintegrado;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.EventoDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Evento;

@EActivity(R.layout.fragment_seleciona_evento)
public class SelecionaEventoFragment extends Activity {

    @ViewById
    ListView listView;

    @AfterViews
    void after(){
        final EventoDAO eventoDAO = new EventoDAO();
        final List<Map<String, Object>> eventos = eventoDAO.list(eventoDAO.list());
        final SharedPreferences escolha = getSharedPreferences("evento", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = escolha.edit();

        String[] de = {"nome", "data"};
        int[] para = {R.id.p1, R.id.p2};
        SimpleAdapter adapter = new SimpleAdapter(this, eventoDAO.list(eventoDAO.list()), R.layout.lista_evento, de, para);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = eventos.get(position);
                Long ide = (Long) item.get("id");
                editor.putLong("id_evento", ide);
                editor.apply();
                Toast.makeText(getApplicationContext(), R.string.evento_selecionado, Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = eventos.get(position);
                Long ide = (Long) item.get("id");
                Evento evento = eventoDAO.getEventoById(ide);
                Intent alteraEvento = new Intent(getApplicationContext(), MainActivity_.class);
                alteraEvento.putExtra("evento_", evento);
                startActivity(alteraEvento);
                return true;
            }
        });
    }
}
