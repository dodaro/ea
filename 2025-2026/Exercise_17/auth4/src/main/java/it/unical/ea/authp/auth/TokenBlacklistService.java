package it.unical.ea.authp.auth;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Blacklist in-memory dei jti revocati.
 * NB: didattica/dev. In produzione usare uno store esterno (Redis) con TTL
 * pari alla scadenza del token, altrimenti la blacklist cresce all'infinito
 * e si perde al riavvio del processo.
 */
@Service
public class TokenBlacklistService {

    private final Set<String> revokedJti = ConcurrentHashMap.newKeySet();

    public void revoke(String jti) {
        if (jti != null) {
            revokedJti.add(jti);
        }
    }

    public boolean isRevoked(String jti) {
        return jti != null && revokedJti.contains(jti);
    }
}
