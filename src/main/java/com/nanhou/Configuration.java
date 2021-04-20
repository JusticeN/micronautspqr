package com.nanhou;

import com.nanhou.graphqlspqr.NoteGQLService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.graalvm.polyglot.Context;

import javax.inject.Singleton;

@Factory
public class Configuration {

    @Bean
    @Singleton
    public GraphQL configureGraphql(NoteGQLService noteGQLService) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages("com.nanhou")
                .withOperationsFromSingleton(noteGQLService) //register the service
                .generate();
        return GraphQL.newGraphQL(schema).build();
    }

    @Bean
    public Context graalContext() {
        return Context.newBuilder()
                .allowAllAccess(true)
                .allowCreateThread(true)
                .build();
    }
}
