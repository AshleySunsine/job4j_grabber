package ru.job4j.template;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {
// String produce(String template, Map<String, String> args);
   /* @Test
    public void whenKeyValueOk() {
        Generator generator = new Generator();
        String template = "I am a ${name}, Who are ${subject}?";
        String expected = "I am a Petr Arsentev, Who are you?";
        Map<String, String> args = new HashMap<>();
        args.put("name",  "Petr Arsentev");
        args.put("subject",  "you");
        String line = generator.produce(template, args);
        Assert.assertEquals(line, expected);
        }

    @Test (expected = IllegalArgumentException.class)
    public void whenKeyIsntExist() {
        Generator generator = new Generator();
        String template = "I am a ${AAA}, Who are ${subject}?";
        String expected = "I am a Petr Arsentev, Who are you?";
        Map<String, String> args = new HashMap<>();
        args.put("name",  "Petr Arsentev");
        args.put("subject",  "you");
        String line = generator.produce(template, args);
    }

    @Test (expected = Exception.class )
    public void whenKeysMoreThenNeed() {
        Generator generator = new Generator();
        String template = "Who are ${subject}?";
        String expected = "Who are you?";
        Map<String, String> args = new HashMap<>();
        args.put("name",  "Petr Arsentev");
        args.put("subject",  "you");
        String line = generator.produce(template, args);
    }*/
}