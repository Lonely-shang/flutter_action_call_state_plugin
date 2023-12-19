package com.jojo.action_call_state

import androidx.annotation.NonNull
import com.jojo.action_call_state.handler.ActionCallStateHandler

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** ActionCallStatePlugin */
class ActionCallStatePlugin: FlutterPlugin, MethodCallHandler {

  private lateinit var actionCallStateHandler: ActionCallStateHandler
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    actionCallStateHandler = ActionCallStateHandler(flutterPluginBinding)
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "action_call_state")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    actionCallStateHandler.dispose()
    channel.setMethodCallHandler(null)
  }
}
