package br.dorga.mantuan.leadcaptureintegrado;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.dorga.mantuan.leadcaptureintegrado.R;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.EventoDAO;

@EActivity(R.layout.activity_seleciona_curso)
public class SelecionaCursoActivity extends Activity {

    @ViewById
    ListView listView;

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDAO = new EventoDAO();
        String[] de = {"id","nome","data"};
        int[] para = {R.id.idEvento,R.id.nomeEvento,R.id.dtEvento};
        SimpleAdapter adapter = new SimpleAdapter(this, eventoDAO.list(eventoDAO.list()),R.layout.lista_evento,de, para);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
