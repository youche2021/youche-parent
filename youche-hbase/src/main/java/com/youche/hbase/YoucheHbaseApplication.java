package com.youche.hbase;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
@SpringBootApplication
public class YoucheHbaseApplication extends SpringBootServletInitializer implements BeanFactoryPostProcessor, CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(YoucheHbaseApplication.class, args);
    }

    /**
     * 如果打War包，在tomcat启动，需要继承 SpringBootServletInitializer 实现 configure
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YoucheHbaseApplication.class);
    }

    /**
     * Bean 延迟加载，加快启动速度
     *
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        for (String beanName : configurableListableBeanFactory.getBeanDefinitionNames()) {
            configurableListableBeanFactory.getBeanDefinition(beanName).setLazyInit(true);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("[{}] 启动成功!", YoucheHbaseApplication.class.getName());
    }
}
