package com.example.njava.proizvod;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Proizvod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "KorisnikId")
    private Long korisnikId;

    @Column(name = "Naslov")
    private String naslov;

    @Column(name = "Opis")
    private String opis;

    @Column(name = "Stanje")
    private String stanje;

    @Column(name = "Cijena")
    private Integer cijena;

    @Column(name = "Kategorija")
    private String kategorija;

    public Proizvod(Long korisnikId, String naslov, String opis, String stanje, Integer cijena, String kategorija) {
        this.korisnikId = korisnikId;
        this.naslov = naslov;
        this.opis = opis;
        this.stanje = stanje;
        this.cijena = cijena;
        this.kategorija = kategorija;
    }

    public Proizvod() {

    }

    public Long getId() {
        return id;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getOpis() {
        return opis;
    }

    public String getStanje() {
        return stanje;
    }

    public int getCijena() {
        return cijena;
    }

    public String getKategorija() {
        return kategorija;
    }
}
