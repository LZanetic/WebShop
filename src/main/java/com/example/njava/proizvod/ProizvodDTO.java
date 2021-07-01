package com.example.njava.proizvod;

public class ProizvodDTO {

    private Long korisnikId;
    private String naslov;
    private String opis;
    private String stanje;
    private Integer cijena;
    private String kategorija;

    public ProizvodDTO(Long korisnikId, String naslov, String opis, String stanje, Integer cijena, String kategorija) {
        this.korisnikId = korisnikId;
        this.naslov = naslov;
        this.opis = opis;
        this.stanje = stanje;
        this.cijena = cijena;
        this.kategorija = kategorija;
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

    @Override
    public String toString() {
        return "ProizvodDTO{" +
                "korisnikId=" + korisnikId +
                ", naslov='" + naslov + '\'' +
                ", opis='" + opis + '\'' +
                ", stanje='" + stanje + '\'' +
                ", cijena=" + cijena +
                ", kategorija='" + kategorija + '\'' +
                '}';
    }
}
