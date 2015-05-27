package br.dorga.mantuan.leadcaptureintegrado;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.dorga.mantuan.leadcaptureintegrado.R;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @ViewById
    EditText login;
    @ViewById
    EditText senha;

    @Click
    void btnLogar(){
        if (login.getText().toString().equals("admin") && senha.getText().toString().equals("admin")){
           startActivity(new Intent(this,MainActivity_.class));
        }else {
            Toast.makeText(this,R.string.erro_logar,Toast.LENGTH_SHORT).show();
        }
    }
}
