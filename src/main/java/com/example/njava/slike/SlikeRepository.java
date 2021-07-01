package com.example.njava.slike;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface SlikeRepository extends JpaRepository<Slike, Long> {

    List<Slike> findByProizvodId(Long proizvodId);

    List<Slike> findByProizvodKorisnikId(Long proizvodKorsnikId);

    void deleteByProizvodId(Long proizvodId);
}
