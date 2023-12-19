import 'package:flutter/services.dart';

import 'action_call_state_platform_interface.dart';

class ActionCallState {
  Future<String?> getPlatformVersion() {
    return ActionCallStatePlatform.instance.getPlatformVersion();
  }

  static const EventChannel _eventChannel = EventChannel('com.jojo.actionCallState');

  static Stream<String> get stream {
    return _eventChannel.receiveBroadcastStream().map((dynamic event) => "$event");
  }
}
