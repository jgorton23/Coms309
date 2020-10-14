package iastate.cs309.myexpenses.expense;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import iastate.cs309.myexpenses.BarGraphFragment;
import iastate.cs309.myexpenses.Expense;
import iastate.cs309.myexpenses.ItemListActivity;
import iastate.cs309.myexpenses.R;
import iastate.cs309.myexpenses.expense.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 */
public class ExpenseFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ExpenseFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ExpenseFragment newInstance(int columnCount) {
        ExpenseFragment fragment = new ExpenseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expense_list, container, false);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.parent_fragment_container, new BarGraphFragment());
        ft.commit();

        View view = rootView.findViewById(R.id.list);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            loadData(recyclerView);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
//            recyclerView.setAdapter(new MyExpenseRecyclerViewAdapter(DummyContent.ITEMS));
        }
        return rootView;
    }

    private void loadData(final RecyclerView recyclerView) {
        String url = "http://coms-309-ug-02.cs.iastate.edu:8080/getItem";

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
                        Collections.sort(listdata, new Comparator<Expense>() {
                            @Override
                            public int compare(Expense expense, Expense t1) {
                                return t1.date.compareTo(expense.date);
                            }
                        });
//                        recyclerView.setAdapter(new ItemListActivity.SimpleItemRecyclerViewAdapter(ExpenseFragment.this, listdata, false));
                        recyclerView.setAdapter(new MyExpenseRecyclerViewAdapter(listdata, getActivity()));
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
                        recyclerView.setAdapter(new MyExpenseRecyclerViewAdapter(listdata, getActivity()));
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