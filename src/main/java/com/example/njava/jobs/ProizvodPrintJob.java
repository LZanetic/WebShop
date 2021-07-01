package com.example.njava.jobs;


import com.example.njava.proizvod.Proizvod;
import com.example.njava.proizvod.ProizvodRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class ProizvodPrintJob extends QuartzJobBean {

    private ProizvodRepository proizvodRepository;

    public ProizvodPrintJob(ProizvodRepository proizvodRepository) {
        this.proizvodRepository = proizvodRepository;
    }

    private Logger logger = LoggerFactory.getLogger(ProizvodPrintJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        final List<Proizvod> proizvodList = proizvodRepository.findAll();

        if (!proizvodList.isEmpty()){
            logger.info("Lista Proizvoda");
            logger.info("------------------------------");
            proizvodList.stream()
                    .filter(proizvod -> !proizvod.getNaslov().isEmpty())
                    .forEach(
                            proizvod -> logger.info(proizvod.toString())
                    );
            logger.info("------------------------------");
        } else {
            logger.info("Trenutno nema proizvoda");
        }

    }
}
