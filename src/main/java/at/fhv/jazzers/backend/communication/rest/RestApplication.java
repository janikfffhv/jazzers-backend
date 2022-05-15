package at.fhv.jazzers.backend.communication.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1")
public class RestApplication extends Application {
    // Here we can register our web services
    // Leaving this empty registers all web services found in this project
}
