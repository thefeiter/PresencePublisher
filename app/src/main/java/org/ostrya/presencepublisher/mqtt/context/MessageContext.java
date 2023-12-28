package org.ostrya.presencepublisher.mqtt.context;

import androidx.annotation.Nullable;

import org.ostrya.presencepublisher.mqtt.context.device.BatteryStatus;

import java.util.List;

public class MessageContext {
    public static final String UNKNOWN = "N/A";

    private final long nextAlarmclockTimestamp;
    private final BatteryStatus batteryStatus;
    private final List<String> conditionContents;
    private final String lastKnownLocation;
    private final long lastSuccessTimestamp;
    private final long currentTimestamp;
    private final long estimatedNextTimestamp;
    private final String deviceName;
    @Nullable private final String currentSsid;

    MessageContext(
            long nextAlarmclockTimestamp,
            BatteryStatus batteryStatus,
            List<String> conditionContents,
            String lastKnownLocation,
            long lastSuccessTimestamp,
            long currentTimestamp,
            long estimatedNextTimestamp,
            String deviceName,
            @Nullable String currentSsid) {
        this.nextAlarmclockTimestamp = nextAlarmclockTimestamp;
        this.batteryStatus = batteryStatus;
        this.conditionContents = conditionContents;
        this.lastKnownLocation = lastKnownLocation;
        this.lastSuccessTimestamp = lastSuccessTimestamp;
        this.currentTimestamp = currentTimestamp;
        this.estimatedNextTimestamp = estimatedNextTimestamp;
        this.deviceName = deviceName;
        this.currentSsid = currentSsid;
    }

    public long getNextAlarmclockTimestamp() {
        return nextAlarmclockTimestamp;
    }

    public BatteryStatus getBatteryStatus() {
        return batteryStatus;
    }

    public List<String> getConditionContents() {
        return conditionContents;
    }

    public String getLastKnownLocation() {
        return lastKnownLocation;
    }

    public long getLastSuccessTimestamp() {
        return lastSuccessTimestamp;
    }

    public long getCurrentTimestamp() {
        return currentTimestamp;
    }

    public long getEstimatedNextTimestamp() {
        return estimatedNextTimestamp;
    }

    public String getDeviceName() {
        return deviceName;
    }

    @Nullable
    public String getCurrentSsid() {
        return currentSsid;
    }
}
