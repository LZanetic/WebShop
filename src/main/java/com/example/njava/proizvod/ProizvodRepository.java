package com.example.njava.proizvod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
public interface ProizvodRepository extends JpaRepository<Proizvod,Long> {
    List<Proizvod> findAllByKorisnikId(Long korisnikId);

    Optional<Proizvod> findById(Long id);

    Proizvod findByNaslov(String naslov);

    @Modifying
    @Query("UPDATE Proizvod p SET p.naslov = ?1, p.opis = ?2, p.stanje = ?3, p.cijena = ?4, p.kategorija = ?5 where p.id = ?6")
    void update(String naslov, String opis, String stanje, Integer cijena, String kategorija, Long id);
}
