package iastate.cs309.myexpenses.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

import iastate.cs309.myexpenses.R;
import iastate.cs309.myexpenses.navbar.BottomNavBarActivity;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mTextUsername.getText().toString().equals("") && !mTextPassword.getText().toString().equals("")){
//                    Temporary ArrayList of Users to compare to when logging in
//                    ArrayList<User> tempList = new ArrayList<>();
//                    list.add(new User("1", "polina", "123"));
//                    list.add(new User("2", "kate", "4567"));
                    loadData();
                    if(validateUser(list)){
                        Intent loginToApp = new Intent(LoginActivity.this, BottomNavBarActivity.class);
                        startActivity(loginToApp);
                    }
                }

            }
        });

    }
    private boolean validateUser(ArrayList<User> list){
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).username.equals(mTextUsername.getText().toString())
            && list.get(i).password.equals(mTextPassword.getText().toString())) {
                return true;
            }
        }
        return false;
    }


    private void loadData() {
        String url = "http://coms-309-ug-02.cs.iastate.edu:8080/persons";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<User> listdata = new ArrayList<>();
                        if (response != null) {
                            //while the server doesn't work
                            if (response.length() == 0) {
                                User item = new User(
                                        "1",
                                        "polina",
                                        "123");
                                listdata.add(item);
                            }
                            for (int i=0;i<response.length();i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    User item = new User(
                                            jsonObject.getString("id"),
                                            jsonObject.getString("username"),
                                            jsonObject.getString("password"));
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
                        ArrayList<User> listdata = new ArrayList<>();
                        User item = new User(
                                "1",
                                "polina",
                                "123");
                        listdata.add(item);

//                        recyclerView.setAdapter(new ItemListActivity.SimpleItemRecyclerViewAdapter(ExpenseFragment.this, listdata, false));
                        setData(listdata);
                        new AlertDialog.Builder(getApplicationContext())
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

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    private void setData(ArrayList<User> listdata) {
        list = listdata;
    }

}