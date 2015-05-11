package br.dorga.mantuan.leadcaptureintegrado;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Evento;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.EventoDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils.Mensagens;

@EFragment(R.layout.fragment_cadastro_evento)
public class CadastroEventoFragment extends Fragment {

    @ViewById
    EditText etnomeEvento;
    @ViewById
    DatePicker dataEvento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro_evento, container, false);
    }

    @Click
    void btnCadastraEvento(){
        if (etnomeEvento.getText().toString() != null && !"".equals(etnomeEvento.getText().toString())){
            Date d = new Date(dataEvento.getYear(),dataEvento.getMonth(),dataEvento.getDayOfMonth());
            Evento e = new Evento(etnomeEvento.getText().toString(),d);
            EventoDAO dao = new EventoDAO();
            Mensagens.msgBanco(dao.save(e), this.getActivity().getApplication().getApplicationContext());
        }else {
            Toast.makeText(getActivity(),R.string.faltaCampos,Toast.LENGTH_SHORT).show();
        }
    }
    @Click
    void btnCancelaEvento(){
        etnomeEvento.setText("");
    }



}
