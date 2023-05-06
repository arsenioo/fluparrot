import 'package:pigeon/pigeon.dart';

@HostApi()
abstract class IBPSdkApi {
  void connect(int mode);
}

@FlutterApi()
abstract class IBPSdkListener {
  void onConnect();
  void onConnectProgress(int progressCode);
  void onConnectFailure(int errorCode);
  void onDisconnect();
}
