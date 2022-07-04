import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';

import 'package:http/http.dart' as http;

class GamePage extends StatefulWidget {
  final String? name;
  final String? ip;
  final String? port;
  const GamePage({Key? key, this.name, this.ip, this.port}) : super(key: key);

  @override
  State<GamePage> createState() => _GamePageState();
}

class _GamePageState extends State<GamePage> {
  var _steps = 1;
  @override
  Widget build(BuildContext context) {

    return Scaffold(
      backgroundColor: Colors.white24,
      appBar: AppBar(
        backgroundColor: Colors.grey,
      ),
      body: Column(
        children: [
          GridView.count(
              padding: const EdgeInsets.all(15),
              shrinkWrap: true,
              crossAxisCount: 5,
              children: List.generate(25, (index) {
                return Container(
                  decoration: BoxDecoration(
                    border: Border.all(color: Colors.white12),
                    color: Colors.blueGrey,
                    image: index == 20 ? const DecorationImage(
                        image: AssetImage("assets/robot.png"))
                        : index == 10? const DecorationImage(
                        image: AssetImage("assets/obstacle.png"))
                        : null,
                  ),
                  width: 10,
                  height: 10,
                );
              })),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              children: [
                Row(
                  children: [
                    const SizedBox(
                      width: 50,
                      height: 10,
                    ),
                    IconButton(
                        onPressed: () async {
                          Map data = {
                          "robot": widget.name,
                          "command": "forward",
                          "arguments": _steps};
                          String body = json.encode(data);
                          String ip = widget.ip!;
                          String port = widget.port!;
                          final response = await http.post(
                            Uri.parse("http://"+ip+":"+port+"/robot/commands"),
                            headers: <String, String>{
                              'Content-Type': 'application/json; charset=UTF-8',
                            },
                            body: body,
                          );
                        },
                        color: Colors.lightGreenAccent,
                        splashColor: Colors.lightBlueAccent,
                        icon: const Icon(Icons.keyboard_arrow_up_outlined)),
                  ],
                ),
                Row(
                  mainAxisSize: MainAxisSize.max,
                  children: [
                    IconButton(
                        onPressed: () async {
                          Map data = {
                            "robot": widget.name,
                            "command": "turn",
                            "arguments": "left"};
                          String body = json.encode(data);
                          String ip = widget.ip!;
                          String port = widget.port!;
                          final response = await http.post(
                            Uri.parse("http://"+ip+":"+port+"/robot/commands"),
                            headers: <String, String>{
                              'Content-Type': 'application/json; charset=UTF-8',
                            },
                            body: body,
                          );
                        },
                        color: Colors.lightGreenAccent,
                        splashColor: Colors.lightBlueAccent,
                        icon: const Icon(Icons.keyboard_arrow_left_outlined)),
                    const SizedBox(
                      width: 50,
                      height: 10,
                    ),
                    IconButton(
                        onPressed: () async {
                          Map data = {
                            "robot": widget.name,
                            "command": "turn",
                            "arguments": "right"};
                          String body = json.encode(data);
                          String ip = widget.ip!;
                          String port = widget.port!;
                          final response = await http.post(
                            Uri.parse("http://"+ip+":"+port+"/robot/commands"),
                            headers: <String, String>{
                              'Content-Type': 'application/json; charset=UTF-8',
                            },
                            body: body,
                          );
                        },
                        color: Colors.lightGreenAccent,
                        splashColor: Colors.lightBlueAccent,
                        icon: const Icon(Icons.keyboard_arrow_right_outlined)),
                    const SizedBox(
                      width: 50,
                      height: 10,
                    ),
                    IconButton(
                        onPressed: () {

                        },
                        splashColor: Colors.redAccent,
                        color: Colors.lightGreenAccent,
                        icon: const Icon(Icons.air_sharp)),
                    const SizedBox(
                      width: 50,
                      height: 10,
                    ),
                    IconButton(
                        onPressed: () async {
                          Map data = {
                            "robot": widget.name,
                            "command": "mine",
                            "arguments": ""};
                          String body = json.encode(data);
                          String ip = widget.ip!;
                          String port = widget.port!;
                          final response = await http.post(
                            Uri.parse("http://"+ip+":"+port+"/robot/commands"),
                            headers: <String, String>{
                              'Content-Type': 'application/json; charset=UTF-8',
                            },
                            body: body,
                          );
                        },
                        splashColor: Colors.redAccent,
                        color: Colors.lightGreenAccent,
                        icon: const Icon(Icons.album))
                  ],
                ),
                Row(
                  children: [
                    const SizedBox(
                      width: 50,
                      height: 10,
                    ),
                    IconButton(
                        onPressed: () async {
                          Map data = {
                            "robot": widget.name,
                            "command": "back",
                            "arguments": _steps};
                          String body = json.encode(data);
                          String ip = widget.ip!;
                          String port = widget.port!;
                          final response = await http.post(
                            Uri.parse("http://"+ip+":"+port+"/robot/commands"),
                            headers: <String, String>{
                              'Content-Type': 'application/json; charset=UTF-8',
                            },
                            body: body,
                          );
                        },
                        color: Colors.lightGreenAccent,
                        splashColor: Colors.lightBlueAccent,
                        icon: const Icon(Icons.keyboard_arrow_down_outlined)),
                  ],
                ),
              ],
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              const Text("Steps to move"),
              Padding(
                padding: const EdgeInsets.all(20.0),
                child: DropdownButton<int>(
                  value: _steps,
                  onChanged: (newValue) {
                    setState(() {
                      _steps = newValue!;
                    });
                  },
                  items: <int>[1,2,3,4,5]
                      .map<DropdownMenuItem<int>>((int value) {
                    return DropdownMenuItem<int>(
                      value: value,
                      child: Text(value.toString()),
                    );
                  }).toList(),
                ),
              ),
            ],
            ),
        ],
      ),
    );
  }
}