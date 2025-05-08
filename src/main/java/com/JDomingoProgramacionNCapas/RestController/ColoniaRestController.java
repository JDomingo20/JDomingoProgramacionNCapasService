
package com.JDomingoProgramacionNCapas.RestController;

import com.JDomingoProgramacionNCapas.DAO.ColoniaDAOImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coloniaapi")
public class ColoniaRestController {
    
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;
    
    @GetMapping("byidmunicipio/{idMunicipio}")
    public ResponseEntity GetByMunicipio(@PathVariable int idMunicipio){
        return ResponseEntity.ok(coloniaDAOImplementation.GetBYMnucipio(idMunicipio));
    
}
    
}
