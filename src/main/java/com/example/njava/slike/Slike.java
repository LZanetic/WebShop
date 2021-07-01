package com.example.njava.slike;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Slike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Proizvod_Id")
    private Long proizvodId;

    @Column(name = "Proizvod_Korisnik_Id")
    private Long proizvodKorisnikId;


    @Column(name = "Slika")
    public String slika;


    public Slike(Long proizvodId, Long proizvodKorisnikId, String slika) {
        this.proizvodId = proizvodId;
        this.proizvodKorisnikId = proizvodKorisnikId;
        this.slika = slika;
    }

    public Slike() {

    }

    public Long getId() {
        return id;
    }

    public Long getProizvodId() {
        return proizvodId;
    }

    public Long getProizvodKorisnikId() {
        return proizvodKorisnikId;
    }

    public String getSlika() {
        return slika;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProizvodId(Long proizvodId) {
        this.proizvodId = proizvodId;
    }

    public void setProizvodKorisnikId(Long proizvodKorisnikId) {
        this.proizvodKorisnikId = proizvodKorisnikId;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
