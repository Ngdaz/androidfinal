package com.example.gitbrowser.ui.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gitbrowser.R;
import com.example.gitbrowser.ui.repos.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;


public class SearchFragment extends Fragment {

    ListView search_bar;
    ArrayAdapter<String> Adapter;
    private RequestQueue requestQueue;
    private TextView tv;
    private String finalString;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        tv = (TextView) v.findViewById(R.id.txtResponse);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final Button button = (Button) v.findViewById(R.id.btnArrayRequest);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse(finalString);
                button.setVisibility(View.GONE);
            }
        });

        Log.i("abcde", "hehehe");
        jsonParse("androidev") ;
        return v;

    }

    private void jsonParse(final String reponame){
        final String repo =" https://api.github.com/search/repositories?q=" + reponame + "&sort=stars&order=desc";
        Log.i("abcd","" + repo);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,repo, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("abcd","abcd" + response.toString());
                try {
                    String finalString = "";
                    JSONArray obj = response.getJSONArray("items");

                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject repoObject = obj.getJSONObject(i);
                        String name = repoObject.getString("name");
                        String full_name = repoObject.getString("full_name");
                        int id = repoObject.getInt("id");
                        Log.i("abcd","hien len pls" + name);
                        finalString += "name: " + name + "\n\n";
                        finalString += "full_name: " + full_name + "\n\n";
                        finalString += "id: " + id + "\n\n\n";

                    }
                    tv.setText(finalString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG,"error: " + error.getMessage());
                Log.i("Error", "Error here!");
            }
        }
        );
//        requestQueue.add(request);
        AppController.getInstance().addToRequestQueue(request);
    }


}