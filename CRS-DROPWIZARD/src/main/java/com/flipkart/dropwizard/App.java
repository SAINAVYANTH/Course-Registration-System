package com.flipkart.dropwizard;


/**
 * Hello world!
 *
 */
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.controller.AdminRestApi;
import com.flipkart.controller.CrsRestApi;
import com.flipkart.controller.HelloRestController;
import com.flipkart.controller.ProfessorRestApi;
import com.flipkart.controller.StudentRestApi;


/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
 
    /*
     * It is a dropwizard intitialize method which will intialize the configuration.
     * */
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }
 
    /*
     * Run will execute by the dropwizard container and registered all the web services here.
     * */
    
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
      //  e.jersey().register(new EmployeeRESTController(e.getValidator()));
        e.jersey().register(new HelloRestController());
        e.jersey().register(new CrsRestApi());
        e.jersey().register(new AdminRestApi());
        e.jersey().register(new StudentRestApi());
        e.jersey().register(new ProfessorRestApi());
    }
 
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}