import 'package:client/models/world.dart';
import 'package:flutter/material.dart';

class AdminGameMode extends StatelessWidget {
  final World world;
  const AdminGameMode({Key? key, required this.world}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(world.name.toString()),
      ),
        body: Center(
          child: Column(
              children: const []
          )
    ));
  }
}
