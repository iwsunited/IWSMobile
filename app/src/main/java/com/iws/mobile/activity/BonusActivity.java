package com.iws.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.iws.mobile.CommonMethod;
import com.iws.mobile.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BonusActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "ganteng";
    TextView tvPanahBack, tvNav, tvBonusHariIni, tvBonusBulanIni, tvNama, tvUsername, tvBonusTerbentuk, tvBonusTransfer, tvBonusBelumTransfer;
    TableLayout tableLayout;

    EditText edtStartDate, edtEndDate;
    Spinner spnEntries;

    TextView tvPage1, tvPage2, tvPage3, tvPage4, tvPage5, tvPrev, tvNext;

    static final String[] paths = {"10", "25", "50"};

    String showEntries;
    int totalRow = 0;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

        initView();

        Calendar calendar = Calendar.getInstance();
        setTanggal(calendar.getTimeInMillis(), calendar.getTimeInMillis());

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(today);

        edtStartDate.setText(date);
        edtEndDate.setText(date);

        setEntries();

        loadData(date, date, showEntries);
    }

    private void initView() {
        tvNav = findViewById(R.id.tv_navbar_other);
        tvNav.setText("Bonus");
        tvPanahBack = findViewById(R.id.tv_bonus_prev);
        tvPanahBack.setText("<");

        tvBonusHariIni = findViewById(R.id.tv_bonus_hariini);
        tvBonusBulanIni = findViewById(R.id.tv_bonus_bulanini);
        tvNama = findViewById(R.id.tv_bonus_nama);
        tvUsername = findViewById(R.id.tv_bonus_username);
        tableLayout = findViewById(R.id.tl_bonus);
        tvBonusTerbentuk = findViewById(R.id.tv_bonus_bonusterbentuk);
        tvBonusTransfer = findViewById(R.id.tv_bonus_bonusditransfer);
        tvBonusBelumTransfer = findViewById(R.id.tv_bonus_bonusbelumditansfer);
        edtStartDate = findViewById(R.id.edt_bonus_startdate);
        edtEndDate = findViewById(R.id.edt_bonus_enddate);
        spnEntries = findViewById(R.id.spn_entries);

        showEntries = "25";

        tvPage1 = findViewById(R.id.tv_bonus_page1);
        tvPage2 = findViewById(R.id.tv_bonus_page2);
        tvPage3 = findViewById(R.id.tv_bonus_page3);
        tvPage4 = findViewById(R.id.tv_bonus_page4);
        tvPage5 = findViewById(R.id.tv_bonus_page5);
        tvPrev = findViewById(R.id.tv_bonus_prev);
        tvNext = findViewById(R.id.tv_bonus_next);
    }

    private void setPagination(int totalRow, int currentPage){

        Log.d(TAG, "setPagination: totalrow : " + totalRow);
        Log.d(TAG, "setPagination: currentpage : " + currentPage);

        ArrayList<TextView> listPage = new ArrayList<>();
        listPage.add(tvPage1);
        listPage.add(tvPage2);
        listPage.add(tvPage3);
        listPage.add(tvPage4);
        listPage.add(tvPage5);

        int totalPage = (int)(totalRow/Integer.parseInt(showEntries)) + 1;
        int sisaPage = totalPage - currentPage;

        Log.d(TAG, "setPagination: totalpage : " + totalPage);
        Log.d(TAG, "setPagination: sisapage : " + sisaPage);

        if (totalPage == 1){
            tvNext.setVisibility(View.GONE);
            tvPrev.setVisibility(View.GONE);
            for (int i = 0; i < listPage.size(); i++){
                listPage.get(i).setVisibility(View.GONE);
            }
        } else {
            for (int i = 0; i < listPage.size(); i++){
                listPage.get(i).setVisibility(View.GONE);
            }

            if (sisaPage > 0){
                tvNext.setVisibility(View.VISIBLE);
            } else {
                tvNext.setVisibility(View.GONE);
            }

            if (currentPage != 1){
                tvPrev.setVisibility(View.VISIBLE);
            } else {
                tvPrev.setVisibility(View.GONE);
            }

            for (int j = 0; j < sisaPage; j++){
                listPage.get(j).setVisibility(View.VISIBLE);
                listPage.get(j).setText("" + (currentPage + j + 1));
            }
        }

        tvPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = Integer.parseInt(tvPage1.getText().toString());
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
            }
        });
        tvPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = Integer.parseInt(tvPage1.getText().toString());
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
            }
        });
        tvPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = Integer.parseInt(tvPage1.getText().toString());
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
            }
        });
        tvPage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = Integer.parseInt(tvPage1.getText().toString());
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
            }
        });
        tvPage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = Integer.parseInt(tvPage1.getText().toString());
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
            }
        });
        tvPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page--;
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
            }
        });
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
            }
        });
    }

    private void setEntries(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BonusActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEntries.setAdapter(adapter);
        spnEntries.setOnItemSelectedListener(this);

        spnEntries.setSelection(1);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Log.d(TAG, "onItemSelected: entri : 10");
                showEntries = "10";
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
                break;
            case 1:
                showEntries = "25";
                Log.d(TAG, "onItemSelected: entri : 25");
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
                break;
            case 2:
                Log.d(TAG, "onItemSelected: entri : 50");
                showEntries = "50";
                loadData(edtStartDate.getText().toString(), edtEndDate.getText().toString(), showEntries);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setTanggal(Long mulai, Long akhir) {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setSelection(new Pair<>(mulai, akhir));
        builder.setTitleText("Pilih tanggal mulai dan akhir");

        Calendar calendar = Calendar.getInstance();
        Long hariini = calendar.getTimeInMillis();

        CalendarConstraints.Builder builder1 = new CalendarConstraints.Builder();
        CalendarConstraints.DateValidator max = DateValidatorPointBackward.before(hariini);
        builder1.setValidator(max);

        builder.setCalendarConstraints(builder1.build());

        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();

        edtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                String start = new SimpleDateFormat("dd-MM-yyyy").format(new Date(selection.first));
                String end = new SimpleDateFormat("dd-MM-yyyy").format(new Date(selection.second));

                edtStartDate.setText(start);
                edtEndDate.setText(end);

                loadData(start, end, showEntries);
            }
        });
    }

    private void loadData(String startDate, String endDate, String showEntries) {
        tableLayout.removeAllViews();
        SharedPreferences pref = getSharedPreferences(CommonMethod.PREF_KEY, MODE_PRIVATE);

        String userid = pref.getString("secure_id", "");
        String nama = pref.getString("member_nama", "");
        String username = pref.getString("secure_username", "");

        tvUsername.setText(username);
        tvNama.setText(nama);

        Call<ResponseBody> call = CommonMethod.getJsonApiGeniIws().bonus("" + page, "1", startDate, endDate, showEntries);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: code : " + response.code());
                    return;
                }

                try {
                    JSONObject hasil = new JSONObject(response.body().string());
                    JSONObject result = hasil.getJSONObject("data2");
                    String bonusHariIni = (result.getString("bonus_hari_ini") == null) ? "0" : result.getString("bonus_hari_ini");
                    String bonusBulanIni = (result.getString("bonus_bulan_ini") == null) ? "0" : result.getString("bonus_bulan_ini");
                    String bonusTerbentuk = (result.getString("bonus_terbentuk") == null) ? "0" : result.getString("bonus_terbentuk");
                    String bonusTransfer = (result.getString("bonus_ditransfer") == null) ? "0" : result.getString("bonus_ditransfer");
                    String bonusBelumTransfer = (result.getString("bonus_belum_ditransfer") == null) ? "0" : result.getString("bonus_belum_ditransfer");

                    tvBonusHariIni.setText("Rp. " + bonusHariIni);
                    tvBonusBulanIni.setText("Rp. " + bonusBulanIni);
                    tvBonusTerbentuk.setText("Rp. " + bonusTerbentuk);
                    tvBonusTransfer.setText("Rp. " + bonusTransfer);
                    tvBonusBelumTransfer.setText("Rp. " + bonusBelumTransfer);

                    JSONArray bonuslist = hasil.getJSONArray("data");
                    if (bonuslist.length() == 0) {
                        return;
                    }

                    for (int i = 0; i < bonuslist.length(); i++) {
                        String tanggal = bonuslist.getJSONObject(i).getString("bonus_date");
                        String jenis = bonuslist.getJSONObject(i).getString("bonus_name");
                        String jumlah = bonuslist.getJSONObject(i).getString("bonus_amount_formatted");

                        if (i % 2 == 0) {
                            addTableRow(tanggal, jenis, jumlah, false);
                        } else {
                            addTableRow(tanggal, jenis, jumlah, true);
                        }
                    }

                    setPagination(Integer.parseInt(result.getString("total_row")), page);

                } catch (Exception e) {
                    Log.d(TAG, "onResponse: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void addTableRow(String tanggal, String jenis, String jumlah, boolean abuabu) {
        TableRow row = new TableRow(this);
        if (abuabu) {
            row.setBackgroundColor(getResources().getColor(R.color.abuabumuda));
        }

        row.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f);

        TextView tv1 = new TextView(this);
        tv1.setText(tanggal);
        tv1.setGravity(Gravity.CENTER);
        tv1.setPadding(0, 4, 0, 4);
        TextView tv2 = new TextView(this);
        tv2.setText(jenis);
        tv2.setGravity(Gravity.CENTER);
        tv2.setPadding(0, 4, 0, 4);
        TextView tv3 = new TextView(this);
        tv3.setText(jumlah);
        tv3.setGravity(Gravity.CENTER);
        tv3.setPadding(0, 4, 0, 4);

        tv1.setLayoutParams(params);
        tv2.setLayoutParams(params);
        tv3.setLayoutParams(params);

        row.addView(tv1);
        row.addView(tv2);
        row.addView(tv3);

        tableLayout.addView(row);
    }
}