package com.jojo.action_call_state.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager

open class ActionCallStateBroadcast : BroadcastReceiver() {

    var actionCallState: String? = null
    var phoneNumber: String? = null
    var slotIndex: Number? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        try {

            var action: String? = intent?.action
            intent?.getStringExtra(TelephonyManager.EXTRA_STATE)

        }catch (e: Exception) {
           e.printStackTrace()
        }
    }

}