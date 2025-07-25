package tech.habegger.graphql.course.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import tech.habegger.graphql.course.GraphQLRuntime;

import java.sql.Connection;
import java.sql.DriverManager;

public class GraphQLHttpServer {

    public static void main(String[] args)  {
        try {
            System.out.println("Starting GraphQL Server");

            Connection dbConnection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "graphqlcourse"
            );

            GraphQLRuntime graphQLRuntime = new GraphQLRuntime(dbConnection);

            Server server = new Server(8080);
            ContextHandlerCollection contexts = new ContextHandlerCollection();
            contexts.addHandler(new ContextHandler(new GraphiQLHandler(), "/"));
            contexts.addHandler(new ContextHandler(new GraphQLHandler(graphQLRuntime), "/graphql"));
            server.setHandler(contexts);

            server.start();
            System.out.println("GraphQL Server Started");
            server.join();
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
            e.printStackTrace();
        }
    }
}
