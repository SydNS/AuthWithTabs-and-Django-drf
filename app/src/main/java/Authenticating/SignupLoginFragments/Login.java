package Authenticating.SignupLoginFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moja.Posts.MainActivity;
import com.example.moja.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Fragment {
    private static final String ROOT_URL_POST = "http://192.168.43.87:5000/WebIntApi/login/";
    RequestQueue requestQueue;
    TextInputLayout loginuname, loginpasswd;
    String luname, lpasswd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, container, false);

        Button login = (Button) view.findViewById(R.id.loginButton);
        loginuname = (TextInputLayout) view.findViewById(R.id.loginuname);
        loginpasswd = (TextInputLayout) view.findViewById(R.id.loginpasswd);
        final CheckBox rememberme = (CheckBox) view.findViewById(R.id.rememberme);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = loginuname.getEditText().getText().toString();
                String upasswd = loginpasswd.getEditText().getText().toString();
                posting(uname,upasswd);
                if (rememberme.isChecked()) {
                    Toast.makeText(getActivity(), "You'll be remembered", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;
    }


    private void posting(String uname, String upasword) {
        requestQueue = Volley.newRequestQueue(getActivity());

        JSONObject parameters = new JSONObject();

        try {
            parameters.put("username", uname);
            parameters.put("password", upasword);
//            parameters.put("email", uemail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ROOT_URL_POST, parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String loggedin = null;
                        try {
                            loggedin = (String) response.getString("Message");
                            if(loggedin.equals("logged in")){
                            Toast.makeText(getActivity(), loggedin, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(),MainActivity.class));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjReq);
    }

}
