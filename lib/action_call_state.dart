
import 'action_call_state_platform_interface.dart';

class ActionCallState {
  Future<String?> getPlatformVersion() {
    return ActionCallStatePlatform.instance.getPlatformVersion();
  }
}
