
package com.JDomingoProgramacionNCapas.DAO;

import com.JDomingoProgramacionNCapas.JPA.Result;
import com.JDomingoProgramacionNCapas.JPA.Usuario;
import com.JDomingoProgramacionNCapas.JPA.UsuarioDireccion;




public interface IUsuarioDAO {
    Result GetAllJPA();
    Result GetById(int IdUsuario);
    Result AddJPA(UsuarioDireccion usuarioDireccion);
    Result DeleteJPA(int IdUsuario);
    Result UsuarioUpdate(Usuario usuario);
}
