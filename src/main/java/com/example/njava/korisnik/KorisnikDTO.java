package com.example.njava.korisnik;

import java.util.Set;

public class KorisnikDTO {

    private final String ime;
    private final String prezime;
    private final String username;
    private final String kontakt;
    private final String lokacija;
    private final String brojTelefona;
    private Set<String> authorities;

    public Set<String> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public KorisnikDTO(String ime, String prezime, String username, String kontakt, String lokacija, String brojTelefona) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.kontakt = kontakt;
        this.lokacija = lokacija;
        this.brojTelefona = brojTelefona;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getUsername() {
        return username;
    }

    public String getKontakt() {
        return kontakt;
    }

    public String getLokacija() {
        return lokacija;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    @Override
    public String toString() {
        return "KorisnikDTO{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", kontakt='" + kontakt + '\'' +
                ", lokacija='" + lokacija + '\'' +
                ", brojTelefona='" + brojTelefona + '\'' +
                '}';
    }
}
