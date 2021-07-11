package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.ref.SoftReference;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    public String getCachingDir() {
        return cachingDir;
    }

    @Override
    protected String load(String key) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(cachingDir + File.separator + key))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
            this.cache.put(key, new SoftReference<>(builder.toString()));
            return cache.get(key).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}