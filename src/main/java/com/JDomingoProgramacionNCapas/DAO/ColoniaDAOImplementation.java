
package com.JDomingoProgramacionNCapas.DAO;

import com.JDomingoProgramacionNCapas.JPA.Colonia;
import com.JDomingoProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaDAOImplementation implements IColoniaDAO{
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetBYMnucipio(int IdMunicipio) {
        Result result = new Result();
        
        try {
            TypedQuery<Colonia> QueryColonia = entityManager.createQuery("FROM Colonia WHERE Municipio.IdMunicipio = :idMunicipio", Colonia.class);
            QueryColonia.setParameter("idMunicipio", IdMunicipio);
            result.object = QueryColonia.getResultList();
            
            result.correct=true;
        } catch (Exception ex) {
            result.correct=false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
}
