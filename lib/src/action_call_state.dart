import 'package:action_call_state/src/data/action_call_data.dart';
import 'package:action_call_state/src/interface/action_call_interface.dart';

class ActionCallState {
  static Stream<ActionCallData> get callStateStream => ActionCallInterface.instance.stream();
}
