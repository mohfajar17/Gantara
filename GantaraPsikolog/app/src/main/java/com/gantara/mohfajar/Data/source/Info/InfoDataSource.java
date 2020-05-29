package com.gantara.mohfajar.Data.source.Info;

import android.support.annotation.NonNull;

import com.gantara.mohfajar.Data.BaseDataSourceCallback;
import com.gantara.mohfajar.Data.Info;

import java.util.List;

public interface InfoDataSource {

    interface LoadInfoCallBack extends BaseDataSourceCallback<List<Info>> {

    }

    void getInfos(@NonNull LoadInfoCallBack callback);
}
