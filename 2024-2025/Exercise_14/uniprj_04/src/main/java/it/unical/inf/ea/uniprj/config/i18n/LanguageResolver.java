package it.unical.inf.ea.uniprj.config.i18n;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

/*
 * Controlla la lingua della richiesta e seleziona il Locale più adatto
 *
 * Estende AcceptHeaderLocaleResolver, quindi sovrascrive
 * il comportamento standard di Spring per decidere quale Locale usare
 */
@Component
public class LanguageResolver extends AcceptHeaderLocaleResolver {

  /*
   * - Recupera l’Accept-Language dalla richiesta HTTP.
   * - Controlla se è una lingua supportata.
   * - Se sì → la usa.
   * - Se no → usa quella di default.
   */
  @Override
  public Locale resolveLocale(HttpServletRequest request) {
    String language = request.getHeader("Accept-Language");
    List<Locale> supportedLocales = getSupportedLocales();
    Locale defaultLocale = getDefaultLocale();

    if (StringUtils.isEmpty(language)) {
      return defaultLocale;
    }
    Locale requestLocale = Locale.forLanguageTag(language);
    if (supportedLocales.contains(requestLocale)) {
      return requestLocale;
    } else {
      return defaultLocale;
    }
  }

}