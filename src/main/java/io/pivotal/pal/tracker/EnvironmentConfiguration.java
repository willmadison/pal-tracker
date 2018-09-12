package io.pivotal.pal.tracker;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "environment")
public class EnvironmentConfiguration {

    private String port;

    private String memoryLimit;

    private String cfInstanceIndex;

    private String cfInstanceAddress;

    private String dataSourceUrl;

    public boolean disableHttps = true;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getCfInstanceIndex() {
        return cfInstanceIndex;
    }

    public void setCfInstanceIndex(String cfInstanceIndex) {
        this.cfInstanceIndex = cfInstanceIndex;
    }

    public String getCfInstanceAddress() {
        return cfInstanceAddress;
    }

    public void setCfInstanceAddress(String cfInstanceAddress) {
        this.cfInstanceAddress = cfInstanceAddress;
    }

    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    public void setDataSourceUrl(String dataSourceUrl) {
        this.dataSourceUrl = dataSourceUrl;
    }

    public boolean isDisableHttps() {
        return disableHttps;
    }

    public void setDisableHttps(boolean disableHttps) {
        this.disableHttps = disableHttps;
    }
}
