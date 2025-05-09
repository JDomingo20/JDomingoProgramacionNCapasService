package com.JDomingoProgramacionNCapas.DAO;

import com.JDomingoProgramacionNCapas.JPA.Direccion;
import com.JDomingoProgramacionNCapas.JPA.Result;
import com.JDomingoProgramacionNCapas.JPA.Usuario;
import com.JDomingoProgramacionNCapas.JPA.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionDAOImplementation implements IDireccionDAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Result DireccionAdd(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        Direccion direccion = new Direccion();
        direccion.Usuario = new Usuario();
        entityManager.persist(direccion);
        try {
            result.correct=true;
        } catch (Exception ex) {
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result DireccionDelete(int IdDireccion) {
        Result result = new Result();
        try {
            Direccion direccion = new Direccion();
            direccion = entityManager.find(Direccion.class, IdDireccion);
            entityManager.remove(direccion);

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

    @Transactional
    @Override
    public Result DireccionUpdate(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            Direccion direccion = new Direccion();
            direccion.Usuario = new Usuario();
            
            direccion = entityManager.find(Direccion.class, direccion.getIdDireccion());
            direccion.Usuario.setIdUsuario(direccion.Usuario.getIdUsuario());
            entityManager.merge(direccion);
            result.correct=true;
        } catch (Exception ex) {
            result.correct=false;
            result.errorMessage = ex.getLocalizedMessage();
            result. ex =ex;
        }
        return result;
    }

}
