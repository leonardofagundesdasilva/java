package tech.habegger.graphql.course.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import org.apache.logging.log4j.core.util.IOUtils;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Context;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import tech.habegger.graphql.course.GraphQLRuntime;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class GraphQLHandler extends Handler.Abstract {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final GraphQLRuntime graphQLRuntime;

    public GraphQLHandler(GraphQLRuntime graphQLRuntime) {
        this.graphQLRuntime = graphQLRuntime;
    }

    @Override
    public boolean handle(Request httpRequest, Response response, Callback callback) throws Exception {
        GraphQLRequest graphQLRequest = graphqlRequestFromHttp(httpRequest);

        ExecutionResult result = graphQLRuntime.execute(graphQLRequest.query(), graphQLRequest.variables(), graphQLRequest.operationName());
        Content.Sink.write(response, true, MAPPER.writeValueAsString(result.toSpecification()), callback);
        return true;
    }

    private GraphQLRequest graphqlRequestFromHttp(Request httpRequest) throws IOException {
        var contentType = httpRequest.getHeaders().get(HttpHeader.CONTENT_TYPE);
        var mimeType = MimeTypes.getBaseType(contentType);
        var charset = Optional.ofNullable(MimeTypes.getCharsetFromContentType(contentType)).orElse(StandardCharsets.UTF_8.name());

        if (mimeType == null) {
            throw new UnsupportedOperationException("Don't know how to handle 000 %s".formatted(contentType));
        }
        InputStream is = Content.Source.asInputStream(httpRequest);
        Reader httpRequestReader = new InputStreamReader(is, charset);

        return switch (mimeType.getBaseType().asString()) {
            case "application/graphql" -> unmarshalGraphQLRequest(httpRequestReader);
            case "application/json" -> unmarshalJsonRequest(httpRequestReader);
            default -> throw new UnsupportedOperationException("Don't know how to handle 111 %s".formatted(contentType));
        };
    }

    private GraphQLRequest unmarshalJsonRequest(Reader jsonContent) throws IOException {
        return MAPPER.readValue(jsonContent,GraphQLRequest.class);
    }

    private GraphQLRequest unmarshalGraphQLRequest(Reader graphqlContent) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(graphqlContent,writer);

        return new GraphQLRequest(writer.toString(), null, null);
    }
}
