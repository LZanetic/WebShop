package com.example.njava.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private static final String KORISNIK_PRINT_JOB_IDENTITY = "korisnikPrintJob";
    private static final String KORISNIK_PRINT_TRIGGER ="korisnikPrintTrigger";

    @Bean
    public JobDetail korisnikPrintJobDetail(){
        return JobBuilder.newJob(KorisnikPrintJob.class).withIdentity(KORISNIK_PRINT_JOB_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger korisnikPrintTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();
        return TriggerBuilder.newTrigger().forJob(korisnikPrintJobDetail())
                .withIdentity(KORISNIK_PRINT_TRIGGER).withSchedule(scheduleBuilder).build();
    }
}
