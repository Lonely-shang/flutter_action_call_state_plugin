package com.jojo.action_call_state.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.jojo.action_call_state.utils.Constants

open class ActionCallStateBroadcast : BroadcastReceiver() {

    var actionCallState: String? = null
    var phoneNumber: String? = null
    var slotIndex: Number? = null
    var reocrdName: String? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            val action: String? = intent?.action
            if (action == Constants.ANDROID_BROADCAST_ACTION) {
                handlerAndroidCallState(intent)
            } else {
                handlerYunKeCallState(intent, action)
            }
        }catch (e: Exception) {
           e.printStackTrace()
        }
    }

    // 来电 YUNKE_INCOMING_CAL -> YUNKE_CALL_REJECT -> YUNKE_CALL_DISCONNECTED
    // 来电  YUNKE_INCOMING_CAL ->YUNKE_CALL_CONNECTED -> Constants.YUNKE_CALL_RECORD -> YUNKE_CALL_DISCONNECTED
    //

    private fun handlerYunKeCallState(intent: Intent?, action: String?) {
        try {
            actionCallState = when(action) {
                Constants.YUNKE_INCOMING_CALL -> ""
                Constants.YUNKE_OUTGOING_CALL -> ""
                Constants.YUNKE_CALL_DISCONNECTED -> ""
                Constants.YUNKE_CALL_REJECT -> ""
                Constants.YUNKE_CALL_CONNECTED -> ""
                Constants.YUNKE_CALL_RECORD -> ""
                else -> ""
            }
            phoneNumber = intent?.getStringExtra("number")
            slotIndex = intent?.getIntExtra("slotIndex", -1)
            println()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun handlerAndroidCallState(intent: Intent?) {
       try {
           val callState: String? = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
            actionCallState = when( callState ) {
                TelephonyManager.EXTRA_STATE_RINGING -> ""
                TelephonyManager.EXTRA_STATE_OFFHOOK -> ""
                TelephonyManager.EXTRA_STATE_IDLE -> ""
                else -> ""
            }
            phoneNumber = intent?.getStringExtra("income_number")
       } catch (e: Exception) {
           e.printStackTrace()
       }
    }

}