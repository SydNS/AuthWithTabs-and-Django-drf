package Authenticating.SignupLoginFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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

import Authenticating.SignupLogin;

public class Signup extends Fragment {
    private static final String ROOT_URL_POST = "http://192.168.43.87:5000/WebIntApi/users/";
    RequestQueue requestQueue;
    TextInputLayout signup_uname,signup_uemail,signup_upassword1,signup_upassword2;

    String uname,upassd,upassd2,uemail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup, container, false);
         signup_uname = (TextInputLayout) view.findViewById(R.id.signup_uname);
         signup_uemail = (TextInputLayout) view.findViewById(R.id.signup_uemail);
         signup_upassword1 = (TextInputLayout) view.findViewById(R.id.signup_upassword1);
         signup_upassword2 = (TextInputLayout) view.findViewById(R.id.signup_upassword2);

        Button signupButton =(Button)view.findViewById(R.id.signupButton);
         signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (upassd==upassd2){
                uname = signup_uname.getEditText().getText().toString();
                upassd = signup_upassword1.getEditText().getText().toString();
                upassd2 = signup_upassword2.getEditText().getText().toString();
                uemail = signup_uemail.getEditText().getText().toString();

                posting(uname, upassd, uemail);
//                getChildFragmentManager().beginTransaction().replace(R.id.signuplayout,new Login()).commit();

//                    } else {
//                    Toast.makeText(getActivity(), "Passwords not matching", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void posting(String uname, String upasword, String uemail) {
        requestQueue = Volley.newRequestQueue(getActivity());

        JSONObject parameters = new JSONObject();

        try {
            parameters.put("username", uname);
            parameters.put("password", upasword);
            parameters.put("email", uemail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ROOT_URL_POST, parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(getActivity(), MainActivity.class));
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
