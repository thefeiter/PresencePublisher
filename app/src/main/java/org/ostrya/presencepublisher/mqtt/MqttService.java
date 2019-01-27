package org.ostrya.presencepublisher.mqtt;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.Charset;

import static org.ostrya.presencepublisher.ui.ConnectionFragment.*;
import static org.ostrya.presencepublisher.ui.ScheduleFragment.LAST_PING;

public class MqttService {
    private static final String TAG = MqttService.class.getSimpleName();

    private final AndroidSslSocketFactoryFactory factory;
    private final SharedPreferences sharedPreferences;

    public MqttService(Context context, SharedPreferences sharedPreferences) {
        this.factory = new AndroidSslSocketFactoryFactory(context);
        this.sharedPreferences = sharedPreferences;
    }

    public void sendPing() {
        try {
            doSendPing();
            sharedPreferences.edit().putLong(LAST_PING, System.currentTimeMillis()).apply();
        } catch (MqttException e) {
            Log.w(TAG, "Error while sending ping", e);
        }
    }

    public void doSendPing() throws MqttException {
        Log.d(TAG, "Try pinging server");
        String topic = sharedPreferences.getString(TOPIC, "topic");
        boolean tls = sharedPreferences.getBoolean(TLS, false);
        String clientCertAlias = sharedPreferences.getString(CLIENT_CERT, null);

        MqttClient mqttClient = new MqttClient(getMqttUrl(tls), Settings.Secure.ANDROID_ID, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setConnectionTimeout(5);
        if (tls) {
            options.setSocketFactory(factory.getSslSocketFactory(clientCertAlias));
        }
        mqttClient.connect(options);
        mqttClient.publish(topic, "online".getBytes(Charset.forName("UTF-8")), 0, false);
        mqttClient.disconnect(5);
        mqttClient.close(true);
        Log.d(TAG, "Ping successful");
    }

    private String getMqttUrl(boolean tls) {
        String host = sharedPreferences.getString(HOST, "localhost");
        String port = sharedPreferences.getString(PORT, null);
        String protocolPrefix = tls ? "ssl://" : "tcp://";
        String portAppendix = port == null ? "" : ":" + port;
        return protocolPrefix + host + portAppendix;
    }
}