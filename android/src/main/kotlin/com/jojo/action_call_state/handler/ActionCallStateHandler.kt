package com.jojo.action_call_state.handler

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.telephony.TelephonyManager
import androidx.annotation.NonNull
import com.jojo.action_call_state.receiver.ActionCallStateBroadcast
import com.jojo.action_call_state.utils.Constants
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink
import org.json.JSONObject

@Suppress("DEPRECATION")
class ActionCallStateHandler(@NonNull binding: FlutterPluginBinding){
    private var actionCallEventChannel: EventChannel =
        EventChannel(binding.binaryMessenger, Constants.EVENT_CHANNEL)

    init {
        actionCallEventChannel.setStreamHandler(object : EventChannel.StreamHandler {
            private var systemType: String? = null
            private lateinit var receiver: ActionCallStateBroadcast
            override fun onListen(arguments: Any?, events: EventSink?) {
                val intentFilterButton = IntentFilter()
                intentFilterButton.addAction("com.yunke.ACTION_INCOMING_CALL")
                intentFilterButton.addAction("com.yunke.ACTION_OUTGOING_CALL")
                intentFilterButton.addAction("com.yunke.ACTION_CALL_DISCONNECTED")
                intentFilterButton.addAction("com.yunke.ACTION_CALL_REJECT")
                intentFilterButton.addAction("com.yunke.ACTION_CALL_CONNECTED")
                intentFilterButton.addAction(Intent.ACTION_NEW_OUTGOING_CALL)
                intentFilterButton.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
                receiver = object: ActionCallStateBroadcast() {
                    override fun onReceive(context: Context?, intent: Intent?) {
                        super.onReceive(context, intent)
                        if (systemMark == Constants.SYSTEN_MARK_YUNKE) {
                            systemType = Constants.SYSTEN_MARK_YUNKE
                        }
                        events?.success(
                            JSONObject(
                                mapOf(
                                    "systemMark" to (systemType ?: systemMark),
                                    "callStatus" to actionCallStatus.ordinal,
                                    "phoneNumber" to phoneNumber,
                                    "slotIndex" to slotIndex
                                )
                            ).toString()
                        )
                    }
                }

                binding.applicationContext.registerReceiver(
                    receiver,
                    intentFilterButton
                )
            }

            override fun onCancel(arguments: Any?) {
                binding.applicationContext.unregisterReceiver(receiver)
            }

        })
    }

    fun dispose () {
        actionCallEventChannel.setStreamHandler(null)
    }

}