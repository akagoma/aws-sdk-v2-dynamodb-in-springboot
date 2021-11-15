package com.akagoma.app.domain.model;

import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Setter
@AllArgsConstructor
@DynamoDbBean
@Table(name = "USER_MASTER_TABLE")
public class User {

    private String id;

    private String name;

    @DynamoDbPartitionKey
    @DynamoDbAttribute(value = "USER_ID")
    public String getId() {
        return id;
    }

    @DynamoDbAttribute(value = "USER_NAME")
    public String getName() {
        return name;
    }
}
