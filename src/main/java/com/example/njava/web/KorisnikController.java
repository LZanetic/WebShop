package com.example.njava.web;

import com.example.njava.korisnik.KorisnikCommand;
import com.example.njava.korisnik.KorisnikDTO;
import com.example.njava.korisnik.KorisnikService;
import com.example.njava.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Set;

@RestController
@RequestMapping("korisnik")
@CrossOrigin(origins = "http://localhost:4200")
public class KorisnikController {
    private final KorisnikService korisnikService;


    public KorisnikController(KorisnikService korisnikService) throws SQLException {
        this.korisnikService = korisnikService;
    }


    @GetMapping
    public Set<KorisnikDTO> getAllKorisnik(){
        return korisnikService.findAll();
    }

    @GetMapping("/{Username}")
    public ResponseEntity<KorisnikDTO> getKorisnikByUsername(@PathVariable final String Username){
        return korisnikService.findByUsername(Username).map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @PostMapping
    @Transactional
    public ResponseEntity<KorisnikDTO> save(@Valid @RequestBody final KorisnikCommand command){
        return korisnikService.save(command)
                .map(korisnikDTO -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(korisnikDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .build()
                );
    }

    @PutMapping("/{Username}")
    public  ResponseEntity<KorisnikDTO> update(@PathVariable String Username, @Valid @RequestBody final KorisnikCommand updateKorisnikCommand){
        return korisnikService.update(Username, updateKorisnikCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @GetMapping("/current-user")
    public ResponseEntity<KorisnikDTO> getCurrentUser() {
        return SecurityUtils.getCurrentUserUsername()
                .map(
                        username -> korisnikService.findByUsername(username)
                                .map(ResponseEntity::ok)
                                .orElseGet(
                                        () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                                )
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{Username}")
    public void delete(@PathVariable String Username){ korisnikService.deleteByUsername(Username);}
}
