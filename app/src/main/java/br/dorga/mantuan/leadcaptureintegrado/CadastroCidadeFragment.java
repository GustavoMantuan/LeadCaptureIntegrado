package br.dorga.mantuan.leadcaptureintegrado;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Cidade;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.CidadeDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils.Mensagens;

@EFragment(R.layout.fragment_cadastro_cidade)
public class CadastroCidadeFragment extends Fragment {

    @ViewById(R.id.etnomeCidade)
    EditText cidade;
    @ViewById(R.id.etsiglaEstado)
    Spinner estado;
    @ViewById
    Button btnCadastraCidade;
    @ViewById
    Button btnCancelaCidade;

    private String c,e;

    public CidadeDAO cidadeDAO = new CidadeDAO();
    private Cidade cid;

    @AfterViews
    public void preenche(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.estados,android.R.layout.simple_spinner_dropdown_item);
        estado.setAdapter(adapter);
       try{
           MainActivity_ activity = (MainActivity_) getActivity();
           cid = activity.retornaCidade();
           cidade.setText(cid.getNome());
           btnCadastraCidade.setText("Alterar");
           btnCancelaCidade.setText("Excluir");
        }catch (Exception e){
           btnCadastraCidade.setText("Cadastrar");
           btnCancelaCidade.setText("Limpar");
           e.printStackTrace();
       }
    }

    @Click(R.id.btnCadastraCidade)
    void btncadastraCidade() {
        c = cidade.getText().toString();
        e = estado.getSelectedItem().toString();

        if ("".equals(c) || "".equals(e)) {
            Toast.makeText(getActivity(), R.string.faltaCampos, Toast.LENGTH_SHORT).show();
        }else {
            if (cid == null){
                cid = new Cidade(c, e);
                Mensagens.msgBanco(cidadeDAO.save(cid), this.getActivity().getApplication().getApplicationContext());
                cid = null;
                btnCancelaCidade();
            }else {
                cid.setNome(c);
                cid.setEstado(e);
                Mensagens.msgBanco(cidadeDAO.update(cid), this.getActivity().getApplication().getApplicationContext());
                cid = null;
                btnCancelaCidade();
            }
        }
    }


    @Click
    void btnCancelaCidade() {
        if (cid == null) {
            cidade.setText("");
            btnCadastraCidade.setText("Cadastrar");
            btnCancelaCidade.setText("Limpar");
        } else {
            Mensagens.msgExclusao(cidadeDAO.delete(cid),this.getActivity().getApplication().getApplicationContext());
            cid = null;
            btnCancelaCidade();
          }
    }
}
