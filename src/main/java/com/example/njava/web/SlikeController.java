package com.example.njava.web;


import com.example.njava.slike.Slike;
import com.example.njava.slike.SlikeDTO;
import com.example.njava.slike.SlikeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("slike")
@CrossOrigin(origins = "http://localhost:4200")
public class SlikeController {
    private final SlikeService slikeService;


    public SlikeController(SlikeService slikeService) throws SQLException {
        this.slikeService = slikeService;
    }

    @GetMapping("/slike/poruka")
    public String helloWorld(){
        return "poruka";
    }


    @GetMapping("")
    public List<SlikeDTO> getAllSlike() {
        return slikeService.findAll();
    }

    @GetMapping("/{proizvodId}")
    public List<SlikeDTO> getByProizvodId(@PathVariable final Long proizvodId) {
        return slikeService.findByProizvodId(proizvodId);
    }


    @GetMapping( "Korisnik/{proizvodKorisnikId}")
    public List<SlikeDTO> getByProizvodKorisnikId(@PathVariable final Long proizvodKorisnikId){
        return slikeService.findByProizvodKorisnikId(proizvodKorisnikId);

    }


    @PostMapping
    @Transactional
    public Slike dodajSliku(@Valid @RequestBody Slike slika)
    {
        return slikeService.save(slika);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{proizvodId}")
    @Transactional
    public void deleteByProizvodId(@PathVariable Long proizvodId){
        slikeService.deleteByProizvodId(proizvodId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("Id/{id}")
    @Transactional
    public void deleteById(@PathVariable Long id){
        slikeService.deleteById(id);
    }

}
