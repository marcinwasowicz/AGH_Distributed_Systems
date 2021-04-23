package Main;

import Actors.*;
import Messages.RawDataRequest;
import Messages.RawSatelliteDatabaseQuery;
import Messages.SatelliteDataGeneric;
import akka.actor.typed.*;
import akka.actor.typed.javadsl.Behaviors;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;
import java.io.File;
import java.util.Random;

public class Main {
    public static void initDatabase(MongoClient client){
        MongoCollection<Document> satelliteCollection = client.getDatabase("SatelliteDatabase").getCollection("SatelliteErrors");

        for(int satelliteID = 100; satelliteID <= 199; satelliteID++){
            satelliteCollection.updateOne(Filters.eq("_id", satelliteID), Updates.set("ErrorCount", 0), new UpdateOptions().upsert(true));
        }
    }

    public static Behavior<Void> createSatelliteSystem(int numWorkers, MongoClient databaseConnection) {
        return Behaviors.setup(context -> {
            Random random = new Random();

            ActorRef<SatelliteDataGeneric> dispatcher = context.
                    spawn(Dispatcher.createDispatcher(numWorkers, databaseConnection), "dispatcher",
                            DispatcherSelector.fromConfig("my-dispatcher"));
            ActorRef<SatelliteDataGeneric> station1 = context.
                    spawn(MonitoringStation.createStation("station1", dispatcher), "station1",
                            DispatcherSelector.fromConfig("my-dispatcher"));
            ActorRef<SatelliteDataGeneric> station2 = context.
                    spawn(MonitoringStation.createStation("station2", dispatcher), "station2",
                            DispatcherSelector.fromConfig("my-dispatcher"));
            ActorRef<SatelliteDataGeneric> station3 = context.
                    spawn(MonitoringStation.createStation("station3", dispatcher), "station3",
                            DispatcherSelector.fromConfig("my-dispatcher"));

            station1.tell(new RawDataRequest(100 + random.nextInt(50), 50, 300));
            station3.tell(new RawDataRequest(100 + random.nextInt(50), 50, 300));
            station2.tell(new RawDataRequest(100 + random.nextInt(50), 50, 300));
            station2.tell(new RawDataRequest(100 + random.nextInt(50), 50, 300));
            station3.tell(new RawDataRequest(100 + random.nextInt(50), 50, 300));
            station1.tell(new RawDataRequest(100 + random.nextInt(50), 50, 300));

            Thread.sleep(1000);

            for(int satelliteID = 100; satelliteID <= 199; satelliteID++){
                station1.tell(new RawSatelliteDatabaseQuery(satelliteID));
            }

            return Behaviors.receive(Void.class)
                    .onSignal(Terminated.class, sig -> Behaviors.stopped())
                    .build();
        });
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/application.conf");
        Config config = ConfigFactory.parseFile(file);
        MongoClient databaseConnection = new MongoClient();
        Main.initDatabase(databaseConnection);
        ActorSystem.create(Main.createSatelliteSystem( 10, databaseConnection), "SatelliteSystem", config);
    }
}
