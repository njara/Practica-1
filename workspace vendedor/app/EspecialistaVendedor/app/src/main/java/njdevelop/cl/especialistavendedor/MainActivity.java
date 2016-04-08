package njdevelop.cl.especialistavendedor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView toolbartitle;
    LinearLayout linearLayoutAceptar;
    EditText editTextUsuario;
    EditText editTextClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface miso_light = Typeface.createFromAsset(getAssets(), Utils.FONT_MISO_LIGHT);
        toolbartitle = (TextView) toolbar.findViewById(R.id.toolbartitle);
        toolbartitle.setText("Login");
        toolbartitle.setTypeface(miso_light);

        editTextUsuario = (EditTextCustom) findViewById(R.id.editTextUsuario);
        editTextClave = (EditTextCustom) findViewById(R.id.editTextClave);

        linearLayoutAceptar = (LinearLayout) findViewById(R.id.linearLayoutAceptar);
        linearLayoutAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(editTextUsuario.getText().toString(), editTextClave.getText().toString());
            }
        });
    //    startActivity(new Intent(MainActivity.this, SolicitudesActivity.class));
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

    public void login(String usuario, String clave) {
        usuario = usuario.trim();
        clave = clave.trim();

        if (usuario.equals("admin") && clave.equals("admin")) {
            startActivity(new Intent(MainActivity.this, SolicitudesActivity.class));
            finish();
        } else
            alertDialog();
    }

    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Debe ingresar Usuario y Clave válidos.")
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}
