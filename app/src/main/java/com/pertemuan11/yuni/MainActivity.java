package com.pertemuan11.yuni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


        private TextView txtid1, txtjudul1, txttgl1, txtisi1,txtJurusan,txtAkreditasi,txtAlamat;
        private RequestQueue requestQueue;
        private StringRequest stringRequest;
        ArrayList<HashMap<String, String>> list_data;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            String url = "http://192.168.228.54/mobile.tampilan.php";

            txtjudul1=(TextView)findViewById(R.id.txtjudul);
            txttgl1 = (TextView)findViewById(R.id.txttgl);
            txtisi1 = (TextView)findViewById(R.id.txtisi);
            txtJurusan =(TextView)findViewById(R.id.jurusan);
            txtAkreditasi =(TextView)findViewById(R.id.akreditasi);
            txtAlamat =(TextView)findViewById(R.id.alamat);

            requestQueue = Volley.newRequestQueue(MainActivity.this);
            list_data = new ArrayList<HashMap<String, String>>();
            stringRequest = new StringRequest(Request.Method.GET, url, new
                    Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int a = 0; a < jsonArray.length(); a ++){
                                    JSONObject json = jsonArray.getJSONObject(a);
                                    HashMap<String, String> map = new HashMap<String,
                                            String>();

                                    map.put("judul", json.getString("judul"));
                                    map.put("tgl", json.getString("tgl"));
                                    map.put("isi", json.getString("isi"));
                                    map.put("Jurusan", json.getString("jurusan"));
                                    map.put("Akreditasi", json.getString("Akreditasi"));
                                    map.put("Alamat", json.getString("Alamat"));
                                    list_data.add(map);
                                }

                                txtjudul1.setText(list_data.get(0).get("judul"));
                                txttgl1.setText(list_data.get(0).get("tgl"));
                                txtisi1.setText(list_data.get(0).get("isi"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequest);
        }
    }
