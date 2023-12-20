enum ActionCallStatus {
  callIncoming,
  callOutgoing,
  callReject,
  callConnected,
  callDisconnected,
  callRecord,

  nothing;

  static ActionCallStatus fromInt(int value) => ActionCallStatus.values.firstWhere(
        (element) => element.index == value,
        orElse: () => ActionCallStatus.nothing,
      );
}
