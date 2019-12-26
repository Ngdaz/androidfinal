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
        jsonParese("portgasdax99") ;
        Log.i("abcde", "hehehe");
        return v;

    }

    private void jsonParese(final String username){
        final String searchRepo = "https://api.github.com/users/" + username +"/repos";
        Log.i("abcd","" + searchRepo);
        JsonArrayRequest request = new JsonArrayRequest(searchRepo,new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.i("abcd","abcd" + response.toString());
               try {
                    String finalString = "";
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject repoObject = (JSONObject) response
                                .get(i);
                        String name = repoObject.getString("name");
                        String full_name = repoObject.getString("full_name");
                        String id = repoObject.getString("id");
                        Log.i("abcd","hien len pls" + full_name);
                        finalString += "name: " + name + "\n\n";
                        finalString += "full_name: " + full_name + "\n\n";
                        finalString += "login: " + id + "\n\n\n";

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