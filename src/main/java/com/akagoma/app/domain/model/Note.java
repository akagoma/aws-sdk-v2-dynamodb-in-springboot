package com.akagoma.app.domain.model;

import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Setter
@AllArgsConstructor
@DynamoDbBean
@Table(name = "NOTE_MASTER_TABLE")
public class Note {

    private String id;

    private String userId;

    private String type;

    private String note;

    @DynamoDbPartitionKey
    @DynamoDbAttribute(value = "NOTE_ID")
    public String getId() {
        return id;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute(value = "NOTE_USER_ID")
    public String getUserId() {
        return userId;
    }

    @DynamoDbAttribute(value = "NOTE_TYPE")
    public String getType() {
        return type;
    }

    @DynamoDbAttribute(value = "NOTE")
    public String getNote() {
        return note;
    }
}
