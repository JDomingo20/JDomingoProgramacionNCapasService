
package com.JDomingoProgramacionNCapas.DAO;

import com.JDomingoProgramacionNCapas.JPA.Result;
import com.JDomingoProgramacionNCapas.JPA.UsuarioDireccion;


public interface IDireccionDAO {
    
    Result DireccionAdd(UsuarioDireccion usuarioDireccion);
    Result DireccionDelete(int IdDireccion);
    Result DireccionUpdate(UsuarioDireccion usuarioDireccion);
}
