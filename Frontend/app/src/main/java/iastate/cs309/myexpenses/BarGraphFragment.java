package iastate.cs309.myexpenses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import iastate.cs309.myexpenses.login.LoginActivity;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.TemporalAdjusters.previous;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BarGraphFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarGraphFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private BarChart chart;

    public BarGraphFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BarGraphFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarGraphFragment newInstance(String param1, String param2) {
        BarGraphFragment fragment = new BarGraphFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bar_graph, container, false);

        initChart(rootView);

        loadData();
        return rootView;
    }

    public BarChart initChart(View rootView) {
        chart = rootView.findViewById(R.id.chart1);
        return chart;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setData(ArrayList<Expense> expenses) {

        if (expenses == null) {
            return;
        }
        ArrayList<Integer> totals = new ArrayList<>();
        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            totals.add(0);
        }
        final LocalDate today = LocalDate.now();
        final LocalDate sunday = today.with(previous(SUNDAY));
        expenses.forEach(expense -> {
            try {
                LocalDate expenseDate = LocalDate.parse(expense.date);
                System.out.println(expenseDate);
                if (!expenseDate.isBefore(sunday)) {
                    int daysBetween = (int) DAYS.between(sunday, expenseDate);
                    totals.set(daysBetween, totals.get(daysBetween) + expense.amount.intValue());
                }
            } catch (Exception exception){

            }
        });
        System.out.println(today);

        int count = 7;
        float range = 10;

        float start = 1f;

        for (int i = 0; i < 7; i++) {
            values.add(new BarEntry(i, totals.get(i)));
        }
//        for (int i = (int) start; i < start + count; i++) {
//            float val = (float) (Math.random() * (range + 1));
//
//            if (Math.random() * 100 < 25) {
//                values.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.ic_baseline_friends_24)));
//            } else {
//                values.add(new BarEntry(i, val));
//            }
//        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "Starting from " + sunday);

            set1.setDrawIcons(false);

            int startColor1 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(getContext(), android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(getContext(), android.R.color.holo_red_light);
            int endColor1 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(getContext(), android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(getContext(), android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(getContext(), android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark);

//            List<Fill> gradientFills = new ArrayList<>();
//            gradientFills.add(new Fill(startColor1, endColor1));
//            gradientFills.add(new Fill(startColor2, endColor2));
//            gradientFills.add(new Fill(startColor3, endColor3));
//            gradientFills.add(new Fill(startColor4, endColor4));
//            gradientFills.add(new Fill(startColor5, endColor5));
//
//            set1.setFsetFills(gradientFills);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            //data.setValueTypeface(tfLight);
            data.setBarWidth(0.9f);

            chart.setData(data);
        }
        chart.invalidate();
    }

    private void loadData() {
        int num = LoginActivity.getUserId();
        String url = "http://coms-309-ug-02.cs.iastate.edu:8080/getItems/" + num;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Expense> listdata = new ArrayList<Expense>();
                        if (response != null) {
                            //while the server doesn't work
                            if (response.length() == 0) {
                                Expense item = new Expense(
                                        "123",
                                        "note1",
                                        "note1",
                                        new BigDecimal(10),
                                        "gas", "2020-10-14");
                                listdata.add(item);
                            }
                            for (int i=0;i<response.length();i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    Expense item = new Expense(
                                            jsonObject.getString("id"),
                                            jsonObject.getString("notes"),
                                            jsonObject.getString("notes"),
                                            new BigDecimal(jsonObject.getDouble("price")),
                                            jsonObject.getString("category"),
                                            jsonObject.getString("date"));
                                    listdata.add(item);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
//                        recyclerView.setAdapter(new ItemListActivity.SimpleItemRecyclerViewAdapter(ExpenseFragment.this, listdata, false));
                        setData(listdata);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println(error);
                        ArrayList<Expense> listdata = new ArrayList<Expense>();
                        Expense item = new Expense(
                                "123",
                                "note1",
                                "note1",
                                new BigDecimal(10),
                                "gas", "2020-10-14");
                        listdata.add(item);

//                        recyclerView.setAdapter(new ItemListActivity.SimpleItemRecyclerViewAdapter(ExpenseFragment.this, listdata, false));
                        setData(listdata);
                        new AlertDialog.Builder(getContext())
                                .setTitle("Error")
                                .setMessage(error.toString())

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                //.setNegativeButton(android.R.string.no, null)
                                //.setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(jsonArrayRequest);
    }
}