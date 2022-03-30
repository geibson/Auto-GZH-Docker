package GZH.spring.selenium.Config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties
@EnableConfigurationProperties
public class GZHProperties {

  @Value("${host}")
  private String host;

  @Value("${browser}")
  private String browser;

  @Value("${context}")
  private String context;

}

