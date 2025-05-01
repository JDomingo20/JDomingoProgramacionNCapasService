package com.JDomingoProgramacionNCapas.RestController;

import com.JDomingoProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.JDomingoProgramacionNCapas.JPA.Result;
import com.JDomingoProgramacionNCapas.JPA.UsuarioDireccion;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarioapi")
public class UsuarioRestController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @GetMapping
    public ResponseEntity GetAll() {
        Result result;
        result = usuarioDAOImplementation.GetAllJPA();
        if (result.correct) {
            if (result.objects.isEmpty()) {
                return ResponseEntity.status(204).body(null);
            } else {
                return ResponseEntity.ok(result);
            }
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/getbyid/{idUsuario}")
    public ResponseEntity GetById(@PathVariable int idUsuario) {
        Result result = usuarioDAOImplementation.GetById(idUsuario);

        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }

    public ResponseEntity Add(@RequestBody UsuarioDireccion usuarioDireccion) {

        Result result = usuarioDAOImplementation.AddJPA(usuarioDireccion);
        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }

}
