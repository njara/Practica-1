package njdevelop.cl.especialistavendedor;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    Solicitud solicitudIntent;
    Solicitud solicitudResponse;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
        progressDialog = new ProgressDialog(this);
        solicitudIntent = (Solicitud)getIntent().getSerializableExtra("Solicitud");
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("RESULT", rawResult.getText()); // Prints scan results
        Log.v("RESULT", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        try {
            String[] result = rawResult.getText().split(",");
            Log.e("QR",result[0].toString());
            solicitudResponse = new Solicitud(result[0], result[1], result[2], result[3]);
            if (solicitudResponse.equals(solicitudResponse)){
                llamar();
                Toast.makeText(this,"Atendiendo Solicitud",Toast.LENGTH_LONG).show();
            }
            else dialog();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }

    private void llamar() {

        // Instantiate the RequestQueue.
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Comprobando");
        progressDialog.setMessage("Comprobando Datos...");
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
                      finish();
                      /// /  Intent intent = new Intent(SimpleScannerActivity.this, SolicitudesActivity.class);
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

                params.put("id", solicitudResponse.getId());
                params.put("estado", "Atendiendo...");

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
    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Solicitud Incorrecta")
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}