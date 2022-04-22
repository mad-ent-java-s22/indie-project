package org.davidcalabrese.config;

import org.davidcalabrese.services.OpenAIService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/api") //You may want to add a value here so that all traffic isn't routed to the class below.
//The java class declares root resource and provider classes
public class App extends Application {
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> services = new HashSet<Class<?>>();
        services.add(OpenAIService.class);
        return services;
    }
}