package sumdu.edu.ua.radchenko.lab3;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import sumdu.edu.ua.radchenko.lab3.model.MovieParser;
import sumdu.edu.ua.radchenko.lab3.model.POIDocCreator;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@PropertySource("classpath:application.properties")
public class Lab3Application {

    private final static Logger logger = Logger.getLogger(Lab3Application.class);

    private String ThreadNamePrefix;
    private int CorePoolSize;
    private int MaxPoolSize;
    private int QueueCapacity;

    @Autowired
    public void setThreadNamePrefix(@Value("${thread.name.prefix}") String ThreadNamePrefix) {
        this.ThreadNamePrefix = ThreadNamePrefix;
    }

    @Autowired
    public void setCorePoolSize(@Value("${core.pool.size}") int CorePoolSize) {
        this.CorePoolSize = CorePoolSize;
    }

    @Autowired
    public void setMaxPoolSize(@Value("${max.pool.size}") int MaxPoolSize) {
        this.MaxPoolSize = MaxPoolSize;
    }

    @Autowired
    public void setQueueCapacity(@Value("${queue.capacity}") int QueueCapacity) {
        this.QueueCapacity = QueueCapacity;
    }

    public static void main(String[] args) {
        SpringApplication.run(Lab3Application.class, args);
    }

    @Bean(name="processExecutor")
    public TaskExecutor workExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix(ThreadNamePrefix);
        threadPoolTaskExecutor.setCorePoolSize(CorePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(MaxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(QueueCapacity);
        threadPoolTaskExecutor.afterPropertiesSet();
        logger.info("ThreadPoolTaskExecutor set");
        return threadPoolTaskExecutor;
    }
}
