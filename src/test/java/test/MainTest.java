package test;

import com.apollographql.apollo3.exception.ApolloException;
import com.apollographql.apollo3.runtime.java.ApolloClient;
import com.example.HelloQuery;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;

import java.io.IOException;

public class MainTest {

    @Test
    public void test() throws IOException, InterruptedException {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse()
                .setBody("{\"data\": {\"hello\": \"world\"}}")
                .setResponseCode(200)
        );
        server.start(9999);
        ApolloClient client = new ApolloClient.Builder()
                .serverUrl(server.url("/").toString())
                .build();


        HelloQuery mockQuery = new HelloQuery();

        client.query(mockQuery).enqueue(response -> {
            System.out.println("****response:{}" + response.data);
            if (response.data != null) {
                // No errors
            } else {
                // Errors
                if (response.exception instanceof ApolloException) {
                    // Network error
                    System.out.println("****exception:{}" + response.exception);
                } else {
                    // GraphQL errors
                    System.out.println("****errors:{}" + response.errors);
                }
            }
        });

        // Give some time for the callback to fire
        Thread.sleep(1000);
    }
}
