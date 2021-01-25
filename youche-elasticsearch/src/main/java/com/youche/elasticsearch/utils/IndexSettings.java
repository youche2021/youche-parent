package com.youche.elasticsearch.utils;

import org.elasticsearch.common.settings.Settings;

public class IndexSettings {
    private static final String SETTINGS_INDEX_NUMBER_OF_SHARDS = "index.number_of_shards";
    private static final String SETTINGS_INDEX_NUMBER_OF_REPLICAS = "index.number_of_replicas";
    private static final int DEFAULT_INDEX_NUMBER_OF_SHARDS = 3;
    private static final int DEFAULT_INDEX_NUMBER_OF_REPLICAS = 2;

    private Settings.Builder settingsBuilder;

    private int getDefaultIndexNumberOfShards(int defaultShards) {
        return defaultShards < DEFAULT_INDEX_NUMBER_OF_SHARDS ? DEFAULT_INDEX_NUMBER_OF_SHARDS : defaultShards; // 默认 3 个分片
    }

    private int getDefaultIndexNumberOfReplicas(int defaultReplicas) {
        return defaultReplicas < DEFAULT_INDEX_NUMBER_OF_REPLICAS ? DEFAULT_INDEX_NUMBER_OF_REPLICAS : defaultReplicas; // 默认 2 个副本;
    }

    public IndexSettings() {
        this.settingsBuilder = Settings.builder();
    }

    public static IndexSettings instance() {
        return new IndexSettings();
    }

    public IndexSettings setGroupSettings(int shards, int replicas) {

        shards = getDefaultIndexNumberOfShards(shards);
        replicas = getDefaultIndexNumberOfReplicas(replicas);

        this.settingsBuilder
                .put(SETTINGS_INDEX_NUMBER_OF_SHARDS, shards) // 分片
                .put(SETTINGS_INDEX_NUMBER_OF_REPLICAS, replicas); // 副本
        return this;
    }

    public IndexSettings updateReplicas(int replicas) {
        replicas = getDefaultIndexNumberOfReplicas(replicas);
        this.settingsBuilder
                .put(SETTINGS_INDEX_NUMBER_OF_REPLICAS, replicas); // 副本
        return this;
    }

    public Settings.Builder getSettingsBuilder() {
        return this.settingsBuilder;
    }
}
