package it.unical.inf.ea.uniprj.config.filter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class LoggingFilter extends AbstractRequestLoggingFilter {

  public LoggingFilter() {
    setIncludePayload(true);
    setIncludeHeaders(true);
    setIncludeQueryString(true);
  }

  @Override
  protected void beforeRequest(HttpServletRequest request, String message) {
    log.info(message);
  }

  @Override
  protected void afterRequest(HttpServletRequest request, String message) {
    log.info(message);
  }
}
