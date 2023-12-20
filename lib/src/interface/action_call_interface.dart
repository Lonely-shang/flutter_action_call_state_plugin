import 'package:action_call_state/src/data/action_call_data.dart';
import 'package:action_call_state/src/handler/action_call_state_handler.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

abstract class ActionCallInterface extends PlatformInterface {
  ActionCallInterface({required super.token});

  static final Object _token = Object();

  static ActionCallInterface _instance = ActionCallStateHandler();

  static ActionCallInterface get instance => _instance;

  static set instance(ActionCallInterface instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Stream<ActionCallData> stream() {
    throw UnimplementedError('stream() has not been implemented.');
  }
}
