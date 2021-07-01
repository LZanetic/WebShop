package com.example.njava.slike;

import java.util.List;

public interface SlikeService {

    List<SlikeDTO> findAll();

    List<SlikeDTO> findByProizvodId(Long proizvodId);

    List<SlikeDTO> findByProizvodKorisnikId(Long proizvodKorsnikId);

    void deleteById(Long id);

    void deleteByProizvodId(Long proizvodId);

    Slike save(Slike slika);
}
