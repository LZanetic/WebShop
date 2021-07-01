package com.example.njava.korisnik;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Primary
@Repository
public class JdbcKorisnkRepository implements KorisnikRepository{

    private static final String SELECT_ALL = "SELECT * FROM Korisnik";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter ;

    public JdbcKorisnkRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Korisnik")
                .usingGeneratedKeyColumns("Id");
    }


    @Override
    public Set<Korisnik> findAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToKorisnik));
    }

    @Override
    public Optional<Korisnik> findByUsername(String username) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE Username = ?", this::mapRowToKorisnik, username)
            );
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Korisnik> save(Korisnik korisnik) {
        try {
            korisnik.setId(saveKorisnikDetails(korisnik));
            return Optional.of(korisnik);

        }catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Korisnik> update(String username, Korisnik updatedKorisnik) {
        int executed = jdbc.update("UPDATE korisnik set " +
                "Ime = ?," +
                "Prezime = ?," +
                "Password = ?," +
                "Kontakt = ?,"+
                "Lokacija = ?,"+
                "Email = ?,"+
                "BrojTelefona = ?"+
                    "WHERE Username = ?",
                updatedKorisnik.getIme(),
                updatedKorisnik.getPrezime(),
                updatedKorisnik.getPassword(),
                updatedKorisnik.getKontakt(),
                updatedKorisnik.getLokacija(),
                updatedKorisnik.getEmail(),
                updatedKorisnik.getBrojTelefona(),
                updatedKorisnik.getUsername());

        if(executed > 0){
            return Optional.of(updatedKorisnik);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public void deleteByUsername(String username) {
        jdbc.update("DELETE FROM korisnik WHERE Username = ?", username);
    }

    private Korisnik mapRowToKorisnik(ResultSet rs, int rowNum) throws SQLException{
        return new Korisnik(
                rs.getLong("Id"),
                rs.getString("Ime"),
                rs.getString("Prezime"),
                rs.getString("Username"),
                rs.getString("Password"),
                rs.getString("Kontakt"),
                rs.getString("Lokacija"),
                rs.getString("Email"),
                rs.getString("BrojTelefona"));
    }

    private long saveKorisnikDetails(Korisnik korisnik){
        Map<String, Object> values = new HashMap<>();

        values.put("ime", korisnik.getIme());
        values.put("prezime", korisnik.getPrezime());
        values.put("username", korisnik.getUsername());
        values.put("password", korisnik.getPassword());
        values.put("kontakt", korisnik.getKontakt());
        values.put("lokacija", korisnik.getLokacija());
        values.put("email", korisnik.getEmail());
        values.put("brojTelefona", korisnik.getBrojTelefona());

        return inserter.executeAndReturnKey(values).longValue();
    }
}
