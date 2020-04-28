package rmiguele.transaction.validation.application;

import com.mongodb.MongoClient;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

class Configuration {

    @Startup
    @ApplicationScoped
    @Produces
    MongoClient mongoClient() {
        return new MongoClient();
    }

}