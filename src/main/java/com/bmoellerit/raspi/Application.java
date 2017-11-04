package com.bmoellerit.raspi;

/**
 * Created by Bernd on 01.11.2017.
 *
 * Package com.bmoellerit.raspi
 */

import com.bmoellerit.raspi.infrastructure.RaspberryPiGPIO;
import io.swagger.client.ApiClient;
import io.swagger.client.api.VersionApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired
  private RaspiConfiguration raspiConfiguration;

  public static void main(String args[]) {
    SpringApplication.run(Application.class);
  }


  @Bean
  public VersionApi versionApi(){
    return new VersionApi(apiClient());
  }

  @Bean
  public ApiClient apiClient() {
    return new ApiClient().setBasePath(raspiConfiguration.getConfigurl());
  }

  @Bean(initMethod = "init")
  @Scope("singleton")
  @Profile("rpi")
  public RaspberryPiGPIO raspberryPiGPIO(){
    return new RaspberryPiGPIO();
  }


}