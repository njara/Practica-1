package njdevelop.cl.especialistavendedor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import njdevelop.cl.especialistavendedor.R;

public class AdapterCustom extends ArrayAdapter<Solicitud> {
    private final Context context;
    private final ArrayList<Solicitud> values;

    public AdapterCustom(Context context, ArrayList<Solicitud> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.itemcustom, parent, false);

        TextViewCustom textView1 = (TextViewCustom) rowView.findViewById(R.id.itemtitle);
        TextViewCustom textView2 = (TextViewCustom) rowView.findViewById(R.id.itempasillo);
        TextViewCustom textView3 = (TextViewCustom) rowView.findViewById(R.id.itemestado);

        textView1.setText(values.get(position).getCliente());
        textView2.setText(values.get(position).getPasillo());
        textView3.setText(values.get(position).getEstado());
        // change the icon for Windows and iPhone
        return rowView;
    }
}