import 'package:client/models/world.dart';
import 'package:client/views/object.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'block_make.dart';

class CreateWorld extends StatefulWidget {

  const CreateWorld({Key? key}) : super(key: key);

  @override
  State<CreateWorld> createState() => _CreateWorldState();
}

class _CreateWorldState extends State<CreateWorld> {
  var _value;
  late World world;
  List<Object> objects = [];//{"name":"",position}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: const BottomAppBar(
        child: TextField(
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            labelText: "Name",
          ),
        ),
      ),
        backgroundColor: Colors.white24,
        appBar: AppBar(
          backgroundColor: Colors.grey,
        ),
     body: GridView.count(
          padding: const EdgeInsets.all(15),
          crossAxisCount: 5,
          children: List.generate(25, (index) {
            return BlockMake(objects, index);
          })),


    floatingActionButton: FloatingActionButton.extended(
        onPressed: () {

        },
        label: const Text("Save World"))
    );
  }
}

