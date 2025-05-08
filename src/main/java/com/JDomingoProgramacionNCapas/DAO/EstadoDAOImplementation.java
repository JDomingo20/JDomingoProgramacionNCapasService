
package com.JDomingoProgramacionNCapas.DAO;

import com.JDomingoProgramacionNCapas.JPA.Estado;
import com.JDomingoProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImplementation implements IEstadoDAO{
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetByPais(int IdPais) {
        Result result = new Result();
        
        try {
           TypedQuery<Estado> QueryEstado = entityManager.createQuery("FROM Estado WHERE Pais.IdPais = :idPais",Estado.class);
           QueryEstado.setParameter("idPais", IdPais);
           result.object = QueryEstado.getResultList();
            result.correct=true;
        } catch (Exception ex) {
            result.correct=false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
}
