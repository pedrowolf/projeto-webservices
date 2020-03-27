package com.wolf.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class RestConfig extends ResourceConfig{

	public RestConfig() {
		packages("com.wolf.resources");
	}
}
