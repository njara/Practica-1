package njdevelop.cl.especialistavendedor;

import java.io.Serializable;

/**
 * Created by nicol on 27-01-2016.
 */
public class Solicitud implements Serializable {

    String id;
    String cliente;
    String pasillo;
    String estado;

    public Solicitud(String id, String cliente, String pasillo, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.pasillo = pasillo;
        this.estado = estado;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasillo() {
        return pasillo;
    }

    public void setPasillo(String pasillo) {
        this.pasillo = pasillo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean equals(Solicitud o) {
        if (o.getId().equals(id) && o.getCliente().equals(cliente) && o.getPasillo().equals(pasillo) && getEstado().equals(estado)){
            return true;
        }
        else return false;
    }
}
