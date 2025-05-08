
package com.JDomingoProgramacionNCapas.DAO;

import com.JDomingoProgramacionNCapas.JPA.Pais;
import com.JDomingoProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaisDAOImplementation implements IPaisDAO{

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        Result result = new Result();
        
        try {
            TypedQuery<Pais> QueryPais = entityManager.createQuery("FROM Pais", Pais.class);
            result.object = QueryPais.getResultList();
            result.correct= true;
        } catch (Exception ex) {
            result.correct= false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
}
