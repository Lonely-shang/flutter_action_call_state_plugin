import 'package:flutter/cupertino.dart';

enum SystemMarkType {
  android(text: "Android"),
  yunKe(text: "YunKe"),
  unknown(text: "");

  final String text;
  const SystemMarkType({required this.text});

  static SystemMarkType fromString(String? value) => SystemMarkType.values
      .firstWhere((element) => element.text == value, orElse: () => SystemMarkType.unknown);
}
