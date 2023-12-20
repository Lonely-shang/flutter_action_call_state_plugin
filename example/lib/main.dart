import 'package:action_call_state/action_call_state.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  ActionCallStatus actionCallStatus = ActionCallStatus.nothing;

  @override
  void initState() {
    super.initState();
    ActionCallState.callStateStream.listen((ActionCallData event) {
      setState(() {
        actionCallStatus = event.callState;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: ${actionCallStatus.name}'),
        ),
      ),
    );
  }
}
