package sg.edu.nus.iss.ibfb4ssfassessment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import sg.edu.nus.iss.ibfb4ssfassessment.util.*;

@Configuration
public class RedisConfig {

    // inject application.properties into configuration
    @Value("${spring.data.redis.host}")
    private String redisHost; 
	@Value("${spring.data.redis.port}")
    private Integer redisPort;
    @Value("${spring.data.redis.username}")
    private String redisUser;
    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(); 
        redisConfig.setHostName(redisHost);
        redisConfig.setPort(redisPort);

        if (!"NOT_SET".equals(redisUser.trim())) {
            redisConfig.setUsername(redisUser);
            redisConfig.setPassword(redisPassword);
        }  
        
        JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisConnFac = new JedisConnectionFactory(redisConfig,jedisClient);
        jedisConnFac.afterPropertiesSet();

        return jedisConnFac;
    }

    @Bean(Util.REDIS_STRING)
    public RedisTemplate<String, String> redisTemplate() {

        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());

        return template;
    }

    // @Bean(Util.REDIS_TWO)
    // public RedisTemplate<String,Object> redisObjectTemplate() {

    //     RedisTemplate<String,Object>  template = new RedisTemplate<>();
    //     template.setConnectionFactory(jedisConnectionFactory());
    //     template.setKeySerializer(new StringRedisSerializer());
    //     template.setValueSerializer(new StringRedisSerializer());
    //     template.setHashKeySerializer(new StringRedisSerializer());
    //     template.setHashValueSerializer(new StringRedisSerializer());

    //     return template;
    // }
}   
