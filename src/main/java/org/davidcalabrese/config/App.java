package org.davidcalabrese.config;

import org.davidcalabrese.services.OpenAIService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures the openAI API
 */
@ApplicationPath("/api")
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