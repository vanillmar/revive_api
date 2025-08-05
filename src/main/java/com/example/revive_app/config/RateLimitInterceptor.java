package com.example.revive_app.config;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    // Map<endpoint, Bucket>
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket resolveBucket(String endpoint) {
        // Example: 5 requests per minute per endpoint
        return buckets.computeIfAbsent(endpoint, key ->
                Bucket.builder()
                        .addLimit(limit -> limit.capacity(20).refillGreedy(10, Duration.ofMinutes(1)))
                        .build()
        );
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String endpoint = request.getRequestURI();
        Bucket bucket = resolveBucket(endpoint);
        if (bucket.tryConsume(1)) {
            return true;
        } else {
            response.setStatus(429);
            response.getWriter().write("Too Many Requests");
            return false;
        }
    }
}
