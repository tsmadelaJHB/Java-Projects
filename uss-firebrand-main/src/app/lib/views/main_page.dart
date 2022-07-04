import 'dart:convert';

import 'package:client/models/connection.dart';
import 'package:client/views/launch_page.dart';
import 'package:client/views/list_worlds.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'package:http/http.dart' as http;

// Enum options
var variable1 = 1;

class Connection extends StatefulWidget {
  const Connection({Key? key}) : super(key: key);

  @override
  _ConnectionState createState() => _ConnectionState();
}

class _ConnectionState extends State<Connection> {
  static final ipController = TextEditingController();
  static final portController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Center(child: Text("Toy Worlds")),
      ),
      body: Center(
        child: Column(
          children: [
            const Padding(
              padding: EdgeInsets.all(15),
              child: Text("Enter server information",
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 20,
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(25.0),
              child: TextField(
                controller: ipController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: "I.P address",
                ),
                keyboardType: TextInputType.number,
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(25.0),
              child: TextField(
                controller: portController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: "PORT number",
                ),
                keyboardType: TextInputType.number,
                inputFormatters: <TextInputFormatter>[
                  FilteringTextInputFormatter.digitsOnly
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(30),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  FloatingActionButton.extended(
                      onPressed: () {
                        createConnection();
                        Navigator.of(context).push(MaterialPageRoute(builder: (context)=> LaunchPage(ip: ipController.text,port: portController.text)));
                      }, label: const Text("Play")),
                  FloatingActionButton.extended(
                      backgroundColor: Colors.blueGrey,
                      onPressed: () {
                        Navigator.of(context).push(MaterialPageRoute(builder: (context)=> const ListWorlds()));
                      }, label: const Text("Admin")),
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
  Future<Connector> createConnection() async {
    final response = await http.get(
      Uri.parse('http://'+ipController.text+':7000/connect/' +
          ipController.text +
          '/' +
          portController.text),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
    );
    if (response.statusCode == 201) {

      // if successful in connecting go to specified page
      return Connector.fromJson(jsonDecode(response.body));
    } else {
      //throw a widget for when connection fails
      throw Exception('Failed to connect.');
    }
  }
}

