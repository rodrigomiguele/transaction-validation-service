package rmiguele.transaction.validation.application;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.DBCollectionFindOptions;
import io.quarkus.test.Mock;
import org.mockito.stubbing.Answer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockConfiguration {

    @ApplicationScoped
    @Produces
    @Mock
    public MongoClient mockMongoClient() {
        MongoClient mongoClient = mock(MongoClient.class);
        when(mongoClient.getMongoClientOptions()).thenReturn(mock(MongoClientOptions.class));
        when(mongoClient.getDatabase(any())).thenAnswer(
                (Answer<MongoDatabase>) invocation -> {
                    MongoDatabase database = mock(MongoDatabase.class, invocation.getArgument(0, String.class));
                    when(database.withCodecRegistry(any())).thenReturn(database);
                    when(database.getCollection(any(), any())).thenAnswer((Answer<MongoCollection>) invocation1 ->
                            mock(MongoCollection.class, invocation1.getArgument(0, String.class)));
                    return database;
                });
        when(mongoClient.getDB(any())).thenAnswer((Answer<DB>) invocation -> {
            DB db = mock(DB.class, invocation.getArgument(0, String.class));
            when(db.getCollection(any())).thenAnswer((Answer<DBCollection>) invocation1 -> {
                DBCollection dbColl = mock(DBCollection.class, invocation1.getArgument(0, String.class));
                when(dbColl.find(any(DBObject.class), any(DBCollectionFindOptions.class))).thenAnswer((Answer<DBCursor>) invocation2 -> {
                    DBCursor cursor = mock(DBCursor.class);
                    when(cursor.setDecoderFactory(any())).thenReturn(cursor);
                    return cursor;
                });
                return dbColl;
            });
            return db;
        });
        return mongoClient;
    }
}
