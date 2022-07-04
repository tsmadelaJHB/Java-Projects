import 'package:client/controllers/worlds.dart';
import 'package:client/views/main_page.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MultiProvider(providers: [
    ChangeNotifierProvider(
        create: (_) => Worlds()),
  ],
    child: const MyApp(),));
}

class MyApp extends StatelessWidget{
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      title: "Robot Worlds",
      home: Connection(),
    );
  }
}