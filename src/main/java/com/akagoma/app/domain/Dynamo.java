package com.akagoma.app.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.Table;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.TransactWriteItemsEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class Dynamo {

    static DynamoDbEnhancedClient getClient() {

        return DynamoDbEnhancedClient.builder()
            .dynamoDbClient(DynamoDbClient.builder().region(Region.US_EAST_2)
                .endpointOverride(URI.create(""))
                .build())
            .build();
    }

    public static <T> void put(T record, Class<T> type) {
        DynamoDbTable<T> table = getClient().table(type.getAnnotation(Table.class).name(),
            TableSchema.fromBean(type));
        table.putItem(record);
    }

    public static <T> void update(T record, Class<T> type) {
        DynamoDbTable<T> table = getClient().table(type.getAnnotation(Table.class).name(),
            TableSchema.fromBean(type));
        table.updateItem(record);
    }

    public static <T> List<T> findAll(Class<T> type) {

        DynamoDbTable<T> table = getClient().table(type.getAnnotation(Table.class).name(),
            TableSchema.fromBean(type));

        Iterator<T> results = table.scan().items().iterator();

        List<T> list = new ArrayList<>();
        results.forEachRemaining(list::add);
        return list;
    }

    public static <T> TransactWriteItemsEnhancedRequest.Builder addPutItem(
        TransactWriteItemsEnhancedRequest.Builder builder, T item, Class<T> type) {
        DynamoDbTable<T> table = getClient().table(type.getAnnotation(Table.class).name(),
            TableSchema.fromBean(type));

        if (Objects.nonNull(builder)) {
            return builder.addPutItem(table, item);
        } else {
            return TransactWriteItemsEnhancedRequest.builder().addPutItem(table, item);
        }
    }

    public static void transactWriteItems(TransactWriteItemsEnhancedRequest request) {
        getClient().transactWriteItems(request);
    }
}
