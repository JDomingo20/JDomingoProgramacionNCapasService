package com.JDomingoProgramacionNCapas.DAO;

import com.JDomingoProgramacionNCapas.JPA.Colonia;
import com.JDomingoProgramacionNCapas.JPA.Direccion;
import com.JDomingoProgramacionNCapas.JPA.Estado;
import com.JDomingoProgramacionNCapas.JPA.Municipio;
import com.JDomingoProgramacionNCapas.JPA.Pais;
import com.JDomingoProgramacionNCapas.JPA.Result;
import com.JDomingoProgramacionNCapas.JPA.Rol;
import com.JDomingoProgramacionNCapas.JPA.Usuario;
import com.JDomingoProgramacionNCapas.JPA.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO {

    @Autowired
    private EntityManager entityManager; //Conexion con JPA

    @Override
    public Result GetAllJPA() {
        //Está en lenguaje JPQL
        Result result = new Result();

        try {
            TypedQuery<com.JDomingoProgramacionNCapas.JPA.Usuario> queryUsuarios = entityManager.createQuery("FROM Usuario", com.JDomingoProgramacionNCapas.JPA.Usuario.class);
            List<com.JDomingoProgramacionNCapas.JPA.Usuario> usuarios = queryUsuarios.getResultList();

            result.objects = new ArrayList<>();
            for (com.JDomingoProgramacionNCapas.JPA.Usuario usuarioJPA : usuarios) {

                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = usuarioJPA;
                TypedQuery<com.JDomingoProgramacionNCapas.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idUsuario", com.JDomingoProgramacionNCapas.JPA.Direccion.class);
                queryDireccion.setParameter("idUsuario", usuarioJPA.getIdUsuario());

                List<com.JDomingoProgramacionNCapas.JPA.Direccion> direccionesJPA = queryDireccion.getResultList();
                usuarioDireccion.Direcciones = direccionesJPA;

                result.objects.add(usuarioDireccion);
            }
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetById(int IdUsuario) {
        Result result = new Result();

        try {
            Usuario usuario = entityManager.find(Usuario.class, IdUsuario);
            result.object = usuario;
            
            result.correct=true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result AddJPA(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {
            entityManager.persist(usuarioDireccion.Usuario);
            usuarioDireccion.Direccion.Usuario = usuarioDireccion.Usuario;
            entityManager.persist(usuarioDireccion.Direccion);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

//    @Transactional
//    @Override
//    public Result UsuarioUpdateJPA(Usuario usuario) {
//        Result result = new Result();
//        com.JDomingoProgramacionNCapas.JPA.Usuario usuarioJPA = new com.JDomingoProgramacionNCapas.JPA.Usuario();
//        usuarioJPA = entityManager.find(com.JDomingoProgramacionNCapas.JPA.Usuario.class, usuario.getIdUsuario());
//
//        usuarioJPA.setNombre(usuario.getNombre());
//        usuarioJPA.setApellidoPaterno(usuario.getApellidoPaterno());
//        usuarioJPA.setApellidoMaterno(usuario.getApellidoMaterno());
//        usuarioJPA.setNumeroTelefonico(usuario.getNumeroTelefonico());
//        usuarioJPA.setCorreoElectronico(usuario.getCorreoElectronico());
//        usuarioJPA.setFechaNacimiento(usuario.getFechaNacimiento());
//        usuarioJPA.setUserName(usuario.getUserName());
//        usuarioJPA.setPassword(usuario.getSexo());
//        usuarioJPA.setCURP(usuario.getCURP());
//        usuarioJPA.setCelular(usuario.getCelular());
//        usuarioJPA.Rol = new com.JDomingoProgramacionNCapas.JPA.Rol();
//        usuarioJPA.Rol.setIdRol(usuario.Rol.getIdRol());
//        entityManager.merge(usuarioJPA);
//        try {
//            result.correct = true;
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//        return result;
//    }
//
//    @Transactional
//    @Override
//    public Result DeleteJPA(int idUsuario) {
//        Result result = new Result();
//        try {
//            com.JDomingoProgramacionNCapas.JPA.Usuario usuarioJPA= entityManager.find(com.JDomingoProgramacionNCapas.JPA.Usuario.class, idUsuario);
//
//            if (usuarioJPA != null) {
//                // Buscar todas las direcciones relacionadas
//                Query query = entityManager.createQuery("SELECT d FROM Direccion d WHERE d.Usuario.idUsuario = :idUsuario");
//                query.setParameter("idUsuario", idUsuario);
//                List<Direccion> direcciones = query.getResultList();
//
//                for (Direccion d : direcciones) {
//                    entityManager.remove(d);
//                }
//
//                entityManager.remove(usuarioJPA);
//                result.correct = true;
//            } else {
//                result.correct = false;
//                result.errorMessage = "Usuario no encontrado";
//            }
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getMessage();
//            result.ex = ex;
//        }
//        return result;
//    }
//
//    @Transactional
//    @Override
//    public Result DireccionUpdateJPA(UsuarioDireccion usuarioDireccion) {
//        Result result = new Result();
//        try {
//            com.JDomingoProgramacionNCapas.JPA.Direccion direccionJPA = new com.JDomingoProgramacionNCapas.JPA.Direccion();
//            direccionJPA.setIdDireccion(usuarioDireccion.Direccion.getIdDireccion());
//            direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
//            direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
//            direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
//
//            direccionJPA.Colonia = new com.JDomingoProgramacionNCapas.JPA.Colonia();
//            direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());
//
//            direccionJPA.Usuario = new com.JDomingoProgramacionNCapas.JPA.Usuario();
//            direccionJPA.Usuario.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());
//
//            //Vaciar ML a JPA
//            entityManager.merge(direccionJPA);
//
//            result.correct = true;
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//        return result;
//    }
//
//    @Transactional
//    @Override
//    public Result DireccionDeleteJPA(int IdDireccion) {
//        Result result = new Result();
//
//        try {
//            com.JDomingoProgramacionNCapas.JPA.Direccion direccionJPA = new com.JDomingoProgramacionNCapas.JPA.Direccion();
//            direccionJPA = entityManager.find(com.JDomingoProgramacionNCapas.JPA.Direccion.class, IdDireccion);
//            entityManager.remove(direccionJPA);
//
//            result.correct = true;
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//        return result;
//    }
//
//    @Transactional
//    @Override
//    public Result DireccionAddJPA(UsuarioDireccion usuarioDireccion) {
//        Result result = new Result();
//
//        try {
//            com.JDomingoProgramacionNCapas.JPA.Direccion direccionJPA = new com.JDomingoProgramacionNCapas.JPA.Direccion();
//            direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
//            direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
//            direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
//
//            direccionJPA.Colonia = new com.JDomingoProgramacionNCapas.JPA.Colonia();
//            direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());
//
//            direccionJPA.Usuario = new com.JDomingoProgramacionNCapas.JPA.Usuario();
//            direccionJPA.Usuario.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());
//            entityManager.persist(direccionJPA);
//
//            result.correct = true;
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//
//        return result;
//    }
}
