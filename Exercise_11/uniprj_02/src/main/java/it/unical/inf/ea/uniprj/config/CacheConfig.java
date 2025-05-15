package it.unical.inf.ea.uniprj.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableCaching
@EnableScheduling
@Slf4j
public class CacheConfig {

  public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static final String CACHE_FOR_STUDENTS = "STUDENT";
  public static final String CACHE_FOR_TEACHER_ID = "TEACHER_ID";

  @Bean("cacheManager")
  public CacheManager manager() {
    return new ConcurrentMapCacheManager(CACHE_FOR_STUDENTS, CACHE_FOR_TEACHER_ID);
  }

  @CacheEvict(allEntries = true, value = { CACHE_FOR_STUDENTS })
  @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 500)
  public void studentIdCacheEvict() {
    log.info(String.format("Flush Cache[%s] at [%s]", CACHE_FOR_STUDENTS, formatter.format(LocalDateTime.now())));
  }

  @CacheEvict(allEntries = true, value = { CACHE_FOR_TEACHER_ID })
  @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 500)
  public void teacherIdCacheEvict() {
    log.info(String.format("Flush Cache[%s] at [%s]", CACHE_FOR_TEACHER_ID, formatter.format(LocalDateTime.now())));
  }

  @Scheduled(cron = "0 0 * * * Sun")
  public void sendEmail() {
    log.info("SEND EMAIL");
  }
}
