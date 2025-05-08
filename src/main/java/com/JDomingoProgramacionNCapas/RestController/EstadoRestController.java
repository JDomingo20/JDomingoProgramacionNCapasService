package com.JDomingoProgramacionNCapas.RestController;

import com.JDomingoProgramacionNCapas.DAO.EstadoDAOImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadoapi")
public class EstadoRestController {

    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    
    @GetMapping("/byidpais/{idpais}")
    public ResponseEntity GetByIdPais(@PathVariable int idpais){
        
    return ResponseEntity.ok(estadoDAOImplementation.GetByPais(idpais));
}

}
