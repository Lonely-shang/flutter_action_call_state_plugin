import 'dart:convert';

import 'package:action_call_state/src/data/action_call_data.dart';
import 'package:action_call_state/src/interface/action_call_interface.dart';
import 'package:action_call_state/src/utils/action_call_status.dart';
import 'package:action_call_state/src/utils/constants.dart';
import 'package:action_call_state/src/utils/system_mark_type.dart';
import 'package:flutter/services.dart';

class ActionCallStateHandler implements ActionCallInterface {
  final actionCallEventChannel = const EventChannel(Constants.eventChannel);

  @override
  Stream<ActionCallData> stream() {
    return actionCallEventChannel.receiveBroadcastStream().distinct().map((event) {
      Map<String, dynamic> map = jsonDecode(event);
      return ActionCallData(
        callState: ActionCallStatus.fromInt(map["callStatus"]),
        systemMarkType: SystemMarkType.fromString(map["systemMark"]),
        phoneNumber: map["phoneNumber"],
        slotIndex: map["slotIndex"],
      );
    });
  }
}
