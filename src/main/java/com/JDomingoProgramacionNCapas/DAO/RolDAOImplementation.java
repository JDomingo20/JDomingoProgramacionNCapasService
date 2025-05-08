
package com.JDomingoProgramacionNCapas.DAO;

import com.JDomingoProgramacionNCapas.JPA.Result;
import com.JDomingoProgramacionNCapas.JPA.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOImplementation implements IRolDAO  {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        Result result = new Result();
        
        try {
            TypedQuery<Rol> queryRol = entityManager.createQuery("FROM Rol", Rol.class);
            result.object = queryRol.getResultList();
            result.correct=true;
        } catch (Exception ex) {
            result.correct=false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
}
