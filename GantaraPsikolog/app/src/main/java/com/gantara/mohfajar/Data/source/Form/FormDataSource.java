package com.gantara.mohfajar.Data.source.Form;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.BaseDataSourceCallback;
import com.gantara.mohfajar.Data.Form;

import java.util.List;

public interface FormDataSource {
    interface FormCallBack extends BaseDataSourceCallback<List<Form>> {

    }

    void getForm(@NonNull FormCallBack callback, String idPsikolog);
}
