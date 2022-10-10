package com.algaworks.example.spring.cloud.stream.scheduler.domain.model;

import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.valueobject.HealthCheckConfig;
import lombok.*;
import org.apache.commons.lang3.Validate;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class HealthCheckTask {
    private UUID id;
    private App app;
    private HealthCheckConfig checkConfig;
    private OffsetDateTime createdAt;
    private UUID scheduleId;

    public HealthCheckTask(UUID id, App app, HealthCheckConfig checkConfig, OffsetDateTime createdAt, UUID scheduleId) {
        Validate.notNull(id);
        Validate.notNull(app);
        Validate.notNull(checkConfig);
        Validate.notNull(createdAt);
        Validate.notNull(scheduleId);
        this.id = id;
        this.app = app;
        this.checkConfig = checkConfig;
        this.createdAt = createdAt;
        this.scheduleId = scheduleId;
    }

    public static HealthCheckTask of(Schedule schedule) {
        Validate.notNull(schedule);
        Validate.notNull(schedule.getCheckConfig());
        return HealthCheckTask.builder()
                .id(UUID.randomUUID())
                .scheduleId(schedule.getId())
                .app(schedule.getApp())
                .checkConfig(schedule.getCheckConfig())
                .createdAt(OffsetDateTime.now())
                .build();
    }
}
