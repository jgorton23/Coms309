package iastate.cs309.myexpenses.add;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import iastate.cs309.myexpenses.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void saveData() throws JSONException {

//        final TextView textView = rootView.findViewById(R.id.item_detail);
        String url = "http://coms-309-ug-02.cs.iastate.edu:8080/addItem";
        JSONObject jsonBody = new JSONObject();
        EditText price = findViewById(R.id.amountPlainText);
        jsonBody.put("price", new BigDecimal(price.getText().toString()));
        jsonBody.put("catagory", "Grocery");
        EditText date = findViewById(R.id.datePlainText);
        jsonBody.put("date", date.getText());
        EditText name = findViewById(R.id.namePlainText);
        jsonBody.put("notes", name.getText());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        finish();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        // TODO: Handle error

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonObjectRequest);
    }
}