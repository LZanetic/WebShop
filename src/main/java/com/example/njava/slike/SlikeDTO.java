package com.example.njava.slike;



public class SlikeDTO {
    private final String slika;
    private final Long proizvodId;
    private final Long proizvodKorisnikId;

    public SlikeDTO(String slike, Long proizvodId, Long proizvodKorisnikId) {
        this.slika = slike;
        this.proizvodId = proizvodId;
        this.proizvodKorisnikId = proizvodKorisnikId;
    }

    public String getSlika() {
        return slika;
    }

    public Long getProizvodId() {
        return proizvodId;
    }

    public Long getProizvodKorisnikId() {
        return proizvodKorisnikId;
    }

    @Override
    public String toString() {
        return "SlikaDTO{" +
                "slika=" + slika +
                ", proizvodId=" + proizvodId +
                ", proizvodKorisnikId=" + proizvodKorisnikId +
                '}';
    }
}
