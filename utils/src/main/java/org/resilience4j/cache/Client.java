package org.resilience4j.cache;

import io.github.resilience4j.cache.Cache;

import javax.cache.Caching;

/**
 * @author: zyr
 * @date: 2019/5/24
 * @description: 缓存
 */
public class Client {
    public static void main(String[] args) {
        javax.cache.Cache<String, String> cacheInstance = Caching.getCache("cacheName", String.class, String.class);
        Cache<String, String> cacheContext = Cache.of(cacheInstance);
        // todo ??? 找不到文档里的api

        cacheContext.getEventPublisher()
                .onCacheHit(event -> System.out.println("cache hit"))
                .onCacheMiss(event -> System.out.println("cache miss"))
                .onError(event -> System.out.println("error," + event));
    }
}
