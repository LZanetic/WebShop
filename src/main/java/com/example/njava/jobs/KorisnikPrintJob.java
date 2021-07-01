package com.example.njava.jobs;

import com.example.njava.korisnik.Korisnik;
import com.example.njava.korisnik.KorisnikRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;


import java.util.Set;

public class KorisnikPrintJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(KorisnikPrintJob.class);

    private final KorisnikRepository korisnikRepository;

    public KorisnikPrintJob(KorisnikRepository korisnikRepository){
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        final Set<Korisnik> korisnikSet = korisnikRepository.findAll();

        if (!korisnikSet.isEmpty()){
            logger.info("Lista Korisnika");
            logger.info("------------------------------");
            korisnikSet.stream()
                    .filter(korisnik -> !korisnik.getUsername().isEmpty())
                    .forEach(
                            korisnik -> logger.info(korisnik.toString())
                    );
            logger.info("------------------------------");
        } else {
            logger.info("Trenutno nema korisnika");
        }
    }
}
