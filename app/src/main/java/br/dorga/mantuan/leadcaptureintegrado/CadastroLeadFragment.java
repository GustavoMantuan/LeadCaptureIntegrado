package br.dorga.mantuan.leadcaptureintegrado;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.CidadeDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.CursoDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao.LeadDAO;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Cidade;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Curso;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model.Lead;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils.Conversor;
import br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils.Mensagens;


@EFragment(R.layout.fragment_cadastro_lead)
public class CadastroLeadFragment extends Fragment {

    @ViewById
    EditText etNomeLead;
    @ViewById
    EditText etEmailLead;
    @ViewById
    EditText etFoneRes;
    @ViewById
    EditText etFoneCel;
    @ViewById
    EditText dataNascimento;
    @ViewById
    Spinner spCidade;
    @ViewById
    EditText etEndereco;
    @ViewById
    EditText etNumero;
    @ViewById
    EditText etBairro;
    @ViewById
    CheckBox cbFies;
    @ViewById
    EditText etFacebook;
    @ViewById
    CheckBox cbProuni;
    @ViewById
    EditText etProuniP;
    @ViewById
    CheckBox cbEnem;
    @ViewById
    EditText edEnem;
    @ViewById
    Spinner spCurso1;
    @ViewById
    Spinner spCurso2;
    @ViewById
    Button btnCadastraLead;
    @ViewById
    Button btnCancelaLead;

    Cidade cidade;
    Curso curso1;
    Curso curso2;
    static int prouni = 0;
    Double prounid = 0.0;
    Double enemd = 0.0;
    static int enem = 0;
    static int fies = 0;
    private Lead lead;
    private LeadDAO leadDAO = new LeadDAO();


    @Click(R.id.cbProuni)
    void cbProuni() {
        if (cbProuni.isChecked()) {
            etProuniP.setVisibility(View.VISIBLE);
            prouni = 1;
        } else {
            Log.e("erro", String.valueOf(cbProuni.isChecked()));
            etProuniP.setText("");
            etProuniP.setVisibility(View.INVISIBLE);
            prouni = 0;
        }
    }

    @Click
    void cbEnem() {
        if (cbEnem.isChecked()) {
            edEnem.setVisibility(View.VISIBLE);
            enem = 1;
        } else {
            edEnem.setText("");
            edEnem.setVisibility(View.INVISIBLE);
            enem = 0;
        }
    }

    @Click
    void cbFies() {
        fies = cbFies.isChecked() ? 1 : 0;
    }

    @AfterViews
    void after() {
        try {
            lead = (Lead) getArguments().getSerializable("lead");
            btnCadastraLead.setText("Alterar");
            btnCancelaLead.setText("Excluir");
            etNomeLead.setText(lead.getNome());
            etEmailLead.setText(lead.getEmail());
            etFacebook.setText(lead.getFacebook());
            etProuniP.setText(lead.getPercProuni().toString());
            etBairro.setText(lead.getBairro());
            etEndereco.setText(lead.getLougradouro());
            etFoneCel.setText(lead.getTelefonecel());
            etFoneRes.setText(lead.getTelefonetel());
            etNumero.setText(lead.getNumeroEnd().toString());

        } catch (Exception e) {
            e.printStackTrace();
            btnCadastraLead.setText("Cadastrar");
            btnCancelaLead.setText("Limpar");
        }
        final CidadeDAO cidadeDAO = new CidadeDAO();
        final CursoDAO cursoDAO = new CursoDAO();
        final List<Map<String, Object>> cidades = cidadeDAO.list(cidadeDAO.list());
        final List<Map<String, Object>> cursos = cursoDAO.list(cursoDAO.list());
        String[] de_cidade = {"estado", "nome"};
        String[] de_curso = {"aberto", "nome"};
        int[] para = {R.id.p1, R.id.p2};
        SimpleAdapter adapterCurso = new SimpleAdapter(this.getActivity(), cursoDAO.list(cursoDAO.list()), R.layout.listagem, de_curso, para);
        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), cidadeDAO.list(cidadeDAO.list()), R.layout.listagem, de_cidade, para);
        spCidade.setAdapter(adapter);
        spCidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = cidades.get(position);
                Long ide = (Long) item.get("id");
                cidade = cidadeDAO.getCidadeById(ide);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spCurso1.setAdapter(adapterCurso);
        spCurso1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = cursos.get(position);
                Long ide = (Long) item.get("id");
                curso1 = cursoDAO.getCursoById(ide);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spCurso2.setAdapter(adapterCurso);
        spCurso2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = cursos.get(position);
                Long ide = (Long) item.get("id");
                curso2 = cursoDAO.getCursoById(ide);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Click
    void btnCadastraLead() {
        if ("".equals(etNomeLead.getText().toString()) || "".equals(etFacebook.getText().toString()) || "".equals(etEmailLead.getText().toString()) || "".equals(etFoneCel.getText().toString()) || curso1 == null || MainActivity.evento_selecionado == null || cidade == null) {
            Toast.makeText(this.getActivity(), R.string.faltaCampos, Toast.LENGTH_SHORT).show();
        } else {
            Date data = Calendar.getInstance().getTime();
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dt.format(data);
            Date dataNasc = Conversor.stringParaDate(dataNascimento.getText().toString());
            prounid = etProuniP.getText().toString().equals("") ? 0.0 : Double.parseDouble(etProuniP.getText().toString());
            enemd = edEnem.getText().toString().equals("") ? 0.0 : Double.parseDouble(edEnem.getText().toString());
            lead = new Lead(etNomeLead.getText().toString(), etEmailLead.getText().toString(), etFoneRes.getText().toString(), etFoneCel.getText().toString(), dataNasc, data, etEndereco.getText().toString(), etNumero.getText().toString(), etBairro.getText().toString(), etFacebook.getText().toString(), cidade.get_id(), MainActivity_.evento_selecionado.get_id(), fies, prouni, prounid, enem, curso1.get_id(), curso2.get_id(), enemd);
            Mensagens.msgBanco(leadDAO.save(lead), this.getActivity().getApplication().getApplicationContext());
        }
    }

    @Click
    void btnCancelaLead() {


    }


}
