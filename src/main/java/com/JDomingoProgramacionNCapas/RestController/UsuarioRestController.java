package com.JDomingoProgramacionNCapas.RestController;

import com.JDomingoProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.JDomingoProgramacionNCapas.JPA.Result;
import com.JDomingoProgramacionNCapas.JPA.Usuario;
import com.JDomingoProgramacionNCapas.JPA.UsuarioDireccion;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/CargaMasiva")
    public ResponseEntity CargaMasiva(@RequestParam MultipartFile archivo) {
        if (!archivo.isEmpty() || archivo != null) {
            try {
                String tipoArchivo = archivo.getOriginalFilename().split("\\.")[1];
                String root = System.getProperty("user.dir");
                String path = "src/main/resources/static/archivos";
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
                String absolutePath = root + "/" + path + "/" + fecha + archivo.getOriginalFilename();

                //Leer el archivo
                List<UsuarioDireccion> listaUsuarios = new ArrayList();
                if (tipoArchivo.equals("txt")) {
                    //listaUsuarios = LecturaArchivoTXT(new File(absolutePath)); //método para leer la lista
                } else {
                    //   listaUsuarios =  LecturaArchivoExcel(new File(absolutePath));
                }

                //Validar el archivo
            } catch (Exception ex) {
            }
        }

        return null;
    }

    @PutMapping("/update")
    public ResponseEntity Update(@RequestBody Usuario usuario ) {
        Result result = usuarioDAOImplementation.UsuarioUpdate(usuario);
        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }

    }

}
