package br.dorga.mantuan.leadcaptureintegrado;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
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
    @ViewById
    Button btnCadastraCurso;
    @ViewById
    Button btnCancelaCurso;

    private Curso curso;
    private CursoDAO dao = new CursoDAO();
    int escolha = 0;

    @AfterViews
    public void preenche(){
        try{
            MainActivity_ activity = (MainActivity_) getActivity();
            curso = activity.retornaCurso();
            etnomeCurso.setText(curso.getNome());
            btnCadastraCurso.setText("Alterar");
            btnCancelaCurso.setText("Excluir");
            if (curso.getAberto() != 0){
                aberto.setChecked(true);
            }else {
                aberto.setChecked(false);
            }
        }catch (Exception e){
            btnCadastraCurso.setText("Cadastrar");
            btnCancelaCurso.setText("Limpar");
            e.printStackTrace();
        }
    }

    @Click
    void btnCadastraCurso() {
        if ("".equals(etnomeCurso.getText().toString())) {
            Toast.makeText(getActivity(), R.string.faltaCampos, Toast.LENGTH_SHORT).show();
        }else {
            if (aberto.isChecked()) {
                escolha = 1;
            }
            if (curso == null) {
                curso = new Curso(etnomeCurso.getText().toString(), escolha);
                Mensagens.msgBanco(dao.save(curso), this.getActivity().getApplication().getApplicationContext());
                curso = null;
                btnCancelaCurso();
            } else {
                curso.setAberto(escolha);
                curso.setNome(etnomeCurso.getText().toString());
                Mensagens.msgBanco(dao.update(curso), this.getActivity().getApplication().getApplicationContext());
                curso = null;
                btnCancelaCurso();
            }

        }
    }

    @Click
    void btnCancelaCurso(){
        if (curso == null) {
            etnomeCurso.setText("");
            aberto.setChecked(false);
            btnCadastraCurso.setText("Cadastrar");
            btnCancelaCurso.setText("Limpar");
        } else {
            Mensagens.msgExclusao(dao.delete(curso),this.getActivity().getApplication().getApplicationContext());
            curso = null;
            btnCancelaCurso();
        }
    }
}
