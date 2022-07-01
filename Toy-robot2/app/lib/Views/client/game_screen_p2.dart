import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:groupproject/Models/get_data.dart';
import 'package:groupproject/button.dart';
import 'package:provider/provider.dart';
import 'package:provider/src/provider.dart';

class GamePage extends StatefulWidget {
  final name;
  GamePage(this.name);
  @override
  GameState createState() => GameState(name);
}

class GameState extends State<GamePage> {
  final myName;

  GameState(this.myName);

  @override
  Widget build(BuildContext context) {
    context.read<QuoteData>().fetchPosition;
    return Scaffold(
      backgroundColor: Colors.pink,
      body: Column(

        children: [

          Expanded(
            child: RefreshIndicator(
              onRefresh: () async {},
              child: Center(
                child: Consumer<QuoteData>(
                  builder: (context, value, child) {
                    return value.jsonresponsep.length == 0 && !value.error
                        ? ElevatedButton(
                            onPressed: () {},
                            child: const Text(
                                "NOT CONNECTED TO THE WORLD THE WORLD"),
                          )
                        : value.error
                            ? const Text(
                                'NOT CONNECTED TO THE WORLD THE WORLD',
                                textAlign: TextAlign.center,
                              )
                            : ListView.builder(
                                itemCount: value.jsonresponsep.length,
                                itemBuilder: (context, index) {
                                  return Padding(
                                    padding: const EdgeInsets.all(8.0),
                                    child: Card(
                                      elevation: 10,
                                      child: ListTile(
                                        trailing: Icon(Icons.person),
                                        // tileColor: Colors.deepPurple,
                                        hoverColor: Colors.grey,
                                        title: Text(
                                            value.jsonresponsep[index]["text"]),
                                      ),
                                    ),
                                  );
                                });
                  },
                ),
              ),
            ),
          ),
          Expanded(
              child: Container(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Mybutton(
                        color: Colors.white,
                        child: const Icon(Icons.change_circle_outlined,
                            size: 50, color: Colors.cyanAccent),
                        text: "reload",
                        name: myName),
                    Mybutton(

                        color: Colors.white,
                        child: const Icon(Icons.arrow_drop_up, size: 70),
                        text: "forward",
                        name: myName),
                    Mybutton(
                        color: Colors.white,
                        child: const Icon(Icons.build_circle_outlined,
                            size: 50, color: Colors.yellow),
                        text: "repair",
                        name: myName)
                  ],
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Mybutton(
                        color: Colors.white,
                        child: const Icon(Icons.visibility,
                            size: 50, color: Colors.lightBlueAccent),
                        text: "Look",
                        name: myName),
                    Mybutton(

                        color: Colors.white,
                        child: const Icon(
                          Icons.arrow_left,
                          size: 70,
                        ),
                        text: "left",
                        name: myName),
                    Mybutton(
                        color: Colors.white,
                        child: const Icon(Icons.control_camera_outlined,
                            size: 50, color: Colors.red),
                        text: "fire",
                        name: myName),
                    Mybutton(

                        color: Colors.white,
                        child: const Icon(
                          Icons.arrow_right,
                          size: 70,
                        ),
                        text: "right",
                        name: myName),
                    Mybutton(
                        color: Colors.white,
                        child: const Icon(
                          Icons.memory_rounded,
                          size: 50,
                          color: Colors.pinkAccent,
                        ),
                        text: "State",
                        name: myName)
                  ],
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Mybutton(
                        color: Colors.white,
                        child: const Icon(Icons.coronavirus_outlined,
                            size: 50, color: Colors.lightGreenAccent),
                        text: "mine",
                        name: myName),
                    Mybutton(

                        color: Colors.white,
                        child: const Icon(
                          Icons.arrow_drop_down,
                          size: 70,
                        ),
                        text: "back",
                        name: myName),
                    Mybutton(
                        color: Colors.white,
                        child: const Icon(Icons.emoji_flags_outlined,
                            size: 50, color: Colors.deepPurpleAccent),
                        text: "close",
                        name: myName)
                  ],
                )
              ],
            ),
          ))
        ],
      ),
    );
  }
}
