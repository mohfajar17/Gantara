package com.mohfajar.gantara.Form;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.Injection;
import com.mohfajar.gantara.OnFragmentInteractionListener;
import com.mohfajar.gantara.RunTime;
import com.mohfajar.gantara.SharedPrefManager;

public class FormFragment extends Fragment implements FormContract.View{

    private FormContract.Presenter mFormPresenter;

    //creat xml
    private Button btnSimpanForm;
    private RadioGroup radioButtonFSesiLatihan;
    private Spinner spinnerAntusiasmeSebelum;
    private Spinner spinnerAntusiasmeSesudah;
    private RadioGroup radioButtonFFisik;
    private EditText editTextFCatatanFisik;
    private Spinner spinnerStres;
    private Spinner spinnerKonsentrasi;
    private Spinner spinnerKeyakinan;
    private Spinner spinnerTarget;
    private Spinner spinnerKelelahan;
    private Spinner spinnerKomunikasi;
    private Spinner spinnerIntensitas;
    private RadioGroup radioButtonFDehidrasi;
    private RadioGroup radioButtonFTidur;
    private CheckBox checkBoxFNutrisi1;
    private CheckBox checkBoxFNutrisi2;
    private CheckBox checkBoxFNutrisi3;
    private CheckBox checkBoxFNutrisi4;
    private CheckBox checkBoxFNutrisi5;
    private CheckBox checkBoxFRecovery1;
    private CheckBox checkBoxFRecovery2;
    private CheckBox checkBoxFRecovery3;
    private CheckBox checkBoxFRecovery4;
    private CheckBox checkBoxFRecovery5;
    private CheckBox checkBoxFRecovery6;
    private CheckBox checkBoxFRecovery7;
    private CheckBox checkBoxFRecovery8;
    private CheckBox checkBoxFRecovery9;
    private EditText editTextFRecoveryLain;
    private CheckBox checkBoxFMentalSkill1;
    private CheckBox checkBoxFMentalSkill2;
    private CheckBox checkBoxFMentalSkill3;
    private CheckBox checkBoxFMentalSkill4;
    private CheckBox checkBoxFMentalSkill5;
    private EditText editTextFMentalSkillLain;
    private EditText editTextFKendalaMentalSkill;
    private RadioGroup radioButtonFTindakLanjut;
    private EditText editTextFTindakLanjutLain;
    private EditText editTextFHalPositif;

    //local variable
    private float sesiLatihan = 0;
    private float antusiasmeSebelum = 0;
    private float antusiasmeSesudah = 0;
    private float fisik = 0;
    private float stres = 0;
    private float konsentrasi = 0;
    private float keyakinan = 0;
    private float target = 0;
    private float kelelahan = 0;
    private float komunikasi = 0;
    private float intensitas = 0;
    private float dehidrasi = 0;
    private float tidur = 0;
    private float nutrisi = 0;
    private float recovery = 0;
    private float mentalSkill = 0;
    private float scoringMental = 0;
    private float scoringFisik = 0;
    private float tindakLanjut = 0;

    private OnFragmentInteractionListener mListener;

    private ProgressDialog mProgessDialog;

    private SharedPrefManager sharedPrefManager;

    public FormFragment() {

    }

    public static FormFragment newInstance() {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        this.mProgessDialog = new ProgressDialog(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_form, container, false);

        sharedPrefManager = SharedPrefManager.getInstance(getActivity());

        radioButtonFSesiLatihan = (RadioGroup) view.findViewById(R.id.radioButtonFSesiLatihan);
        spinnerAntusiasmeSebelum = (Spinner) view.findViewById(R.id.spinnerAntusiasmeSebelum);
        spinnerAntusiasmeSesudah = (Spinner) view.findViewById(R.id.spinnerAntusiasmeSesudah);
        radioButtonFFisik = (RadioGroup) view.findViewById(R.id.radioButtonFFisik);
        editTextFCatatanFisik = (EditText) view.findViewById(R.id.editTextFCatatanFisik);
        spinnerStres = (Spinner) view.findViewById(R.id.spinnerStres);
        spinnerKonsentrasi = (Spinner) view.findViewById(R.id.spinnerKonsentrasi);
        spinnerKeyakinan = (Spinner) view.findViewById(R.id.spinnerKeyakinan);
        spinnerTarget = (Spinner) view.findViewById(R.id.spinnerTarget);
        spinnerKelelahan = (Spinner) view.findViewById(R.id.spinnerKelelahan);
        spinnerKomunikasi = (Spinner) view.findViewById(R.id.spinnerKomunikasi);
        spinnerIntensitas = (Spinner) view.findViewById(R.id.spinnerIntensitas);
        radioButtonFDehidrasi = (RadioGroup) view.findViewById(R.id.radioButtonFDehidrasi);
        radioButtonFTidur = (RadioGroup) view.findViewById(R.id.radioButtonFTidur);
        checkBoxFNutrisi1 = (CheckBox) view.findViewById(R.id.checkBoxFNutrisi1);
        checkBoxFNutrisi2 = (CheckBox) view.findViewById(R.id.checkBoxFNutrisi2);
        checkBoxFNutrisi3 = (CheckBox) view.findViewById(R.id.checkBoxFNutrisi3);
        checkBoxFNutrisi4 = (CheckBox) view.findViewById(R.id.checkBoxFNutrisi4);
        checkBoxFNutrisi5 = (CheckBox) view.findViewById(R.id.checkBoxFNutrisi5);
        checkBoxFRecovery1 = (CheckBox) view.findViewById(R.id.checkBoxFRecovery1);
        checkBoxFRecovery2 = (CheckBox) view.findViewById(R.id.checkBoxFRecovery2);
        checkBoxFRecovery3 = (CheckBox) view.findViewById(R.id.checkBoxFRecovery3);
        checkBoxFRecovery4 = (CheckBox) view.findViewById(R.id.checkBoxFRecovery4);
        checkBoxFRecovery5 = (CheckBox) view.findViewById(R.id.checkBoxFRecovery5);
        checkBoxFRecovery6 = (CheckBox) view.findViewById(R.id.checkBoxFRecovery6);
        checkBoxFRecovery7 = (CheckBox) view.findViewById(R.id.checkBoxFRecovery7);
        checkBoxFRecovery8 = (CheckBox) view.findViewById(R.id.checkBoxFRecovery8);
        checkBoxFRecovery9 = (CheckBox) view.findViewById(R.id.checkBoxFRecovery9);
        editTextFRecoveryLain = (EditText) view.findViewById(R.id.editTextFRecoveryLain);
        checkBoxFMentalSkill1 = (CheckBox) view.findViewById(R.id.checkBoxFMentalSkill1);
        checkBoxFMentalSkill2 = (CheckBox) view.findViewById(R.id.checkBoxFMentalSkill2);
        checkBoxFMentalSkill3 = (CheckBox) view.findViewById(R.id.checkBoxFMentalSkill3);
        checkBoxFMentalSkill4 = (CheckBox) view.findViewById(R.id.checkBoxFMentalSkill4);
        checkBoxFMentalSkill5 = (CheckBox) view.findViewById(R.id.checkBoxFMentalSkill5);
        editTextFMentalSkillLain = (EditText) view.findViewById(R.id.editTextFMentalSkillLain);
        editTextFKendalaMentalSkill = (EditText) view.findViewById(R.id.editTextFKendalaMentalSkill);
        radioButtonFTindakLanjut = (RadioGroup) view.findViewById(R.id.radioButtonFTindakLanjut);
        editTextFTindakLanjutLain = (EditText) view.findViewById(R.id.editTextFTindakLanjutLain);
        editTextFHalPositif = (EditText) view.findViewById(R.id.editTextFHalPositif);

        //btn simpan action
        btnSimpanForm = (Button) view.findViewById(R.id.buttonFSimpanForm);
        btnSimpanForm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                simpanForm();
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void launchHomeActivity() {
        Toast.makeText(FormFragment.this.getActivity(), "Form berhasil disimpan", Toast.LENGTH_LONG).show();
        RunTime.logTime();
        mListener.onFragmentChanged(R.id.nav_form);
    }

    @Override
    public void setPresenter(FormContract.Presenter presenter) {
        this.mFormPresenter = presenter;
    }

    @Override
    public void showLoading() {
        if(mProgessDialog!=null){
            mProgessDialog.setTitle("Proses");
            mProgessDialog.setMessage("Silahkan tunggu");
            mProgessDialog.setCancelable(false);
            mProgessDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if(mProgessDialog!=null && mProgessDialog.isShowing()){
            mProgessDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Attention")
                .setMessage(message)
                .show();
    }

    @Override
    public void onDataNotAvailable() {
        showMessage(getString(R.string.message_no_data));
    }

    private void simpanForm() {
        if(radioButtonFSesiLatihan.getCheckedRadioButtonId()==-1 || spinnerAntusiasmeSebelum.getSelectedItemPosition()==0 || spinnerAntusiasmeSesudah.getSelectedItemPosition()==0 || radioButtonFFisik.getCheckedRadioButtonId() == -1 || spinnerStres.getSelectedItemPosition()==0 || spinnerKonsentrasi.getSelectedItemPosition()==0 || spinnerKeyakinan.getSelectedItemPosition()==0 || spinnerTarget.getSelectedItemPosition()==0 || spinnerKelelahan.getSelectedItemPosition()==0 || spinnerKomunikasi.getSelectedItemPosition()==0 || spinnerIntensitas.getSelectedItemPosition()==0 || radioButtonFDehidrasi.getCheckedRadioButtonId() == -1 || radioButtonFTidur.getCheckedRadioButtonId() == -1 || radioButtonFTindakLanjut.getCheckedRadioButtonId() == -1){
            Toast.makeText(FormFragment.this.getActivity(), "anda belum melengkapi form", Toast.LENGTH_LONG).show();
        } else {
            new AlertDialog.Builder(getContext())
                    .setMessage("Anda yakin ingin menyimpan form?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            RunTime.logTime();
                            scoring();
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        }
    }

    private void scoring() {
        switch (radioButtonFSesiLatihan.getCheckedRadioButtonId()){
            case R.id.radioButtonFSesiLatihan1:
                sesiLatihan = 1;
                break;
            case R.id.radioButtonFSesiLatihan2:
                sesiLatihan = 2;
                break;
            case R.id.radioButtonFSesiLatihan3:
                sesiLatihan = 3;
                break;
            case R.id.radioButtonFSesiLatihan4:
                sesiLatihan = 4;
                break;
            default:
                sesiLatihan = 0;
                break;
        }

        antusiasmeSebelum = spinnerAntusiasmeSebelum.getSelectedItemPosition();
        scoringMental += antusiasmeSebelum;
        antusiasmeSebelum = (antusiasmeSebelum/10)*100;

        antusiasmeSesudah = spinnerAntusiasmeSesudah.getSelectedItemPosition();
        scoringMental += antusiasmeSesudah;
        antusiasmeSesudah = (antusiasmeSesudah/10)*100;

        switch (radioButtonFFisik.getCheckedRadioButtonId()){
            case R.id.radioButtonFFisik1:
                fisik = 2;
                break;
            case R.id.radioButtonFFisik2:
                fisik = 4;
                break;
            case R.id.radioButtonFFisik3:
                fisik = 6;
                break;
            case R.id.radioButtonFFisik4:
                fisik = 8;
                break;
            case R.id.radioButtonFFisik5:
                fisik = 10;
                break;
            default:
                fisik = 0;
                break;
        }
        scoringFisik += fisik;
        fisik = (fisik/10)*100;

        stres = spinnerStres.getSelectedItemPosition();
        scoringMental += stres;
        stres = (stres/10)*100;

        konsentrasi = spinnerKonsentrasi.getSelectedItemPosition();
        scoringMental += konsentrasi;
        konsentrasi = (konsentrasi/10)*100;

        keyakinan = spinnerKeyakinan.getSelectedItemPosition();
        scoringMental += keyakinan;
        keyakinan = (keyakinan/10)*100;

        target = spinnerTarget.getSelectedItemPosition();
        scoringMental += target;
        target = (target/10)*100;

        kelelahan = spinnerKelelahan.getSelectedItemPosition();
        scoringFisik +=kelelahan;
        kelelahan = (kelelahan/10)*100;

        komunikasi = spinnerKomunikasi.getSelectedItemPosition();
        scoringMental += komunikasi;
        komunikasi = (komunikasi/10)*100;

        intensitas = spinnerIntensitas.getSelectedItemPosition();
        intensitas = (intensitas/10)*100;

        switch (radioButtonFDehidrasi.getCheckedRadioButtonId()){
            case R.id.radioButtonFDehidrasi1:
                dehidrasi = 2;
                break;
            case R.id.radioButtonFDehidrasi2:
                dehidrasi = 4;
                break;
            case R.id.radioButtonFDehidrasi3:
                dehidrasi = 6;
                break;
            case R.id.radioButtonFDehidrasi4:
                dehidrasi = 8;
                break;
            case R.id.radioButtonFDehidrasi5:
                dehidrasi = 10;
                break;
            default:
                dehidrasi = 0;
                break;
        }
        scoringFisik += dehidrasi;
        dehidrasi = (dehidrasi/10)*100;

        switch (radioButtonFTidur.getCheckedRadioButtonId()){
            case R.id.radioButtonFTidur1:
                tidur = 2;
                break;
            case R.id.radioButtonFTidur2:
                tidur = 4;
                break;
            case R.id.radioButtonFTidur3:
                tidur = 6;
                break;
            case R.id.radioButtonFTidur4:
                tidur = 8;
                break;
            case R.id.radioButtonFTidur5:
                tidur = 10;
                break;
            default:
                tidur = 0;
                break;
        }
        scoringFisik += tidur;
        tidur = (tidur/10)*100;

        if(checkBoxFNutrisi1.isChecked())
            nutrisi+=2;
        if(checkBoxFNutrisi2.isChecked())
            nutrisi+=2;
        if(checkBoxFNutrisi3.isChecked())
            nutrisi+=2;
        if(checkBoxFNutrisi4.isChecked())
            nutrisi+=2;
        if(checkBoxFNutrisi5.isChecked())
            nutrisi+=2;
        scoringFisik += nutrisi;
        nutrisi = (nutrisi/10)*100;

        if(checkBoxFRecovery1.isChecked())
            recovery+=2;
        if(checkBoxFRecovery2.isChecked())
            recovery+=2;
        if(checkBoxFRecovery3.isChecked())
            recovery+=2;
        if(checkBoxFRecovery4.isChecked())
            recovery+=2;
        if(checkBoxFRecovery5.isChecked())
            recovery+=2;
        if(checkBoxFRecovery6.isChecked())
            recovery+=2;
        if(checkBoxFRecovery7.isChecked())
            recovery+=2;
        if(checkBoxFRecovery8.isChecked())
            recovery+=2;
        if(checkBoxFRecovery9.isChecked())
            recovery+=2;
        scoringFisik += recovery;
        recovery = (recovery/18)*100;

        if(checkBoxFMentalSkill1.isChecked())
            mentalSkill+=2;
        if(checkBoxFMentalSkill2.isChecked())
            mentalSkill+=2;
        if(checkBoxFMentalSkill3.isChecked())
            mentalSkill+=2;
        if(checkBoxFMentalSkill4.isChecked())
            mentalSkill+=2;
        if(checkBoxFMentalSkill5.isChecked())
            mentalSkill+=2;
        scoringMental += mentalSkill;
        mentalSkill = (mentalSkill/10)*100;

        scoringMental = (scoringMental/80)*100;
        scoringFisik = (scoringFisik/68)*100;

        switch (radioButtonFTindakLanjut.getCheckedRadioButtonId()){
            case R.id.radioButtonFTindakLanjut1:
                tindakLanjut = 1;
                break;
            case R.id.radioButtonFTindakLanjut2:
                tindakLanjut = 2;
                break;
            case R.id.radioButtonFTindakLanjut3:
                tindakLanjut = 3;
                break;
            default:
                tindakLanjut = 0;
                break;
        }

        insertForm();
    }

    private void insertForm() {
        mFormPresenter.form(
                sharedPrefManager.getAtletID(), sharedPrefManager.getPsikologID(),String.valueOf(sesiLatihan),String.valueOf(antusiasmeSebelum),String.valueOf(antusiasmeSesudah),String.valueOf(fisik),editTextFCatatanFisik.getText().toString()+" ",String.valueOf(stres),String.valueOf(konsentrasi),String.valueOf(keyakinan), String.valueOf(target), String.valueOf(kelelahan), String.valueOf(komunikasi), String.valueOf(intensitas), String.valueOf(dehidrasi), String.valueOf(tidur), String.valueOf(nutrisi), String.valueOf(recovery), editTextFRecoveryLain.getText().toString()+" ", String.valueOf(mentalSkill), editTextFMentalSkillLain.getText().toString()+" ", editTextFKendalaMentalSkill.getText().toString()+" ", String.valueOf(tindakLanjut), editTextFTindakLanjutLain.getText().toString()+" ", editTextFHalPositif.getText().toString()+" ", String.valueOf(scoringMental), String.valueOf(scoringFisik), String.valueOf(sharedPrefManager.getIntensitasTarget())
        );
    }
}
