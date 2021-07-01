package com.example.njava.korisnik;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Prezime")
    private String prezime;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Kontakt")
    private String kontakt;

    @Column(name = "Lokacija")
    private String lokacija;

    @Column(name = "Email")
    private String email;

    @Column(name = "BrojTelefona")
    private String brojTelefona;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
    )
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Korisnik() {

    }

    public Korisnik(long id, String ime, String prezime, String username, String password, String kontakt, String lokacija, String email, String brojTelefona) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.kontakt = kontakt;
        this.lokacija = lokacija;
        this.email = email;
        this.brojTelefona = brojTelefona;
    }

    public Korisnik(String ime, String prezime, String username, String password, String kontakt, String lokacija, String email, String brojTelefona) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.kontakt = kontakt;
        this.lokacija = lokacija;
        this.email = email;
        this.brojTelefona = brojTelefona;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public String getKontakt() {
        return kontakt;
    }

    public String getLokacija() {
        return lokacija;
    }

    public String getEmail() {
        return email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Korisnik korisnik = (Korisnik) o;
        return id == korisnik.id && Objects.equals(ime, korisnik.ime) && Objects.equals(prezime, korisnik.prezime) && Objects.equals(username, korisnik.username) && Objects.equals(password, korisnik.password) && Objects.equals(kontakt, korisnik.kontakt) && Objects.equals(lokacija, korisnik.lokacija) && Objects.equals(email, korisnik.email) && Objects.equals(brojTelefona, korisnik.brojTelefona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, username, password, kontakt, lokacija, email, brojTelefona);
    }
}
