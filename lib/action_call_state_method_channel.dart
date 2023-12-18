import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'action_call_state_platform_interface.dart';

/// An implementation of [ActionCallStatePlatform] that uses method channels.
class MethodChannelActionCallState extends ActionCallStatePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('action_call_state');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
