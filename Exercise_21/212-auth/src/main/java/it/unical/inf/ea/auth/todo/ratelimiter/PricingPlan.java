package it.unical.inf.ea.auth.todo.ratelimiter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import java.time.Duration;

enum PricingPlan {
    PREVENT (Bandwidth.classic(3, Refill.intervally(10, Duration.ofSeconds(10)))),
    FREE (Bandwidth.classic(10, Refill.intervally(10, Duration.ofHours(1)))),
    BASIC (Bandwidth.classic(40, Refill.intervally(40, Duration.ofHours(1)))),
    PROFESSIONAL (Bandwidth.classic(100, Refill.intervally(100, Duration.ofHours(1))));
    //..

    Bandwidth limit;

    Bandwidth getLimit() {
        return limit;
    }

    PricingPlan(Bandwidth limit) {
        this.limit=limit;
    }


    static PricingPlan resolvePlanFromApiKey(String apiKey) {
        if (apiKey.startsWith("PREVENT")) {
            return PREVENT;
        } else if (apiKey.startsWith("FREE-")) {
            return FREE;
        } else if (apiKey.startsWith("PX001-")) {
            return PROFESSIONAL;
        } else if (apiKey.startsWith("BX001-")) {
            return BASIC;
        }
        return FREE;
    }
}