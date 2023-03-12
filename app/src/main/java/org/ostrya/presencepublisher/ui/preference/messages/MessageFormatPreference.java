package org.ostrya.presencepublisher.ui.preference.messages;

import android.content.Context;

import org.ostrya.presencepublisher.R;
import org.ostrya.presencepublisher.message.MessageFormat;
import org.ostrya.presencepublisher.ui.preference.common.ListPreferenceBase;

public class MessageFormatPreference extends ListPreferenceBase {
    public static final String MESSAGE_FORMAT_SETTING = "messageFormatSetting";

    public MessageFormatPreference(Context context) {
        super(
                context,
                MESSAGE_FORMAT_SETTING,
                R.string.message_format_setting_title,
                MessageFormat.PLAINTEXT.name(),
                R.string.message_format_setting_summary);
        setEntries(MessageFormat.settingDescriptions());
        setEntryValues(MessageFormat.settingValues());
    }
}
