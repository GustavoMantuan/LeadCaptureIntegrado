package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dorga on 10/05/2015.
 */
public class Conversor {

    public static String dateParaString(Date data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(data);
        } catch (Exception ex) {
            return "";
        }
    }
    public static Date stringParaDate(String data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(data);
        } catch (Exception ex) {
            return null;
        }
    }
}

