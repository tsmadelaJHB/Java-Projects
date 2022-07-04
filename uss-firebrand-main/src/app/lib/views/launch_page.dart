import 'dart:convert';

import 'package:client/models/response.dart';
import 'package:client/views/main_page.dart';

import 'package:flutter/material.dart';


import 'package:http/http.dart' as http;

import 'game_page.dart';

class LaunchPage extends StatefulWidget {
  final String? ip;
  final String? port;
  const LaunchPage({Key? key, this.ip, this.port}) : super(key: key);

  @override
  State<LaunchPage> createState() => _LaunchPageState();
}

class _LaunchPageState extends State<LaunchPage> {
  final robotNameController = TextEditingController();
  var _value;


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Join The World"),
      ),
      body: Center(
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(25.0),
              child: TextField(
                controller: robotNameController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: "Name",
                ),
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                const Text("Robot Type: "),
                DropdownButton<String>(
                  hint: const Text("choose"),
                  value: _value,
                  onChanged: (String? newValue) {
                    setState(() {
                      _value = newValue!;
                    });
                  },
                  items: <String>['bomber', 'sniper', 'brute']
                      .map<DropdownMenuItem<String>>((String value) {
                    return DropdownMenuItem<String>(
                      value: value,
                      child: Text(value),
                    );
                  }).toList(),
                ),
              ],
            ),
            FloatingActionButton.extended(
                onPressed: () async {
                  Map data = {
                    "robot": robotNameController.text,
                    "command": "launch",
                    "arguments": [_value, "0", "0"]
                  };
                  String body = json.encode(data);
                  String ip = widget.ip!;
                  String port = widget.port!;
                  final response = await http.post(
                    Uri.parse("http://"+ip+":"+port+"/robot/launch"),
                    headers: <String, String>{
                      'Content-Type': 'application/json; charset=UTF-8',
                    },
                    body: body,
                  );
                  if (response.statusCode != 201) {
                    print("failed to launch");
                  } else {
                    Navigator.of(context).push(MaterialPageRoute(
                        builder: (context) => GamePage(name: robotNameController.text,ip: ip,port: port,)));
                  }
                },
                label: const Text("Launch"))
          ],
        ),
      ),
    );
  }

  Future<Response> attemptLaunch() async {
    Map data = {
      "robot": robotNameController.text,
      "command": "launch",
      "arguments": [_value, "0", "0"]
    };
    String body = json.encode(data);

    final response = await http.post(
      Uri.parse("http://192.168.101.113:7000/robot/launch"),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: body,
    );

    if (response.statusCode == 201) {
      print(response.body);
      // if successful in connecting go to specified page
      return Response.fromJson(jsonDecode(response.body));
    } else {
      //throw a widget for when connection fails
      return Response.fromJson(jsonDecode(response.body));
    }
  }
}
