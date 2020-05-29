package com.mohfajar.gantara.Data.source.Info;

import android.support.annotation.NonNull;

import com.mohfajar.gantara.Data.BaseDataSourceCallback;
import com.mohfajar.gantara.Data.Info;

import java.util.List;

public interface InfoDataSource {

    interface LoadInfoCallBack extends BaseDataSourceCallback<List<Info>> {

    }

    void getInfos(@NonNull LoadInfoCallBack callback);
}
