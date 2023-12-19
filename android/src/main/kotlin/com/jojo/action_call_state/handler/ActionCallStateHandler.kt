package com.jojo.action_call_state.handler

import android.content.Context
import android.content.Intent
import androidx.annotation.NonNull
import com.jojo.action_call_state.receiver.ActionCallStateBroadcast
import com.jojo.action_call_state.utils.Constants
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.StreamHandler

class ActionCallStateHandler(@NonNull binding: FlutterPluginBinding) : StreamHandler {
    private var flutterPluginBinding: FlutterPluginBinding = binding
    private lateinit var actionCallStateBroadcast: ActionCallStateBroadcast
    private var actionCallEventChannel: EventChannel =
        EventChannel(flutterPluginBinding.binaryMessenger, Constants.EVENT_CHANNEL)

    init {
        actionCallEventChannel.setStreamHandler(this)
    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        actionCallStateBroadcast = object : ActionCallStateBroadcast() {
            override fun onReceive(context: Context?, intent: Intent?) {
                super.onReceive(context, intent)
                events?.success(
                    mapOf(
                        "systemMark" to systemMark,
                        "callStatus" to actionCallStatus,
                        "phoneNumber" to phoneNumber,
                        "slotIndex" to slotIndex
                    )
                )
            }
        }

    }

    override fun onCancel(arguments: Any?) {
        flutterPluginBinding.applicationContext.unregisterReceiver(actionCallStateBroadcast)
    }

    fun dispose () {
        actionCallEventChannel.setStreamHandler(null)
    }

}