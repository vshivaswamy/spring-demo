package io.vinays.spark.word;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/words")
public class WordController {

    @Autowired
    WordCountService wordCountService;

    @PostMapping("/count")
    public Map<String, Long> countAll(@RequestBody String input) throws IOException {
        List<String> words = new ObjectMapper().readValue(input, List.class);
        Map<String, Long> count = wordCountService.getCountAll(words);
        return count;
    }

    @PostMapping("/count/{word}")
    public Map<String, Long> countAll(@RequestBody String input, @PathVariable String word) throws IOException {
        List<String> words = new ObjectMapper().readValue(input, List.class);
        Map<String, Long> count = wordCountService.getCountByMatch(words, word);
        return count;
    }
}
