package br.dorga.mantuan.leadcaptureintegrado;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    EditText estado;
    @ViewById
    Button btnCadastraCidade;
    @ViewById
    Button btnCancelaCidade;

    public CidadeDAO cidadeDAO = new CidadeDAO();
    private Cidade cid;

    @AfterViews
    public void preenche(){
       try{
            cid = (Cidade) getArguments().getSerializable("cidade");
            cidade.setText(cid.getNome());
            estado.setText(cid.getEstado());
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
        if ("".equals(cidade.getText().toString()) || "".equals(estado.getText().toString())) {
            Toast.makeText(getActivity(), R.string.faltaCampos, Toast.LENGTH_SHORT).show();
        }else {
            if (cid == null){
                cid = new Cidade(cidade.getText().toString(), estado.getText().toString());
                Mensagens.msgBanco(cidadeDAO.save(cid), this.getActivity().getApplication().getApplicationContext());
                cid = null;
                btnCancelaCidade();
            }else {
                cid.setNome(cidade.getText().toString());
                cid.setEstado(estado.getText().toString());
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
            estado.setText("");
            btnCadastraCidade.setText("Cadastrar");
            btnCancelaCidade.setText("Limpar");
        } else {
            Mensagens.msgExclusao(cidadeDAO.delete(cid),this.getActivity().getApplication().getApplicationContext());
            cid = null;
            btnCancelaCidade();
          }
    }
}
