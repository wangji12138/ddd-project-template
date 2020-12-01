package cn.changemax.ddd.interfaces.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ClassUtils;

/**
 * @author WangJi
 * @Description redis(远程字典服务 配置类)
 * @Date 2020/12/1 14:25
 */
@Slf4j
@Configuration
public class RedisConfig {


    @Value("${spring.redis.database}")
    private int database = 0;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password = null;

    @Value("${spring.redis.timeout}")
    private int timeout = 5000;


    private int connectionMinimumIdleSize = 10;
    private int idleConnectionTimeout = 10000;
    private int connectTimeout = 10000;
    private int retryAttempts = 3;
    private int retryInterval = 1500;
    private int subscriptionsPerConnection = 5;
    private String clientName = null;
    private int subscriptionConnectionMinimumIdleSize = 1;
    private int subscriptionConnectionPoolSize = 50;
    private int connectionPoolSize = 64;
    private boolean dnsMonitoring = false;
    private int dnsMonitoringInterval = 5000;

    private String codecName = "org.redisson.codec.JsonJacksonCodec";

    /**
     * 单Redis节点模式
     * 程序化配置
     *
     * @return
     */
    @Bean(name = "redissonClient", destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public RedissonClient redissonClient() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Config config = new Config();
        config.useSingleServer().setAddress(String.format("redis://%s:%s", this.host, this.port))
                .setDatabase(this.database)
                .setTimeout(this.timeout)

                .setConnectionMinimumIdleSize(this.connectionMinimumIdleSize)
                .setConnectionPoolSize(this.connectionPoolSize)
                .setDnsMonitoringInterval(this.dnsMonitoringInterval)
                .setSubscriptionConnectionMinimumIdleSize(this.subscriptionConnectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(this.subscriptionConnectionPoolSize)
                .setSubscriptionsPerConnection(this.subscriptionsPerConnection)
                .setClientName(this.clientName)
                .setRetryAttempts(this.retryAttempts)
                .setRetryInterval(this.retryInterval)
                .setConnectTimeout(this.connectTimeout)
                .setIdleConnectionTimeout(this.idleConnectionTimeout);

        if (StringUtils.isNotBlank(this.password)) {
            config.useSingleServer().setPassword(this.password);
        }

        Codec codec = (Codec) ClassUtils.forName(this.codecName, ClassUtils.getDefaultClassLoader()).newInstance();
        config.setCodec(codec);
        config.setEventLoopGroup(new NioEventLoopGroup());

        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用 String的序列化方式
        // value序列化方式采用 jackson
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //设置hash key value 采用 String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }

}
