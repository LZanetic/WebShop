package com.example.njava.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private static final String KORISNIK_PRINT_JOB_IDENTITY = "korisnikPrintJob";
    private static final String KORISNIK_PRINT_TRIGGER ="korisnikPrintTrigger";

    private static final String PROIZVOD_PRINT_JOB_IDENTITY = "ProizvodPrintJob";
    private static final String PROIZVOD_PRINT_TRIGGER ="ProizvodPrintTrigger";

    @Bean
    public JobDetail korisnikPrintJobDetail(){
        return JobBuilder.newJob(KorisnikPrintJob.class).withIdentity(KORISNIK_PRINT_JOB_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger korisnikPrintTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1).withRepeatCount(1);
        return TriggerBuilder.newTrigger().forJob(korisnikPrintJobDetail())
                .withIdentity(KORISNIK_PRINT_TRIGGER).withSchedule(scheduleBuilder).build();
    }

    @Bean
    public JobDetail proizvodPrintJobDetail(){
        return JobBuilder.newJob(ProizvodPrintJob.class).withIdentity(PROIZVOD_PRINT_JOB_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger ProizvodPrintTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1).withRepeatCount(1);
        return TriggerBuilder.newTrigger().forJob(proizvodPrintJobDetail())
                .withIdentity(PROIZVOD_PRINT_TRIGGER).withSchedule(scheduleBuilder).build();
    }
}
