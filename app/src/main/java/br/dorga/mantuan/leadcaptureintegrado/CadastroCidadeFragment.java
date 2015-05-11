package br.dorga.mantuan.leadcaptureintegrado;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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

    @Click(R.id.btnCadastraCidade)
    void btncadastraCidade() {
        if (cidade.getText().toString() != null && estado.getText().toString() != null && !"".equals(cidade.getText().toString()) && !"".equals(estado.getText().toString())) {
            Cidade c = new Cidade(cidade.getText().toString(), estado.getText().toString());
            CidadeDAO cid = new CidadeDAO();
            Mensagens.msgBanco(cid.save(c), this.getActivity().getApplication().getApplicationContext());
        } else {
            Toast.makeText(getActivity(), R.string.faltaCampos, Toast.LENGTH_SHORT).show();
        }
    }

    @Click
    void btnCancelaCidade() {
        cidade.setText("");
        estado.setText("");
    }
}
