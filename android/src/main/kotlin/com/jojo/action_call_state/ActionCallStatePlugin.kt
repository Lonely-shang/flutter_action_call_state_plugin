package com.jojo.action_call_state

import com.jojo.action_call_state.handler.ActionCallStateHandler

import io.flutter.embedding.engine.plugins.FlutterPlugin

/** ActionCallStatePlugin */
class ActionCallStatePlugin: FlutterPlugin {

  private lateinit var actionCallStateHandler: ActionCallStateHandler

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    actionCallStateHandler = ActionCallStateHandler(flutterPluginBinding)
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    actionCallStateHandler.dispose()
  }
}
