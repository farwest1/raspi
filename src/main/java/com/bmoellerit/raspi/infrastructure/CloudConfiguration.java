package com.bmoellerit.raspi.infrastructure;

import com.pi4j.io.gpio.PinState;
import io.swagger.client.api.VersionApi;
import io.swagger.client.model.Version;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Bernd on 04.11.2017.
 *
 * Package com.bmoellerit.raspi.infrastructure
 */
@Component
public class CloudConfiguration {

  private final static Logger LOGGER = LoggerFactory.getLogger(CloudConfiguration.class);

  private HashMap<String,String> config;

  @Autowired(required = false)
  private RaspberryPiGPIO raspberryPiGPIO = null;

  @Autowired
  private VersionApi versionApi;


  @Scheduled(fixedRateString = "${raspi.configrate}")
  public void readConfig(){
    LOGGER.info("Read cloud configuration");
    Version version = versionApi.getVersion();
    if(version.getMinor() > 0){
      if(raspberryPiGPIO != null) {
        raspberryPiGPIO.getOutputPin().pulse(version.getMinor() * 100, PinState.HIGH);
      }
    }
    LOGGER.info(version.toString());
  }

}
