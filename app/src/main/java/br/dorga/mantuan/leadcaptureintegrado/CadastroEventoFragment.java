package br.dorga.mantuan.leadcaptureintegrado;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
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
    @ViewById
    Button btnCadastraEvento;
    @ViewById
    Button btnCancelaEvento;
    private Evento evento;
    private EventoDAO dao =  new EventoDAO();;

    @AfterViews
    public void preenche(){
        try {
            evento = (Evento) getArguments().getSerializable("evento_");
            etnomeEvento.setText(evento.getNome());
            btnCadastraEvento.setText("Alterar");
            btnCancelaEvento.setText("Excluir");
        }catch (Exception e){
            btnCadastraEvento.setText("Cadastrar");
            btnCancelaEvento.setText("Limpar");
            e.printStackTrace();
        }
    }

    @Click
    void btnCadastraEvento() {

        if ("".equals(etnomeEvento.getText().toString()) || "".equals(etnomeEvento.getText().toString())) {
            Toast.makeText(getActivity(), R.string.faltaCampos, Toast.LENGTH_SHORT).show();
        } else {
           if (evento == null) {
               evento = new Evento(etnomeEvento.getText().toString(),new Date(dataEvento.getYear() - 1900, dataEvento.getMonth(), dataEvento.getDayOfMonth()));
               Mensagens.msgBanco(dao.save(evento), this.getActivity().getApplication().getApplicationContext());
               evento = null;
               btnCancelaEvento();
           }else {
               evento.setNome(etnomeEvento.getText().toString());
               evento.setData(new Date(dataEvento.getYear() - 1900, dataEvento.getMonth(), dataEvento.getDayOfMonth()));
               Mensagens.msgBanco(dao.update(evento), this.getActivity().getApplication().getApplicationContext());
               evento = null;
               btnCancelaEvento();
            }
        }
    }
    @Click
    void btnCancelaEvento(){
        if (evento == null){
            btnCadastraEvento.setText("Cadastrar");
            btnCancelaEvento.setText("Limpar");
            etnomeEvento.setText("");
        } else {
            Mensagens.msgExclusao(dao.delete(evento),this.getActivity().getApplication().getApplicationContext());
            evento = null;
            btnCancelaEvento();
         }
    }



}
