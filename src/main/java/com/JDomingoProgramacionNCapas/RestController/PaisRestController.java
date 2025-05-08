
package com.JDomingoProgramacionNCapas.RestController;

import com.JDomingoProgramacionNCapas.DAO.PaisDAOImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paisapi")
public class PaisRestController {
    
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    
    @GetMapping
    public ResponseEntity GetAll(){
        return ResponseEntity.ok(paisDAOImplementation.GetAll());
    }
}
