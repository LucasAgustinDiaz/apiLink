package com.mercadolibre.demo.services;

import com.mercadolibre.demo.config.ConfigEnvironmentUtility;
import com.mercadolibre.demo.interfaces.IGeneratorID;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class GeneratorLinkIDService implements IGeneratorID {
    // configuracion para leer el archivo propierties
    ConfigEnvironmentUtility configUtility;


    public GeneratorLinkIDService(ConfigEnvironmentUtility configUtility) {
        this.configUtility = configUtility;
    }

    @Override
    public String generateID() throws IOException {
        Date date = new Date();
        return configUtility.getProperty("model_id")+date.getTime();
    }
}
