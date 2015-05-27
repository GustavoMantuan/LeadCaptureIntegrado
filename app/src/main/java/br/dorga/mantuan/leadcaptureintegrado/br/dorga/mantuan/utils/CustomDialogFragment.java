package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import br.dorga.mantuan.leadcaptureintegrado.R;

/**
 * Created by Dorga on 17/05/2015.
 */
public class CustomDialogFragment extends android.support.v4.app.DialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog;
        return super.onCreateDialog(savedInstanceState);
    }
}
