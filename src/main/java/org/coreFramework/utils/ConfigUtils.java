package org.coreFramework.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigUtils {
        private Properties prop;
        //Singelton Pattern
        private ConfigUtils(){
            prop = readProperties();
        }
        private static ConfigUtils configUtils;

        public static ConfigUtils getInstance(){
            if (configUtils == null){
                configUtils = new ConfigUtils();
            }
            return configUtils;
        }
        //Methods
        public Properties readProperties() {
            FileInputStream fis;
            String env = System.getProperty("Env","dev");
            try{
                switch (env){
                    case "dev"-> {
                        fis = new FileInputStream("./src/main/resources/env/dev.properties");
                    }
                    case "stage"->{
                        fis = new FileInputStream("./src/main/resources/env/stage.properties");
                    }
                    case "uat"-> {
                        fis = new FileInputStream("./src/main/resources/env/uat.properties");
                    }
                    case "prod"->{
                        fis = new FileInputStream("./src/main/resources/env/prod.properties");
                    }
                    default -> {
                        throw new RuntimeException("Environment is not supported!");
                    }
                }
                prop = new Properties();
                prop.load(fis);
            }catch (Exception error){
                throw new RuntimeException(error.getCause());
            }
            return prop;
        }

        public String getAppUrl() {
            return prop.get("AppBaseURL").toString();
        }
        public String getApiType(){
            return prop.get("ApiType").toString();
        }
        public String getApiVersion(){
            return prop.get("ApiVersion").toString();
        }
        public String getApiTaskPath(){
            return prop.get("ApiTaskPath").toString();
        }
        public String getApiUserPath(){
            return prop.get("ApiUserPath").toString();
        }
        public String getApiRegisterPath(){
            return prop.get("ApiRegisterPath").toString();
        }
        public String getHeadlessMode(){
            return prop.get("Headless").toString();
        }


}
