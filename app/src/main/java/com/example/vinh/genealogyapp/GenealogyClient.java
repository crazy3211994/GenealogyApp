package com.example.vinh.genealogyapp;

import android.util.Log;

import com.example.vinh.genealogyapp.Model.Member;
import com.example.vinh.genealogyapp.Model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by VINH on 4/3/2016.
 */
public class GenealogyClient {

    public static void setBasicAuth(String username, String password) {
        GenealogyClientBase.setBasicAuth(username, password);
    }

    public static void checkUser(final IGenealogyAPI listener) {
        GenealogyClientBase.post("giapha/checkuser", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                // Parse JSON here
                User user = null;

                try {
                    if (response.getString("role").equalsIgnoreCase("user")) {
                        user = new User();
                        user.setId(response.getInt("id"));
                        user.setUsername(response.getString("username"));
                        Log.i("@GenealogyClientUsage", user.getUsername());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Callback
                listener.OnLoginListener(user);
            }

        });
    }

    public static void getAllMembers(final IGenealogyAPI listener) {
        // Setup RequestParam here

        // Call @GenealogyClientBase get() or post()
        GenealogyClientBase.post("giapha/getmembers", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                // Parse JSON here
                List<Member> members = new ArrayList<>();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    for (int i = 0; i < response.length(); ++i) {
                        Member member = new Member();
                        JSONObject memberObj = response.getJSONObject(i);

                        member.setMemberID(memberObj.getInt("memberID"));
                        member.setName(memberObj.getString("name"));
                        member.setAvatar(memberObj.getString("avatar"));
                        member.setBirthDate(formatter.parse(memberObj.getString("birthDate")));
                        member.setAddress(memberObj.getString("address"));
                        member.setBirthPlace(memberObj.getString("birthPlace"));
                        member.setGender(memberObj.getString("gender"));

                        // Add member to list
                        members.add(member);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Callback
                listener.OnGetAllMembersListener(members);
            }
        });
    }

    public static void getMemberDetail(final IGenealogyAPI listener) {
        GenealogyClientBase.get("giapha/getmember", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                // Parse JSON here
                Member member = new Member();

                // Callback
                listener.OnGetMemberDetailListener(member);
            }
        });
    }
}

class GenealogyClientBase {
    private static final String BASE_URL = "https://tamrestfultest.herokuapp.com/webservice/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * Set username/password for any host and realm for a particular request.
     * By default the Authentication Scope is for any host, port and realm.
     * @param username
     * @param password
     */
    static void setBasicAuth(String username, String password) {
        client.setBasicAuth(username,password);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
