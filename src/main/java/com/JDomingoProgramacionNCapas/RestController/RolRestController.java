
package com.JDomingoProgramacionNCapas.RestController;

import com.JDomingoProgramacionNCapas.DAO.RolDAOImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rolapi")
public class RolRestController {
    @Autowired
    private RolDAOImplementation rolDAOImplementation;
    
    @GetMapping
    public ResponseEntity GetAll(){
        return ResponseEntity.ok(rolDAOImplementation.GetAll());
    }
    
}
