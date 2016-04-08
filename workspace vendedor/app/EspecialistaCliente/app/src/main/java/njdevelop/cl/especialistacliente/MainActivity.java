package njdevelop.cl.especialistacliente;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.MacAddress;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    TextViewCustom textViewCustomPasillo;

    TextView toolbartitle;
    ImageButton imageButton;
    private BeaconManager beaconManager;
    private Region region;
    String Pasillo = "No Info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface miso_light = Typeface.createFromAsset(getAssets(), Utils.FONT_MISO_LIGHT);
        toolbartitle = (TextView) toolbar.findViewById(R.id.toolbartitle);
        toolbartitle.setText("Vendedor Espacialista Cliente");
        toolbartitle.setTypeface(miso_light);

        textViewCustomPasillo = (TextViewCustom) findViewById(R.id.textViewPasillo);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo();
            }
        });
        //     textViewCustom = (TextViewCustom)findViewById(R.id.prueba);
        //  textViewCustom =
        beaconManager = new BeaconManager(this);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Beacon nearestBeacon = list.get(0);
                    updatePasillo(nearestBeacon);
                //    List<String> places = placesNearBeacon(nearestBeacon);
                    // TODO: update the UI here
                //    Log.d("Airport", "Nearest places: " + places);
                }

            }

        });
        region = new Region("ranged region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);
    //    showNotification("Usted se encuentra en:", "Pasillo 5");
    }

    public void updatePasillo(Beacon beacon){
        String result = "No Info";
        String response = beacon.getMacAddress().toString();
        if (response.equals("[DB:01:2C:7E:15:35]")){
            result = "Pasillo 1";
            showNotification("Usted se encuentra en:", "Pasillo 1");
        }
        else if (response.equals("[DB:4E:51:76:B6:36]")){
            result = "Pasillo 2";
            showNotification("Usted se encuentra en:", "Pasillo 2");
        }
        else  if (response.equals("[E4:6C:8E:DB:0B:9A]")){
            result = "Pasillo 3";
            showNotification("Usted se encuentra en:", "Pasillo 3");
        }
        else  if (response.equals("[FE:CF:D9:1D:28:A1]")){
            result = "Pasillo 4";
            showNotification("Usted se encuentra en:", "Pasillo 4");
        }
        else  if (response.equals("[EA:BE:C0:04:3B:89]")){
            result = "Pasillo 5";
            showNotification("Usted se encuentra en:", "Pasillo 5");
        }
        else
            result = "No Info";

        Pasillo = result;

        textViewCustomPasillo.setText(result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dialogo() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Ingrese Su Nombre");
    //    alertDialog.setMessage("Enter Password");

        final EditText input = new EditText(MainActivity.this);
        input.setHint("Ej: Luis Jara");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int id) {
        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
        intent.putExtra("Nombre", input.getText().toString());
        intent.putExtra("Pasillo", Pasillo);
        startActivity(intent);
    }
});
        alertDialog.show();

    }

    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
