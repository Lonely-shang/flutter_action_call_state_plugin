import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'action_call_state_method_channel.dart';

abstract class ActionCallStatePlatform extends PlatformInterface {
  /// Constructs a ActionCallStatePlatform.
  ActionCallStatePlatform() : super(token: _token);

  static final Object _token = Object();

  static ActionCallStatePlatform _instance = MethodChannelActionCallState();

  /// The default instance of [ActionCallStatePlatform] to use.
  ///
  /// Defaults to [MethodChannelActionCallState].
  static ActionCallStatePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [ActionCallStatePlatform] when
  /// they register themselves.
  static set instance(ActionCallStatePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
