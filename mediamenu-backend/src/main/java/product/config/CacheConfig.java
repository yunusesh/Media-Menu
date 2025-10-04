package product.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
    public CacheManager cacheManager(Caffeine<Object, Object> caffeineConfig){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("getAlbumCache",
                "searchAlbumCache",
                "searchArtistCache",
                "getArtistCache");
        cacheManager.setCaffeine(caffeineConfig);
        return cacheManager;
    }

    @Bean
    public Caffeine<Object, Object> caffeineConfig(){
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .maximumSize(500)
                .recordStats();
    }
}