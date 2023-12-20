package com.jojo.action_call_state.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.jojo.action_call_state.utils.ActionCallStatus
import com.jojo.action_call_state.utils.Constants

@Suppress("DEPRECATION")
open class ActionCallStateBroadcast : BroadcastReceiver() {

    var systemMark: String? = null
    var actionCallStatus: ActionCallStatus = ActionCallStatus.NOTHING
    var phoneNumber: String? = null
    var slotIndex: Number? = null
    var reocrdName: String? = null


    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            when (val action: String? = intent?.action) {
                Constants.ANDROID_BROADCAST_ACTION -> handlerAndroidCallState(intent)
                Intent.ACTION_NEW_OUTGOING_CALL -> handlerAndroidOutGoingCallState(intent)
                else -> handlerYunKeCallState(intent, action)
            }
        }catch (e: Exception) {
           e.printStackTrace()
        }
    }

     private fun handlerAndroidOutGoingCallState(intent: Intent?) {
        systemMark = Constants.SYSTEM_MARK_ANDROID
        actionCallStatus = ActionCallStatus.CALL_OUTGOING
        phoneNumber = intent?.getStringExtra(Constants.EXTRA_OUTGOING_NUMBER)
        println()
    }

     private fun handlerYunKeCallState(intent: Intent?, action: String?) {
        try {
            actionCallStatus = when(action) {
                Constants.YUNKE_INCOMING_CALL -> ActionCallStatus.CALL_INCOMING
                Constants.YUNKE_OUTGOING_CALL -> ActionCallStatus.CALL_OUTGOING
                Constants.YUNKE_CALL_DISCONNECTED -> ActionCallStatus.CALL_DISCONNECTED
                Constants.YUNKE_CALL_REJECT -> ActionCallStatus.CALL_REJECT
                Constants.YUNKE_CALL_CONNECTED -> ActionCallStatus.CALL_CONNECTED
                else -> ActionCallStatus.NOTHING
            }
            systemMark = Constants.SYSTEN_MARK_YUNKE
            phoneNumber = intent?.getStringExtra(Constants.EXTRA_YUNKE_NUMBER)
            slotIndex = intent?.getIntExtra(Constants.EXTRA_YUNKE_SLOTINDEX, -1)
            println()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

     private fun handlerAndroidCallState(intent: Intent?) {
       try {
           val callState: String? = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
            actionCallStatus = when( callState ) {
                TelephonyManager.EXTRA_STATE_RINGING -> ActionCallStatus.CALL_INCOMING
                TelephonyManager.EXTRA_STATE_OFFHOOK -> ActionCallStatus.CALL_CONNECTED
                TelephonyManager.EXTRA_STATE_IDLE -> ActionCallStatus.CALL_DISCONNECTED
                else -> ActionCallStatus.NOTHING
            }
            systemMark = Constants.SYSTEM_MARK_ANDROID
            phoneNumber = intent?.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            println()
       } catch (e: Exception) {
           e.printStackTrace()
       }
    }

}