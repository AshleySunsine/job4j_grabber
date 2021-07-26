package ru.job4j.cache;

import java.io.File;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;

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
        try {
            String line = Files.readString(Path.of(cachingDir + File.separator + key));
            this.cache.put(key, new SoftReference<>(line));
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}