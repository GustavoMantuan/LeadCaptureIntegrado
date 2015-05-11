package br.dorga.mantuan.leadcaptureintegrado;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Curso;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.CursoDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils.Mensagens;

@EFragment(R.layout.fragment_cadastro_curso)
public class CadastroCursoFragment extends Fragment {

    @ViewById(R.id.etnomeCurso)
    EditText etnomeCurso;
    @ViewById(R.id.aberto)
    Switch aberto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro_curso, container, false);
    }


    @Click(R.id.btnCadastraCurso)
    void btnCadastraCurso(){
        if (etnomeCurso.getText().toString() != null && !"".equals(etnomeCurso.getText().toString())){
            int escolha = 0;
            if (aberto.isChecked()){
                escolha = 1;
            }
            Curso c = new Curso(etnomeCurso.getText().toString(),escolha);
            CursoDAO cid = new CursoDAO();
            Mensagens.msgBanco(cid.save(c), this.getActivity().getApplication().getApplicationContext());
        }else {
            Toast.makeText(getActivity(),R.string.faltaCampos,Toast.LENGTH_SHORT).show();
        }
    }

    @Click
    void btnCancelaCurso(){
        etnomeCurso.setText("");
        aberto.setChecked(false);
    }
}
