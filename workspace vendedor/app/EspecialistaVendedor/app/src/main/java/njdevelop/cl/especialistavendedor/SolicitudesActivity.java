package njdevelop.cl.especialistavendedor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SolicitudesActivity extends AppCompatActivity {
    TextView toolbartitle;
    ListView listView;
    ArrayList<Solicitud> arraySolicitud;
    ProgressDialog progressDialog;
    AdapterCustom adapter;
    Runnable runnable;
    final Handler handler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arraySolicitud = new ArrayList<Solicitud>();
        Typeface miso_light = Typeface.createFromAsset(getAssets(), Utils.FONT_MISO_LIGHT);

        toolbartitle = (TextView) toolbar.findViewById(R.id.toolbartitle);
        toolbartitle.setText("Solicitudes de Atención");
        toolbartitle.setTypeface(miso_light);

        Solicitud nico = new Solicitud("","Nicolas Jara","4","Pendiente");
        Solicitud cami = new Solicitud("","Camila Perez","4","Atendido");

        arraySolicitud.add(nico);
        arraySolicitud.add(cami);

        listView = (ListView) findViewById(R.id.listViewSolicitudes);

        adapter = new AdapterCustom(this,
                arraySolicitud);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SolicitudesActivity.this, SolicitudActivity.class);
                intent.putExtra("Solicitud", arraySolicitud.get(position));
                startActivity(intent);
            }
        });
        progressDialog = new ProgressDialog(this);
        runnable = new Runnable() {
            public void run() {
                request();
                handler.postDelayed(runnable, 5000);
            }
        };
        handler.post(runnable);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            handler.removeCallbacks(runnable);
        }catch (Exception e){
            e.printStackTrace();
        }

        handler.post(runnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            handler.removeCallbacks(runnable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            handler.removeCallbacks(runnable);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void request() {
        arraySolicitud.clear();
        // Instantiate the RequestQueue.
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Comprobando");
        progressDialog.setMessage("Enviando Datos...");
        //  progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        // progressDialog.setProgress(0);
        // progressDialog.setMax(20);
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://njara.cl/tecnoa/lista_solicitud.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.e("Error", response);
                        try {JSONObject responsejson = new JSONObject(response);
                            JSONArray array;
                            array = responsejson.getJSONArray("array");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject solicitudjson = new JSONObject(String.valueOf(array.getJSONObject(i)));
                                Solicitud solicitud = new Solicitud(solicitudjson.getString("id"),solicitudjson.getString("Cliente"),solicitudjson.getString("Pasillo"), solicitudjson.getString("Estado"));
                                arraySolicitud.add(solicitud);
                            }

                        } catch (Throwable t) {

                        }
                        adapter.notifyDataSetChanged();
                        cancelDialog();
                      //  Intent intent = new Intent(SimpleScannerActivity.this, SolicitudesActivity.class);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ERROR", "That didn't work!");
                cancelDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        queue.add(stringRequest);
    }

    public void cancelDialog() {

        try {
            progressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            handler.removeCallbacks(runnable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
