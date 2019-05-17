package io.vinays.spark.word;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WordCountService {

    @Autowired
    JavaSparkContext javaSparkContext;

    public Map<String , Long> getCountAll(List<String> words){
        JavaRDD<String> rdd = javaSparkContext.parallelize(words);
        Map<String, Long> wordsMap = rdd.countByValue();
        return wordsMap;
    }

    public Map<String , Long> getCountByMatch(List<String> words, String mWord){
        JavaRDD<String> rdd = javaSparkContext.parallelize(words);
        JavaRDD<String> matchingWords = rdd.filter(s -> s.equalsIgnoreCase(mWord));

        return matchingWords.countByValue();
    }
}
