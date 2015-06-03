package br.dorga.mantuan.leadcaptureintegrado;

import android.app.ActionBar;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.ListaCidades_;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.ListaCursos_;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.ListaLeads_;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.EventoDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Cidade;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Curso;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Evento;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Lead;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils.SwipeAdapter;

@OptionsMenu(R.menu.menu_main)
@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    @ViewById
    ViewPager pager;
    public static Evento evento_selecionado;
    private SwipeAdapter mAdapter;
    private ActionBar ab;
    private Bundle args = new Bundle();
    private String[] tabs = {"Cidade","Curso","Evento","Lead"};


    public Cidade retornaCidade(){
        Cidade c = (Cidade) getIntent().getSerializableExtra("cidade");
        return c;
    }
    public Curso retornaCurso(){
        Curso c = (Curso) getIntent().getSerializableExtra("curso");
        return c;
    }
    public Evento retornaEvento(){
        Evento e = (Evento) getIntent().getSerializableExtra("evento_");
        return e;
    }
    public Lead retornaLead(){
        Lead l = (Lead) getIntent().getSerializableExtra("lead");
        return l;
    }

    @AfterViews
    void after(){
        ab = getActionBar();
        mAdapter = new SwipeAdapter(getSupportFragmentManager());
        pager.setAdapter(mAdapter);
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.setHomeButtonEnabled(false);
        ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_style));
        EventoDAO eventoDAO = new EventoDAO();
        SharedPreferences escolha_evento = getSharedPreferences("evento", Context.MODE_PRIVATE);
        evento_selecionado = eventoDAO.getEventoById(escolha_evento.getLong("id_evento", 0));
        for (String tab : tabs){
            ActionBar.Tab tb = ab.newTab();
            tb.setText(tab);
            tb.setTabListener(this);
            ab.addTab(tb);
            }
        if (evento_selecionado == null) {
            Toast.makeText(this.getApplicationContext(), R.string.selecione_evento, Toast.LENGTH_SHORT).show();
        }

        if (getIntent().getSerializableExtra("evento_") != null){
            retornaEvento();
            getActionBar().setSelectedNavigationItem(2);
            mAdapter.getItem(2);
            }
        else if (getIntent().getSerializableExtra("cidade") != null ){
            retornaCidade();
            getActionBar().setSelectedNavigationItem(0);
            mAdapter.getItem(0);
            Log.e("veio cidade","veio cidade");
        }
        else if (getIntent().getSerializableExtra("curso") != null){
            retornaCurso();
            getActionBar().setSelectedNavigationItem(1);
            mAdapter.getItem(1);
        }else if (getIntent().getSerializableExtra("lead") != null){
            retornaLead();
            getActionBar().setSelectedNavigationItem(3);
            mAdapter.getItem(3);
        }else {
            getActionBar().setSelectedNavigationItem(3);
        }
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                ab.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }



//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("indiceTab", getActionBar().getSelectedNavigationIndex());
//    }


    @OptionsItem(R.id.n1)
    public void n1() {
        startActivity(new Intent(this, SelecionaEventoFragment_.class));
    }

    @OptionsItem(R.id.n2)
    public void n2() {
        startActivity(new Intent(this, ListaCidades_.class));
    }

    @OptionsItem(R.id.n3)
    public void n3() {
        startActivity(new Intent(this, ListaCursos_.class));
    }

    @OptionsItem(R.id.n4)
    public void n4() {
        startActivity(new Intent(this, ListaLeads_.class));
    }

    @OptionsItem(R.id.n5)
    public void n5() {

    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        //
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }


}
