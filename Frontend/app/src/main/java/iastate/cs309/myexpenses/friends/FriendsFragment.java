package iastate.cs309.myexpenses.friends;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import iastate.cs309.myexpenses.Expense;
import iastate.cs309.myexpenses.R;
import iastate.cs309.myexpenses.add.AddActivity;
import iastate.cs309.myexpenses.chat.WebsocketActivity;
import iastate.cs309.myexpenses.expense.MyExpenseRecyclerViewAdapter;
import iastate.cs309.myexpenses.friends.dummy.DummyContent;
import iastate.cs309.myexpenses.login.LoginActivity;

/**
 * A fragment representing a list of Items.
 */
public class FriendsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FriendsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FriendsFragment newInstance(int columnCount) {
        FriendsFragment fragment = new FriendsFragment();
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
        View view = inflater.inflate(R.layout.fragment_friends_list, container, false);


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
//            recyclerView.setAdapter(new MyFriendsRecyclerViewAdapter(DummyContent.ITEMS));
        }

        Button chatButton = view.findViewById(R.id.chatBtn);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WebsocketActivity.class);
//                startActivity(intent);
                startActivityForResult(intent,0);
            }
        });

        return view;
    }
    private void loadData(final RecyclerView recyclerView) {
        String url = "http://coms-309-ug-02.cs.iastate.edu:8080/getFriends/" + LoginActivity.getUserId();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Friend> listdata = new ArrayList<>();
                        if (response != null) {
                            //while the server doesn't work
                            if (response.length() == 0) {
                                Friend item = new Friend(
                                        "1",
                                        "kate",
                                        "500");
                                listdata.add(item);
                            }
                            for (int i=0;i<response.length();i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    Friend item = new Friend(
                                            jsonObject.getString("id"),
                                            jsonObject.getString("username"),
                                            jsonObject.getString("budget"));
                                    listdata.add(item);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        Collections.sort(listdata, new Comparator<Friend>() {
                            @Override
                            public int compare(Friend friend, Friend f1) {
                                return f1.content.compareTo(friend.content);
                            }
                        });
//                        recyclerView.setAdapter(new ItemListActivity.SimpleItemRecyclerViewAdapter(ExpenseFragment.this, listdata, false));
                        recyclerView.setAdapter(new MyFriendsRecyclerViewAdapter(listdata, getActivity()));
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println(error);
                        ArrayList<Friend> listdata = new ArrayList<>();
                        Friend item = new Friend(
                                "1",
                                "kate",
                                "500");
                        listdata.add(item);

//                        recyclerView.setAdapter(new ItemListActivity.SimpleItemRecyclerViewAdapter(ExpenseFragment.this, listdata, false));
                        recyclerView.setAdapter(new MyFriendsRecyclerViewAdapter(listdata, getActivity()));
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