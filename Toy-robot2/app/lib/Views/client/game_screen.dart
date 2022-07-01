import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:groupproject/Models/post_data.dart';
import 'package:groupproject/Views/client/pixel.dart';
import 'package:groupproject/button.dart';
import './button2.dart';
import 'client.dart';

class GamePageG extends StatefulWidget {
  final name;
  final worldsize;
  int playerPosition;
  GamePageG(this.name, this.worldsize, this.playerPosition);
  @override
  GameState createState() => GameState(name, worldsize, playerPosition);
}

class GameState extends State<GamePageG> {
  int numberOfSquares = 130;
  int playerPosition;
  int turning = 0;
  final myName;
  final mysize;
  GameState(this.myName, this.mysize, this.playerPosition);
  int bombPosition = -1;

  List<int> barriers = [2, 13, 15, 17];

  void moveForward() {
    setState(() {
      if (turning == 0) {
        if (playerPosition - sqrt(mysize).round() >= 0 &&
            !barriers.contains(playerPosition - sqrt(mysize).round())) {
          playerPosition -= sqrt(mysize).round();
          if(playerPosition>=mysize){
            playerPosition += sqrt(mysize).round();
            print("Out of Bound");
          }
          createQuotes(myName, "forward");
          print("moved forward by 1");
        }
      } else if (turning == 90 || turning == -270) {
        if (!(playerPosition % 10 == 9) &&
            !barriers.contains(playerPosition + 1)) {
          playerPosition += 1;
          if(playerPosition>=mysize){
            playerPosition -= 1;
            print("Out of Bound");
          }
          createQuotes(myName, "forward");
          print("moved forward by 1");
        }
      } else if (turning == 180 || turning == -180) {
        if (playerPosition + sqrt(mysize).round() < numberOfSquares &&
            !barriers.contains(playerPosition + sqrt(mysize).round())) {
          playerPosition += sqrt(mysize).round();
          if(playerPosition>=mysize){
            playerPosition -= sqrt(mysize).round();
            print("Out of Bound");
          }
          createQuotes(myName, "forward");
          print("moved forward by 1");
        }
      } else if (turning == 270 || turning == -90) {
        if (!(playerPosition % 10 == 0) &&
            (!barriers.contains(playerPosition - 1))) {
          playerPosition -= 1;
          if(playerPosition>=mysize){
            playerPosition += 1;
            print("Out of Bound");
          }
          createQuotes(myName, "forward");
          print("moved forward by 1");
        }
      }
    });
  }

  void moveLeft() {
    setState(() {
      // if (!(playerPosition % 10 == 0) &&
      //     (!barriers.contains(playerPosition - 1))) {
      //   playerPosition -= 1;
      //
      // }
      turning -= 90;
      if (turning == -360) {
        turning = 0;
      }
      createQuotes(myName, "left");
      print("Turned left");
    });
  }

  void moveRight() {
    setState(() {
      // if (!(playerPosition % 10 == 9) &&
      //     !barriers.contains(playerPosition + 1)) {
      //   playerPosition += 1;
      //
      // }
      turning += 90;
      if (turning == 360) {
        turning = 0;
      }
      createQuotes(myName, "right");
      print("Turned right");
    });
  }

  void moveBack() {
    setState(() {
      if (turning == 0) {
        if (playerPosition + sqrt(mysize).round() < numberOfSquares &&
            !barriers.contains(playerPosition + sqrt(mysize).round())) {
          playerPosition += sqrt(mysize).round();
          if(playerPosition>=mysize){
            playerPosition -= sqrt(mysize).round();
            print("Out of Bound");
          }
          createQuotes(myName, "back");
          print("moved back by 1");
        }
      } else if (turning == 90 || turning == -270) {
        if (!(playerPosition % 10 == 0) &&
            (!barriers.contains(playerPosition - 1))) {
          playerPosition -= 1;
          if(playerPosition>=mysize){
            playerPosition += 1;
            print("Out of Bound");
          }
          createQuotes(myName, "back");
          print("moved back by 1");
        }
      } else if (turning == 180 || turning == -180) {
        if (playerPosition - sqrt(mysize).round() >= 0 &&
            !barriers.contains(playerPosition - sqrt(mysize).round())) {
          playerPosition -= sqrt(mysize).round();
          if(playerPosition>=mysize){
            playerPosition += sqrt(mysize).round();
            print("Out of Bound");
          }
          createQuotes(myName, "back");
          print("moved back by 1");
        }
      } else if (turning == 270 || turning == -90) {
        if (!(playerPosition % 10 == 9) &&
            !barriers.contains(playerPosition + 1)) {
          playerPosition += 1;
          if(playerPosition>=mysize){
            playerPosition -= 1;
            print("Out of Bound");
          }
          createQuotes(myName, "back");
          print("moved back by 1");
        }
      }
      if(playerPosition==bombPosition){
        print("You are dead");
        showDialog<String>(
          context: context,
          builder: (BuildContext context) => AlertDialog(
            title: const Text('GAME OVER!!!'),
            content: const Text('YOU ARE DEAD'),
            actions: <Widget>[
              TextButton(
                onPressed: () {
                  createQuotes(myName, "close");
                  Navigator.of(context)
                      .push(MaterialPageRoute(builder: (context) => ClientPage()));
                },
                child: const Text('OK'),
              ),
            ],
          ),
        );
      }
    });
  }

  void placeBomb() {
    setState(() {
      bombPosition = playerPosition;
      moveForward();
      createQuotes(myName, "mine");
      showDialog<String>(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: const Text('MINE COMMAND'),
          content: const Text('PLANTING A MINE...'),
          actions: <Widget>[
            TextButton(
              onPressed: () => Navigator.pop(context, 'Cancel'),
              child: const Text('OK'),
            ),
          ],
        ),
      );
    });
  }
  void myrepair() {
    setState(() {
      createQuotes(myName, "repair");
      showDialog<String>(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: const Text('REPAIR COMMAND'),
          content: const Text('REPAIRING...'),
          actions: <Widget>[
            TextButton(
              onPressed: () => Navigator.pop(context, 'Cancel'),
              child: const Text('OK'),
            ),
          ],
        ),
      );
    });
  }
  void mylook() {
    setState(() {
      createQuotes(myName, "Look");
      showDialog<String>(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: const Text('LOOK COMMAND'),
          content: const Text('LOOKING...'),
          actions: <Widget>[
            TextButton(
              onPressed: () => Navigator.pop(context, 'Cancel'),
              child: const Text('OK'),
            ),
          ],
        ),
      );
    });
  }
  void myState() {
    setState(() {
      createQuotes(myName, "state");
      showDialog<String>(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: const Text('STATE COMMAND'),
          content: const Text('STATE...'),
          actions: <Widget>[
            TextButton(
              onPressed: () => Navigator.pop(context, 'Cancel'),
              child: const Text('OK'),
            ),
          ],
        ),
      );
    });
  }
  void myFire() {
    setState(() {
      createQuotes(myName, "fire");
      showDialog<String>(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: const Text('FIRE COMMAND'),
          content: const Text('FIRING...'),
          actions: <Widget>[
            TextButton(
              onPressed: () => Navigator.pop(context, 'Cancel'),
              child: const Text('OK'),
            ),
          ],
        ),
      );
    });
  }
  void myReload() {
    setState(() {
      createQuotes(myName, "reload");
      showDialog<String>(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: const Text('RELOAD COMMAND'),
          content: const Text('RELOADING...'),
          actions: <Widget>[
            TextButton(
              onPressed: () => Navigator.pop(context, 'Cancel'),
              child: const Text('OK'),
            ),
          ],
        ),
      );
    });
  }

  void exit() {
    showDialog<String>(
      context: context,
      builder: (BuildContext context) => AlertDialog(
        title: const Text('CONFIRM'),
        content: const Text('ARE YOU SURE YOU WANT TO QUIT?'),
        actions: <Widget>[
          TextButton(
            onPressed: () => Navigator.pop(context, 'Cancel'),
            child: const Text('NO'),
          ),
          TextButton(
            onPressed: () {
              createQuotes(myName, "close");
              Navigator.of(context)
                  .push(MaterialPageRoute(builder: (context) => ClientPage()));
            },
            child: const Text('YES'),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[900],
      body: Column(
        children: [
          Expanded(
            flex: 2,
            child: Container(
              child: GridView.builder(
                  // physics: NeverScrollableScrollPhysics(),
                  itemCount: mysize,
                  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: sqrt(mysize).round()),
                  itemBuilder: (BuildContext context, int index) {
                    if (bombPosition == index) {
                      return MyPixel(
                        color: Colors.red,
                      );
                    } else if (playerPosition == index && index < mysize + 1) {
                      print(index);
                      return MyPixel(
                        color: Colors.grey,
                        child: Image.asset('assets/images/bot.jpg'),
                      );
                    } else if (barriers.contains(index)) {
                      return MyPixel(
                        color: Colors.black,
                      );
                    } else {
                      return MyPixel(
                        color: Colors.grey,
                      );
                    }
                  }),
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
                    MybuttonB(
                      function: myReload,
                        color: Colors.white,
                        child: const Icon(Icons.change_circle_outlined,
                            size: 50, color: Colors.cyanAccent),
                        text: "reload",
                        name: myName),
                    MybuttonB(
                        function: moveForward,
                        color: Colors.white,
                        child: const Icon(Icons.arrow_drop_up, size: 70),
                        text: "forward",
                        name: myName),
                    MybuttonB(
                      function: myrepair,
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
                    MybuttonB(
                      function: mylook,
                        color: Colors.white,
                        child: const Icon(Icons.visibility,
                            size: 50, color: Colors.lightBlueAccent),
                        text: "Look",
                        name: myName),
                    MybuttonB(
                        function: moveLeft,
                        color: Colors.white,
                        child: const Icon(
                          Icons.arrow_left,
                          size: 70,
                        ),
                        text: "left",
                        name: myName),
                    MybuttonB(
                      function: myFire,
                        color: Colors.white,
                        child: const Icon(Icons.control_camera_outlined,
                            size: 50, color: Colors.red),
                        text: "fire",
                        name: myName),
                    MybuttonB(
                        function: moveRight,
                        color: Colors.white,
                        child: const Icon(
                          Icons.arrow_right,
                          size: 70,
                        ),
                        text: "right",
                        name: myName),
                    MybuttonB(
                      function: myState,
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
                    MybuttonB(
                        function: placeBomb,
                        color: Colors.white,
                        child: const Icon(Icons.coronavirus_outlined,
                            size: 50, color: Colors.lightGreenAccent),
                        text: "mine",
                        name: myName),
                    MybuttonB(
                        function: moveBack,
                        color: Colors.white,
                        child: const Icon(
                          Icons.arrow_drop_down,
                          size: 70,
                        ),
                        text: "back",
                        name: myName),
                    MybuttonB(
                        function: exit,
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
