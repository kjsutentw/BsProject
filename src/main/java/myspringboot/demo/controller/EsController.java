package myspringboot.demo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.util.Nsqlutil;
import myspringboot.demo.util.RRException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private TransportClient client;

    @GetMapping("/testInsert")
    public Object testInsert() throws Exception {

        XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject()
                .field("title", "Java is very strict language")
                .field("content", "Need learn many")
                .field("postdate", "2019-04-28")
                .field("url", "csdn.net/9090990").endObject();
        IndexResponse response = client.prepareIndex("index1", "blog", "4").setSource(contentBuilder).get();
        return response.status();
    }

    @GetMapping("/queryAll")
    public Object queryAll(String index,Integer size) {

        List<Map<String, Object>> resMap = new ArrayList<>();
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        SearchResponse response = client.prepareSearch(index).setQuery(queryBuilder).setSize(size).execute().actionGet();
        SearchHits searchHits = response.getHits();
        for (SearchHit single : searchHits) {
            Map<String, Object> singleMap = single.getSourceAsMap();
            resMap.add(singleMap);
        }

        return resMap;
    }

}
