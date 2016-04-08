package njdevelop.cl.especialistavendedor;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SolicitudActivity extends AppCompatActivity {
    TextView toolbartitle;
    Solicitud solicitud;
    TextViewCustom textViewCustomCliente;
    TextViewCustom textViewCustomPasillo;
    LinearLayout linearLayoutAtender;
    LinearLayout linearLayoutCancelar;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface miso_light = Typeface.createFromAsset(getAssets(), Utils.FONT_MISO_LIGHT);
        toolbartitle = (TextView) toolbar.findViewById(R.id.toolbartitle);
        toolbartitle.setText("Solicitud");
        toolbartitle.setTypeface(miso_light);

        solicitud = (Solicitud)getIntent().getSerializableExtra("Solicitud");

        textViewCustomCliente = (TextViewCustom)findViewById(R.id.textViewCliente);
        textViewCustomPasillo = (TextViewCustom)findViewById(R.id.textViewPasillo);
        textViewCustomCliente.setText(solicitud.getCliente());
        textViewCustomPasillo.setText(solicitud.getPasillo());

        linearLayoutAtender = (LinearLayout)findViewById(R.id.linearLayoutAceptar);
        linearLayoutAtender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SolicitudActivity.this, SimpleScannerActivity.class);
                intent.putExtra("Solicitud", solicitud);
                startActivity(intent);
            }
        });

        linearLayoutCancelar = (LinearLayout) findViewById(R.id.linearLayoutAceptar2);
        linearLayoutCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request("Cancelado", "Cancelando", "Cancelando la Solicitud...");
            }
        });
        progressDialog = new ProgressDialog(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              //          .setAction("Action", null).show();
                onBackPressed();
            }
        });
    }
    private void request(final String value, String title, String msg) {

        // Instantiate the RequestQueue.
        progressDialog.setCancelable(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        //  progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        // progressDialog.setProgress(0);
        // progressDialog.setMax(20);
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://njara.cl/tecnoa/update_solicitud.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        JSONArray array;
                        try {
                            array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject solicitud = new JSONObject(String.valueOf(array.getJSONObject(i)));
                            }

                        } catch (Throwable t) {

                        }
                        cancelDialog();

                        if(value.equals("Cancelado")){
                            finish();
                        }else if(value.equals("Atendido"))
                        {
                            finish();
                        }
                     //   Intent intent = new Intent(SolicitudActivity.this, SolicitudesActivity.class);
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

                params.put("id", solicitud.getId());
                params.put("estado", value);

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
        dialog();
    }

    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Solicitud Terminada?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        request("Atendido", "Enviando", "Finalizando la Solicitud...");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       finish();
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}
