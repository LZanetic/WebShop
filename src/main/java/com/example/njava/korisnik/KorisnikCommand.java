package com.example.njava.korisnik;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

public class KorisnikCommand {

    @NotBlank(message = "Ime ne smije biti prazno")
    private String ime;

    @NotBlank(message = "Ime ne smije biti prazno")
    private String prezime;

    @NotBlank(message = "Username ne smije biti prazno")
    private String username;

    @NotBlank(message = "Password ne smije biti prazan")
    private String password;

    @Email(message = "Ne validan Email")
    private String email;

    @NotBlank(message = "Kontakt ne smije biti prazno")
    private String kontakt;

    @NotBlank(message = "Lokacija ne smije biti prazno")
    private String lokacija;

    @NotBlank(message = "Broj Mobitela/Telefona ne smije biti prazno")
    @PositiveOrZero(message = "Brojevi u Broj Mobitela/Telefona moraju biti pozitivni")
    private String brojTelefona;

    @NotBlank
    private Set<Authority> authorities;

    public Set<Authority> getAuthorities() {
        return authorities;
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

    public String getEmail() {
        return email;
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
}
