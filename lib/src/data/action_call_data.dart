import 'package:action_call_state/src/utils/action_call_status.dart';
import 'package:action_call_state/src/utils/system_mark_type.dart';

class ActionCallData {
  SystemMarkType systemMarkType;
  ActionCallStatus callState;
  String? phoneNumber;
  int? slotIndex;

  ActionCallData({
    this.slotIndex,
    this.phoneNumber,
    required this.callState,
    required this.systemMarkType,
  });

  @override
  String toString() {
    return "systemMarkType: $systemMarkType \n"
        "callState: $callState \n"
        "phoneNumber: $phoneNumber \n"
        "slotIndex: $slotIndex";
  }
}
