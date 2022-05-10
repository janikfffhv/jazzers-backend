package at.fhv.jazzers.backend;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Server {
    @PostConstruct
    public void init() {

    }
}