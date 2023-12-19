package com.jojo.action_call_state.utils

class Constants {
    companion object {
        const val EVENT_CHANNEL: String  = "com.jojo.actionCallState"
        const val ANDROID_BROADCAST_ACTION: String = "android.intent.action.PHONE_STATE"
        const val YUNKE_OUTGOING_CALL: String = "com.yunke.ACTION_OUTGOING_CALL"
        const val YUNKE_INCOMING_CALL: String = "com.yunke.ACTION_INCOMING_CALL"
        const val YUNKE_CALL_DISCONNECTED: String = "com.yunke.ACTION_CALL_DISCONNECTED"
        const val YUNKE_CALL_REJECT : String = "com.yunke.ACTION_CALL_REJECT"
        const val YUNKE_CALL_CONNECTED : String = "com.yunke.ACTION_CALL_CONNECTED"
        const val YUNKE_CALL_RECORD : String = "com.yunke.ACTION_CALL_RECORD"
    }
}