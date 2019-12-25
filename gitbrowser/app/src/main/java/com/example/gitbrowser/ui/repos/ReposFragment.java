package com.example.gitbrowser.ui.repos;

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
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gitbrowser.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;


public class ReposFragment extends Fragment {

    ListView search_bar;
    ArrayAdapter<String> Adapter;
    private RequestQueue requestQueue;
    private TextView tv;
    private String finalString;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_repos, container, false);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        tv = (TextView) v.findViewById(R.id.txtResponse);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        jsonParese("portgasdax99");
        return v;
    }

    private void jsonParese(String username){
        String searchRepo = "https://api.github.com/users/" + username +"/repos";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, searchRepo, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    String finalString = "";
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject repoObject = (JSONObject) response.getJSONObject(i);
                        String name = repoObject.getString("name");
                        String description = repoObject.getString("description");
                        String login = repoObject.getString("login");
                        finalString += "Name: " + name + "\n\n";
                        finalString += "description: " + description + "\n\n";
                        finalString += "login: " + login + "\n\n\n";
                        tv.append(name);
                    }
                    tv.setText(finalString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", "Error here!");
            }
        }
        );
//        requestQueue.add(request);
        AppController.getInstance().addToRequestQueue(request);
    }


}
