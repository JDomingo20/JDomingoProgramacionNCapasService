package com.JDomingoProgramacionNCapas.DAO;

import com.JDomingoProgramacionNCapas.JPA.Result;
import com.JDomingoProgramacionNCapas.JPA.Municipio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioDAOImplementation implements IMunicipioDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetByEstado(int IdEstado) {
        Result result = new Result();

        try {
            TypedQuery<Municipio> QueryMunicipio = entityManager.createQuery("FROM Municipio WHERE Estado.IdEstado = :idEstado", Municipio.class);
            QueryMunicipio.setParameter("idEstado", IdEstado);
            result.object = QueryMunicipio.getResultList();
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
