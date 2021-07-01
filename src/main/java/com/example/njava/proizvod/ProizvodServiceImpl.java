package com.example.njava.proizvod;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProizvodServiceImpl implements ProizvodService{
    private final ProizvodRepository proizvodRepository;

    public ProizvodServiceImpl(ProizvodRepository proizvodRepository) {
        this.proizvodRepository = proizvodRepository;
    }

    @Override
    public List<ProizvodDTO> findAll() {
        return proizvodRepository.findAll().stream().map(this::mapProizvodToDTO).collect(Collectors.toList());
    }

    private ProizvodDTO mapProizvodToDTO(Proizvod proizvod) {
        return new ProizvodDTO(proizvod.getKorisnikId(), proizvod.getNaslov(), proizvod.getOpis(), proizvod.getStanje(), proizvod.getCijena(), proizvod.getKategorija());
    }

    @Override
    public Optional<ProizvodDTO> findById(Long id) {
        return proizvodRepository.findById(id).map(this::mapProizvodToDTO);
    }

    @Override
    public List<ProizvodDTO> findByKorisnikId(Long korisnikId) {
        return proizvodRepository.findAllByKorisnikId(korisnikId).stream().map(this::mapProizvodToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        proizvodRepository.deleteById(id);
    }

    @Override
    public void deleteByNaslov(String naslov) {
       Proizvod proizvod = proizvodRepository.findByNaslov(naslov);
       proizvodRepository.delete(proizvod);
    }

    @Override
    public ProizvodDTO findByNaslov(String naslov) {
        Proizvod proizvod = proizvodRepository.findByNaslov(naslov);
        return mapProizvodToDTO(proizvod);
    }


    public Proizvod save(Proizvod proizvod) {
        return proizvodRepository.save(proizvod);
    }

    @Override
    public void update(String naslov, String opis, String stanje, Integer cijena, String kategorija, Long id) {
        proizvodRepository.update(naslov, opis, stanje, cijena, kategorija, id);
    }
}
