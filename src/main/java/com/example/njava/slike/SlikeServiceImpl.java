package com.example.njava.slike;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class SlikeServiceImpl implements SlikeService {


    private final SlikeRepository slikeRepository;

    public SlikeServiceImpl(SlikeRepository slikeRepository) {
        this.slikeRepository = slikeRepository;
    }


    public List<SlikeDTO> findAll() {
        return slikeRepository.findAll().stream().map(this::mapSlikaToDTO).collect(Collectors.toList());
    }

    private SlikeDTO mapSlikaToDTO(Slike slike) {
        return new SlikeDTO(slike.getSlika(),slike.getProizvodId(),slike.getProizvodKorisnikId());
    }

    @Override
    public List<SlikeDTO> findByProizvodId(Long proizvodId) {
        return slikeRepository.findByProizvodId(proizvodId).stream().map(this::mapSlikaToDTO).collect(Collectors.toList());
    }

    @Override
    public List<SlikeDTO> findByProizvodKorisnikId(Long proizvodKorsnikId) {
        return slikeRepository.findByProizvodKorisnikId(proizvodKorsnikId).stream().map(this::mapSlikaToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        slikeRepository.deleteById(id);
    }

    @Override
    public void deleteByProizvodId(Long proizvodId) {
        slikeRepository.deleteByProizvodId(proizvodId);
    }

    @Override
    public Slike save(Slike slika) {
        mapSlikaToDTO(slika);
        return slikeRepository.save(slika);
    }


}
