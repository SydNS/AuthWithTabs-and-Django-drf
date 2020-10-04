package com.example.moja.Posts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moja.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class AddPost extends AppCompatActivity {
//    private static final String ROOT_URL_POST = "http://192.168.43.87:5000/add_api_posts".replaceAll(" ", "%20");

    private static final String ROOT_URL_POST = "http://192.168.43.87:5000/WebIntApi/allposts/".replaceAll(" ", "%20");
    Button send;
    TextInputLayout postauthor;
    TextInputLayout posttitle;
    TextInputLayout postsubtitle;
    TextInputLayout postpost;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        send = (Button) findViewById(R.id.sendpost);
        postauthor = (TextInputLayout) findViewById(R.id.postauthorlayout);
        posttitle = (TextInputLayout) findViewById(R.id.posttitlelayout);
        postsubtitle = (TextInputLayout)findViewById(R.id.postsubtitlelayout);
        postpost = (TextInputLayout) findViewById(R.id.postpostlayout);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author = postauthor.getEditText().getText().toString();
                String title = posttitle.getEditText().getText().toString();
                String subtitle = postsubtitle.getEditText().getText().toString();
                String post = postpost.getEditText().getText().toString();
                posting(author, title, subtitle, post);


            }
        });
    }

    private void posting(final String author, final String title, final String subtitle, final String post) {

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("author", author);
            parameters.put("title", title);
            parameters.put("subtitle", subtitle);
            parameters.put("post", post);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ROOT_URL_POST, parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        requestQueue.add(jsonObjReq);
    }
}