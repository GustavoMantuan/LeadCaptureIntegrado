package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import br.dorga.mantuan.leadcaptureintegrado.CadastroCidadeFragment;
import br.dorga.mantuan.leadcaptureintegrado.CadastroCidadeFragment_;
import br.dorga.mantuan.leadcaptureintegrado.CadastroCursoFragment_;
import br.dorga.mantuan.leadcaptureintegrado.CadastroEventoFragment;
import br.dorga.mantuan.leadcaptureintegrado.CadastroEventoFragment_;
import br.dorga.mantuan.leadcaptureintegrado.CadastroLeadFragment;
import br.dorga.mantuan.leadcaptureintegrado.CadastroLeadFragment_;

/**
 * Created by Dorga on 26/05/2015.
 */
public class SwipeAdapter extends FragmentPagerAdapter {

    public SwipeAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new CadastroCidadeFragment_();
            case 1:
                return new CadastroCursoFragment_();
            case 2:
                return new CadastroEventoFragment_();
            case 3:
                return new CadastroLeadFragment_();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
