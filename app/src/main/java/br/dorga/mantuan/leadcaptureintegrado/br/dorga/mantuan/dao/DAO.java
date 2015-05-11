package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao;

import android.content.Context;

import java.util.List;

/**
 * Created by Dorga on 05/05/2015.
 */
public interface DAO<T> {
    public long save(T t);
    public long delete (T t);
    public long update(T t);
    public List<T> list ();
}
