package br.dorga.mantuan.leadcaptureintegrado;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.http.SslCertificate;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.ConectaBanco;


public class MainActivity extends FragmentActivity {

    FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab3 = ab.newTab();
        tab3.setText("Lead");
        // tab2.setIcon(R.drawable.ic_action_new);
        tab3.setTabListener(new NavegacaoTabs(new CadastroLeadFragment()));
        ab.addTab(tab3, false);

        ActionBar.Tab tab1 = ab.newTab();
        tab1.setText("Cidade");
        // tab1.setIcon(R.mipmap.ic_action_new);
        tab1.setTabListener(new NavegacaoTabs(new CadastroCidadeFragment()));
        ab.addTab(tab1, false);

        ActionBar.Tab tab2 = ab.newTab();
        tab2.setText("Curso");
        // tab2.setIcon(R.drawable.ic_action_new);
        tab2.setTabListener(new NavegacaoTabs(new CadastroCursoFragment()));
        ab.addTab(tab2, false);



        if (savedInstanceState != null) {
            int indiceTab = savedInstanceState.getInt("indiceTab");
            getActionBar().setSelectedNavigationItem(indiceTab);
        } else {
            getActionBar().setSelectedNavigationItem(0);
        }

            }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("indiceTab",getActionBar().getSelectedNavigationIndex());

    }

    private class NavegacaoTabs implements ActionBar.TabListener {

        private Fragment frag;

        public NavegacaoTabs(Fragment frag){
            this.frag = frag;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            FragmentTransaction fts = fm.beginTransaction();
            fts.replace(R.id.fragmentContainer,frag,"frag1");
            fts.addToBackStack("pilha");
            fts.commit();
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            FragmentTransaction fts = fm.beginTransaction();
            fts.remove(frag);
            fts.addToBackStack("pilha");
            fts.commit();
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            Log.i("Script", "OnTabReselected");
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.n1) {

            FragmentTransaction fts = fm.beginTransaction();
            fts.remove(fm.findFragmentByTag("frag1"));
            Fragment evento = new CadastroEventoFragment();
            fts.replace(R.id.fragmentContainer,evento,"frag1");
            fts.addToBackStack("pilha");
            fts.commit();

        }

        return super.onOptionsItemSelected(item);
    }
}
