package com.mackittipat.macbootelasticsearch.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "kibana_sample_data_ecommerce")
@Data
public class Order {

    @Field(type = FieldType.Keyword, name = "currency")
    private String currency;
    @Field(type = FieldType.Text, name = "customer_full_name")
    private String customerFullName;
    @Field(type = FieldType.Keyword, name = "customer_gender")
    private String customerGender;
}
