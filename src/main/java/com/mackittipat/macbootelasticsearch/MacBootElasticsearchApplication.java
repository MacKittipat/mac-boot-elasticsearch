package com.mackittipat.macbootelasticsearch;

import com.mackittipat.macbootelasticsearch.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

@Slf4j
@SpringBootApplication
public class MacBootElasticsearchApplication implements CommandLineRunner {

    private final String INDEX_NAME = "kibana_sample_data_ecommerce";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public static void main(String[] args) {
        SpringApplication.run(MacBootElasticsearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Running ...");

        QueryBuilder queryBuilder =
                QueryBuilders
                        .matchQuery("customer_full_name", "mary");

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Order> orderSearchHits =
                elasticsearchOperations.search(searchQuery, Order.class, IndexCoordinates.of(INDEX_NAME));
        orderSearchHits.get().forEach(orderSearchHit -> {
            log.info(orderSearchHit.getContent().toString());
        });
    }
}
