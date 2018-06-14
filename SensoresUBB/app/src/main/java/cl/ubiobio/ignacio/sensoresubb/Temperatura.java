package cl.ubiobio.ignacio.sensoresubb;

import java.util.Date;

public class Temperatura {
    public String fecha;
    public String hora;
    public String valor;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Temperatura() {
    }
}

