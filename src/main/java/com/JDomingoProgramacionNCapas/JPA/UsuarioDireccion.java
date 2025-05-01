
package com.JDomingoProgramacionNCapas.JPA;

import jakarta.validation.Valid;
import java.util.List;

@Valid
public class UsuarioDireccion {
    public Usuario Usuario;
    public List<Direccion> Direcciones;
    public Direccion Direccion;

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public List<Direccion> getDirecciones() {
        return Direcciones;
    }

    public void setDirecciones(List<Direccion> Direcciones) {
        this.Direcciones = Direcciones;
    }    
}
