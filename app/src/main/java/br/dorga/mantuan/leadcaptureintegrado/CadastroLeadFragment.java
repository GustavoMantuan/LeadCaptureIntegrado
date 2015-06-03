package br.dorga.mantuan.leadcaptureintegrado;


import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import br.com.jansenfelipe.androidmask.Utils;
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


    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";



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
    final CidadeDAO cidadeDAO = new CidadeDAO();
    final CursoDAO cursoDAO = new CursoDAO();
    final List<Map<String, Object>> cidades = cidadeDAO.list(cidadeDAO.list());
    final List<Map<String, Object>> cursos = cursoDAO.list(cursoDAO.list());
    SimpleAdapter adapter;
    SimpleAdapter adapterCurso;

    public static boolean validaEmail(String email) {
        if (email == null || email.isEmpty() || email.length() > 320) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }




    @Click(R.id.cbProuni)
    void cbProuni() {
        if (cbProuni.isChecked()) {
            etProuniP.setVisibility(View.VISIBLE);
            prouni = 1;
        } else {
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

    @FocusChange(R.id.etEmailLead)
    void verifical(View v, boolean focus){
        if (validaEmail(etEmailLead.getText().toString())){
            try {
                if (lead == null) {
                    lead = leadDAO.getLeadByEmail(etEmailLead.getText().toString());
                    preencheCampos(lead);
                    btnCadastraLead.setText("Alterar");
                    btnCancelaLead.setText("Excluir");
                }
            }catch (Exception e){

            }
        }else {

            if (!"".equals(etEmailLead.getText().toString())){
                Toast.makeText(this.getActivity(), R.string.erro_email, Toast.LENGTH_SHORT).show();
           //     getActivity().getCurrentFocus().clearFocus();
                etEmailLead.requestFocus();
            }
        }
    }

    @FocusChange(R.id.etFoneCel)
    void verificaf(View v, boolean focus){
        if (!"".equals(etFoneCel)){
            try {
                if (lead == null){
                    lead = leadDAO.getLeadByFone(etFoneCel.getText().toString());
                    preencheCampos(lead);
                    btnCancelaLead.setText("Excluir");
                    btnCadastraLead.setText("Alterar");
                }
            }catch(Exception e){}

            }
        }


    @AfterViews
    void after() {
        String[] de_cidade = {"estado", "nome"};
        String[] de_curso = {"aberto", "nome"};
        int[] para = {R.id.p1, R.id.p2};
        adapterCurso = new SimpleAdapter(this.getActivity(), cursoDAO.list(cursoDAO.list()), R.layout.listagem, de_curso, para);
        adapter = new SimpleAdapter(this.getActivity(), cidadeDAO.list(cidadeDAO.list()), R.layout.listagem, de_cidade, para);
        spCidade.setAdapter(adapter);
        MaskEditTextChangedListener maskTel = new MaskEditTextChangedListener("(##)####-####", etFoneRes);
        MaskEditTextChangedListener maskCel = new MaskEditTextChangedListener("(##)####-####", etFoneCel);
        etFoneRes.addTextChangedListener(maskTel);
        etFoneCel.addTextChangedListener(maskCel);
        try {
            MainActivity_ activity = (MainActivity_) getActivity();
            lead = activity.retornaLead();
            btnCadastraLead.setText("Alterar");
            btnCancelaLead.setText("Excluir");
            preencheCampos(lead);

        } catch (Exception e) {
            e.printStackTrace();
            btnCadastraLead.setText("Cadastrar");
            btnCancelaLead.setText("Limpar");
        }



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

    public void preencheCampos(Lead lead){
        etNomeLead.setText(lead.getNome());
        etEmailLead.setText(lead.getEmail());
        etFacebook.setText(lead.getFacebook());
        etFoneRes.setText(lead.getTelefoneRes());
        etFoneCel.setText(lead.getTelefoneCes());
        dataNascimento.setText(Conversor.dateParaString(lead.getNascimento()));
        etEndereco.setText(lead.getEndereco());
        etNumero.setText(lead.getNumero());
        etBairro.setText(lead.getBairro());
        if (lead.getProuni() == 1){
            cbProuni.setChecked(true);
            etProuniP.setVisibility(View.VISIBLE);
            etProuniP.setText(lead.getPercProuni().toString());
        }else {
            cbProuni.setChecked(false);
        }
        if (lead.getFies() == 1){
            cbFies.setChecked(true);
        }else {
            cbFies.setChecked(false);
        }
        if (lead.getEnem() == 1){
            cbEnem.setChecked(true);
            edEnem.setVisibility(View.VISIBLE);
            edEnem.setText(lead.getNotaEnem().toString());
        }else {
            cbEnem.setChecked(false);
        }
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
            if (lead == null) {
                lead = new Lead(etNomeLead.getText().toString(), etEmailLead.getText().toString(), etFoneRes.getText().toString(), etFoneCel.getText().toString(), dataNasc, etEndereco.getText().toString(), etNumero.getText().toString(), etBairro.getText().toString(), fies, prouni, prounid, etFacebook.getText().toString(), enem, enemd, data, cidade, curso1, curso2, MainActivity_.evento_selecionado);
                Mensagens.msgBanco(leadDAO.save(lead), this.getActivity().getApplication().getApplicationContext());
                lead = null;
                btnCancelaLead();
            }
            else {
                lead.setNome(etNomeLead.getText().toString());
                lead.setBairro(etBairro.getText().toString());
                lead.setCidade(cidade);
                lead.setCurso1(curso1);
                lead.setCurso2(curso2);
                lead.setDataLead(data);
                lead.setEmail(etEmailLead.getText().toString());
                lead.setEndereco(etEndereco.getText().toString());
                lead.setEnem(enem);
                lead.setNotaEnem(enemd);
                lead.setProuni(prouni);
                lead.setPercProuni(prounid);
                lead.setFies(fies);
                lead.setEvento(MainActivity_.evento_selecionado);
                lead.setNumero(etNumero.getText().toString());
                lead.setTelefoneCes(etFoneCel.getText().toString());
                lead.setTelefoneRes(etFoneRes.getText().toString());
                lead.setFacebook(etFacebook.getText().toString());
                lead.setNascimento(dataNasc);
                Mensagens.msgBanco(leadDAO.update(lead), this.getActivity().getApplication().getApplicationContext());
                lead = null;
                btnCancelaLead();
            }
        }
    }

    @Click
    void btnCancelaLead() {
        if (lead == null) {
            etNomeLead.setText("");
            etEmailLead.setText("");
            etFoneRes.setText("");
            etFoneCel.setText("");
            dataNascimento.setText("");
            etEndereco.setText("");
            etNumero.setText("");
            etBairro.setText("");
            cbProuni.setChecked(false);
            etFacebook.setText("");
            etProuniP.setVisibility(View.INVISIBLE);
            etProuniP.setText("");
            cbFies.setChecked(false);
            cbEnem.setChecked(false);
            edEnem.setVisibility(View.INVISIBLE);
            edEnem.setText("");
            btnCadastraLead.setText("Cadastrar");
            btnCancelaLead.setText("Limpar");
        } else {
            Mensagens.msgExclusao(leadDAO.delete(lead),this.getActivity().getApplication().getApplicationContext());
            lead = null;
            btnCancelaLead();
        }


    }

//    @Override
//    public void onResume() {
//        etFoneRes.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
//        super.onResume();
//    }
}
