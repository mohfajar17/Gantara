package com.gantara.mohfajar.Form;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.source.Form.FormDataSource;
import com.gantara.mohfajar.Data.source.Form.FormRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class FormRepository implements FormDataSource {

    private static FormRepository INSTANCE;

    private FormRemoteDataSource formRemoteDataSource;

    public FormRepository(FormRemoteDataSource formRemoteDataSource) {
        this.formRemoteDataSource = checkNotNull(formRemoteDataSource);
    }

    public static FormRepository getInstance(@NonNull FormRemoteDataSource formRemoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new FormRepository(formRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getForm(@NonNull FormCallBack callback, String idPsikolog) {
        formRemoteDataSource.getForm(callback, idPsikolog);
    }
}
