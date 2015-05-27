package br.dorga.mantuan.leadcaptureintegrado;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.ListaCidades_;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.ListaCursos_;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.ListaLeads_;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.EventoDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Evento;

@OptionsMenu(R.menu.menu_main)
@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    public FragmentManager fm = getFragmentManager();
    public static Evento evento_selecionado;
    public static Bundle args = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventoDAO eventoDAO = new EventoDAO();
        setContentView(R.layout.activity_main);
        SharedPreferences escolha_evento = getSharedPreferences("evento", Context.MODE_PRIVATE);
        evento_selecionado = eventoDAO.getEventoById(escolha_evento.getLong("id_evento",0));
        ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_style));

        ActionBar.Tab tab1 = ab.newTab();
        tab1.setText("Cidade");
        tab1.setTabListener(new NavegacaoTabs(new CadastroCidadeFragment_()));
        tab1.setTag(args);
        ab.addTab(tab1);
        ActionBar.Tab tab2 = ab.newTab();
        tab2.setText("Curso");
        tab2.setTabListener(new NavegacaoTabs(new CadastroCursoFragment_()));
        tab2.setTag(args);
        ab.addTab(tab2);
        ActionBar.Tab tab3 = ab.newTab();
        tab3.setText("Lead");
        tab3.setTabListener(new NavegacaoTabs(new CadastroLeadFragment_()));
        tab3.setTag(args);
        ab.addTab(tab3);
        ActionBar.Tab tab4 = ab.newTab();
        tab4.setText("Evento");
        tab4.setTabListener(new NavegacaoTabs(new CadastroEventoFragment_()));
        tab4.setTag(args);
        ab.addTab(tab4);

        if (savedInstanceState != null) {
            int indiceTab = savedInstanceState.getInt("indiceTab");
            getActionBar().setSelectedNavigationItem(indiceTab);
        } else {
            getActionBar().setSelectedNavigationItem(2);
        }
       // evento = escolha_evento.getLong(evento_escolhido,null);

        if (evento_selecionado == null){
            Toast.makeText(this.getApplicationContext(),R.string.selecione_evento,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {

    if (getIntent().getSerializableExtra("evento_") != null){
            args.clear();
            args.putSerializable("evento_", getIntent().getSerializableExtra("evento_"));
            getActionBar().setSelectedNavigationItem(3);
        } if (getIntent().getSerializableExtra("cidade") != null){
            args.clear();
            args.putSerializable("cidade", getIntent().getSerializableExtra("cidade"));
            getActionBar().setSelectedNavigationItem(0);
        } if (getIntent().getSerializableExtra("curso") != null){
            args.clear();
            args.putSerializable("curso",getIntent().getSerializableExtra("curso"));
            getActionBar().setSelectedNavigationItem(1);
        } if (getIntent().getSerializableExtra("lead") != null){
            args.clear();
            args.putSerializable("lead",getIntent().getSerializableExtra("lead"));
            getActionBar().setSelectedNavigationItem(2);
        }
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("indiceTab", getActionBar().getSelectedNavigationIndex());
    }

    private class NavegacaoTabs implements ActionBar.TabListener {

        private Fragment frag;
        public NavegacaoTabs(Fragment frag){
            this.frag = frag;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            FragmentTransaction fts = fm.beginTransaction();
            if (!args.isEmpty()) {
                frag.setArguments(args);
            }
            fts.replace(R.id.fragmentContainer,frag);
            fts.commit();
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            FragmentTransaction fts = fm.beginTransaction();
            fts.remove(frag);
            fts.commit();
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            FragmentTransaction fts = fm.beginTransaction();
            fts.replace(R.id.fragmentContainer, frag);
            fts.commit();
        }
    }

    @OptionsItem(R.id.n1)
    public void n1(){
        startActivity(new Intent(this,SelecionaEventoFragment_.class));
    }
    @OptionsItem(R.id.n2)
    public void n2(){
        startActivity(new Intent(this,ListaCidades_.class));
    }
    @OptionsItem(R.id.n3)
    public void n3(){
        startActivity(new Intent(this, ListaCursos_.class));
    }
    @OptionsItem(R.id.n4)
    public void n4(){
        startActivity(new Intent(this, ListaLeads_.class));
    }
    @OptionsItem(R.id.n5)
    public void n5(){

    }
  }
