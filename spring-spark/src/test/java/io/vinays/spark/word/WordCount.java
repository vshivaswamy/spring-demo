package io.vinays.spark.word;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.util.ResourceUtils;
import scala.Tuple2;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class WordCount {
    private static final Pattern SPACE = Pattern.compile(",");

    public static void main(String[] args) throws Exception {

        SparkConf sparkConf = new SparkConf().setAppName("WordCount").setMaster("local");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        File file = ResourceUtils.getFile("classpath:data/names.txt");
        System.out.println(file.getPath());
        JavaRDD<String> lines = ctx.textFile(file.getPath());

        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

        JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

        List<Tuple2<String, Integer>> output = counts.collect();
        for (Tuple2<?, ?> tuple : output) {
            System.out.println(tuple._1() + ": " + tuple._2());
        }
        ctx.stop();
    }
}
