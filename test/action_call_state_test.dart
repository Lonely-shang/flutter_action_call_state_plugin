import 'package:flutter_test/flutter_test.dart';
import 'package:action_call_state/action_call_state.dart';
import 'package:action_call_state/action_call_state_platform_interface.dart';
import 'package:action_call_state/action_call_state_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockActionCallStatePlatform
    with MockPlatformInterfaceMixin
    implements ActionCallStatePlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final ActionCallStatePlatform initialPlatform = ActionCallStatePlatform.instance;

  test('$MethodChannelActionCallState is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelActionCallState>());
  });

  test('getPlatformVersion', () async {
    ActionCallState actionCallStatePlugin = ActionCallState();
    MockActionCallStatePlatform fakePlatform = MockActionCallStatePlatform();
    ActionCallStatePlatform.instance = fakePlatform;

    expect(await actionCallStatePlugin.getPlatformVersion(), '42');
  });
}
