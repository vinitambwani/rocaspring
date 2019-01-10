package com.eny.roca.rocablservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="roca.db.service")
public class RocaBlDbServiceConfig {
	
private String url;

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}
}
