package com.bmoellerit.raspi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Bernd on 03.11.2017.
 *
 * Package com.bmoellerit.raspi
 */
@Configuration
@ConfigurationProperties(prefix = "raspi")
public class RaspiConfiguration {
  private String configurl;

  public String getConfigurl() {
    return configurl;
  }

  public void setConfigurl(String configurl) {
    this.configurl = configurl;
  }
}
