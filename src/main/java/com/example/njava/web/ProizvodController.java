package com.example.njava.web;

import com.example.njava.proizvod.Proizvod;
import com.example.njava.proizvod.ProizvodDTO;
import com.example.njava.proizvod.ProizvodService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("proizvod")
@CrossOrigin(origins = "http://localhost:4200")
public class ProizvodController {
    private final ProizvodService proizvodService;

    public ProizvodController(ProizvodService proizvodService) {
        this.proizvodService = proizvodService;
    }

    @GetMapping
    public List<ProizvodDTO> getAllProizvod() {
        return proizvodService.findAll();
    }

    @GetMapping("/kId/{korisnikId}")
    public List<ProizvodDTO> getByKorisnik(@PathVariable Long korisnikId){
        return proizvodService.findByKorisnikId(korisnikId);
    }

    @GetMapping("/{naslov}")
    public ProizvodDTO getByNaslov(@PathVariable String naslov){
        return proizvodService.findByNaslov(naslov);
    }

    @PostMapping
    @Transactional
    public Proizvod save(@Valid @RequestBody Proizvod proizvod){
        return proizvodService.save(proizvod);
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/byId/{id}")
    public void deleteById(@PathVariable Long id){
        proizvodService.deleteById(id);
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{naslov}")
    public void deleteByNaslov(@PathVariable String naslov) {
        proizvodService.deleteByNaslov(naslov);
    }


    @GetMapping("Id/{id}")
    public Optional<ProizvodDTO> getById(@PathVariable Long id){
        return proizvodService.findById(id);
    }

    @Modifying
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Proizvod proizvod, @PathVariable Long id){
        proizvodService.update(proizvod.getNaslov(), proizvod.getOpis(), proizvod.getStanje(), proizvod.getCijena(), proizvod.getKategorija(), proizvod.getId());
    }
}
