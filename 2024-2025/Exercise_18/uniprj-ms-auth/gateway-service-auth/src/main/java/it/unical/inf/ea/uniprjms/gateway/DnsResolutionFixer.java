package it.unical.inf.ea.uniprjms.gateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.cloud.gateway.config.HttpClientCustomizer;
import org.springframework.stereotype.Component;
import reactor.netty.http.client.HttpClient;

/*
 *  Risolve problemi di DNS che possono verificarsi in ambienti complessi
 *  Questo componente forza WebClient (con Netty) a usare un resolver DNS stabile,
 *  utile per prevenire problemi di risoluzione in ambienti distribuiti o containerizzati.
 *
 *  Imposta un resolver DNS esplicito: DefaultAddressResolverGroup.INSTANCE
 */
@Component
public class DnsResolutionFixer implements HttpClientCustomizer {
  @Override
  public HttpClient customize(HttpClient httpClient) {
    return httpClient.resolver(DefaultAddressResolverGroup.INSTANCE);
  }
}
