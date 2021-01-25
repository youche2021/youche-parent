package com.youche.hbase.config;

import com.youche.hbase.template.HbaseTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(HbaseProperties.class)
@ConditionalOnClass(HbaseProperties.class)
public class HbaseAutoConfiguration {

    private static final String HBASE_QUORUM = "hbase.zookeeper.quorum";
    private static final String HBASE_ROOTDIR = "hbase.rootdir";
    private static final String HBASE_ZNODE_PARENT = "zookeeper.znode.parent";

    private HbaseProperties hbaseProperties;

    @Autowired
    public void setHbaseProperties(HbaseProperties hbaseProperties) {
        this.hbaseProperties = hbaseProperties;
    }

    @Bean
    @ConditionalOnMissingBean(HbaseTemplate.class)
    public HbaseTemplate hbaseTemplate() {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set(HBASE_QUORUM, this.hbaseProperties.getQuorum());

        String rootDir = this.hbaseProperties.getRootDir();
        if (StringUtils.isNotEmpty(rootDir)) {
            configuration.set(HBASE_ROOTDIR, rootDir);
        }

        String nodeParent = this.hbaseProperties.getNodeParent();
        if (StringUtils.isNotEmpty(nodeParent)) {
            configuration.set(HBASE_ZNODE_PARENT, nodeParent);
        }

        boolean prepareConnection = this.hbaseProperties.isPrepareConnection();

        HbaseTemplate hbaseTemplate = new HbaseTemplate(configuration);

        if (prepareConnection) {
            hbaseTemplate.initConnection();
        }
        return hbaseTemplate;
    }
}
