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
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.CursoDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Curso;

@EActivity(R.layout.activity_lista_cursos)
public class ListaCursos extends Activity {

    @ViewById
    ListView listView;

    @AfterViews
    public void listagem(){
        final CursoDAO cursoDAO = new CursoDAO();
        final List<Map<String, Object>> cursos = cursoDAO.list(cursoDAO.list());

        String[] de = {"aberto", "nome"};
        int[] para = {R.id.p1, R.id.p2};
        SimpleAdapter adapter = new SimpleAdapter(this, cursoDAO.list(cursoDAO.list()), R.layout.listagem, de, para);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = cursos.get(position);
                Long ide = (Long) item.get("id");
                Curso curso = cursoDAO.getCursoById(ide);
                Intent alteraCurso = new Intent(getApplicationContext(), MainActivity_.class);
                alteraCurso.putExtra("curso", curso);
                startActivity(alteraCurso);
            }
        });
    }

}
