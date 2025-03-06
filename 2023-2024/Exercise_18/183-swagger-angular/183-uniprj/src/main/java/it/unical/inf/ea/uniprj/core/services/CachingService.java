package it.unical.inf.ea.uniprj.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static it.unical.inf.ea.uniprj.config.CacheConfig.*;

@Service
public class CachingService {

  private static Logger logger = LoggerFactory.getLogger(CachingService.class);

  @CacheEvict(allEntries = true, value = { CACHE_FOR_STUDENTS, CACHE_FOR_TEACHER_ID })
  public void evictAllCacheValues() {logger.info(String.format("Flush (forced) All Caches at [%s]", formatter.format(LocalDateTime.now())));}

  @CacheEvict(allEntries = true, value = { CACHE_FOR_STUDENTS })
  public void evictStudentCacheValues() { logger.info(String.format("Flush (forced) Cache[%s] at [%s]", CACHE_FOR_STUDENTS, formatter.format(LocalDateTime.now())));}

  @CacheEvict(allEntries = true, value = { CACHE_FOR_TEACHER_ID })
  public void evictTeacherIdCacheValues() { logger.info(String.format("Flush (forced) Cache[%s] at [%s]", CACHE_FOR_TEACHER_ID, formatter.format(LocalDateTime.now())));}
}
