package it.unical.inf.ea.uniprjms.ms.student.config.i18n;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Component
public class LanguageResolver extends AcceptHeaderLocaleResolver {

  @Override
  public Locale resolveLocale(HttpServletRequest request) {
    String language = request.getHeader("Accept-Language");
    List<Locale> supportedLocales = getSupportedLocales();
    Locale defaultLocale = getDefaultLocale();

    if (StringUtils.isEmpty(language)) {
      return defaultLocale;
    } else {
      Locale requestLocale = Locale.forLanguageTag(language);
      if (supportedLocales.contains(requestLocale)) {
        return requestLocale;
      } else {
        return defaultLocale;
      }
    }
  }

}