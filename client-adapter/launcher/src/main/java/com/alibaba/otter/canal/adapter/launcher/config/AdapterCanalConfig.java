package com.alibaba.otter.canal.adapter.launcher.config;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.otter.canal.client.adapter.support.CanalClientConfig;

@Component
@ConfigurationProperties(prefix = "canal.conf")
public class AdapterCanalConfig extends CanalClientConfig {

    public static final Set<String> DESTINATIONS = new LinkedHashSet<>();

    @Override
    public void setCanalInstances(List<CanalInstance> canalInstances) {
        super.setCanalInstances(canalInstances);

        if (canalInstances != null) {
            synchronized (DESTINATIONS) {
                DESTINATIONS.clear();
                for (CanalInstance canalInstance : canalInstances) {
                    DESTINATIONS.add(canalInstance.getInstance());
                }
            }
        }
    }

    @Override
    public void setMqTopics(List<MQTopic> mqTopics) {
        super.setMqTopics(mqTopics);

        if (mqTopics != null) {
            synchronized (DESTINATIONS) {
                DESTINATIONS.clear();
                for (MQTopic mqTopic : mqTopics) {
                    DESTINATIONS.add(mqTopic.getTopic());
                }
            }
        }
    }
}
