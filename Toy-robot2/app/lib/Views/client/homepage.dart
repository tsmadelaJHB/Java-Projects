import 'package:flutter/material.dart';
import 'package:groupproject/Views/client/pixel.dart';

import '../../button.dart';
import 'button2.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int numberOfSquares = 140;
  int playerPosition = 0;
  int bombPosition = -1;
  List<int> barriers = [
    11,
    13,
    15,
    17,
    18,
    31,
    33,
    35,
    37,
    38,
    51,
    53,
    55,
    57,
    58,
    71,
    73,
    75,
    77,
    78,
    91,
    93,
    95,
    97,
    98,
    111,
    113,
    115,
    117,
    118
  ];

  void moveUp() {
    setState(() {
      if (playerPosition - 10 >= 0 && !barriers.contains(playerPosition - 10)) {
        playerPosition -= 10;
      }
    });
  }

  void moveLeft() {
    setState(() {
      if (!(playerPosition % 10 == 0) &&
          (!barriers.contains(playerPosition - 1))) {
        playerPosition -= 1;
      }
    });
  }

  void moveRight() {
    setState(() {
      if (!(playerPosition % 10 == 9) &&
          !barriers.contains(playerPosition + 1)) {
        playerPosition += 1;
      }
      print("move right");
    });
  }

  void moveDown() {
    setState(() {
      if (playerPosition + 10 < numberOfSquares &&
          !barriers.contains(playerPosition + 10)) {
        playerPosition += 10;
      }
    });
  }

  void placeBomb() {
    setState(() {
      bombPosition = playerPosition;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[800],
      body: Column(
        children: [
          Expanded(
            flex: 2,
            child: Container(
              child: GridView.builder(
                  physics: NeverScrollableScrollPhysics(),
                  itemCount: numberOfSquares,
                  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: 10),
                  itemBuilder: (BuildContext context, int index) {
                    if (bombPosition == index) {
                      return MyPixel(
                        color: Colors.red,
                      );
                    } else if (playerPosition == index) {
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
                        Mybutton(
                            color: Colors.white,
                            child: const Icon(Icons.change_circle_outlined,
                                size: 50, color: Colors.cyanAccent),
                            text: "shield",
                            name: "jonas"),
                        Mybutton(
                            function: moveUp,
                            color: Colors.white,
                            child: const Icon(Icons.arrow_drop_up, size: 70),
                            text: "forward",
                            name: "jonas"),
                        Mybutton(
                            color: Colors.white,
                            child: const Icon(Icons.build_circle_outlined,
                                size: 50, color: Colors.yellow),
                            text: "repair",
                            name: "jonas")
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
                            name: "jonas"),
                        Mybutton(
                            function: moveLeft,
                            color: Colors.white,
                            child: const Icon(
                              Icons.arrow_left,
                              size: 70,
                            ),
                            text: "left",
                            name: "jonas"),
                        Mybutton(
                            color: Colors.white,
                            child: const Icon(Icons.control_camera_outlined,
                                size: 50, color: Colors.red),
                            text: "fire",
                            name: "jonas"),
                        Mybutton(
                            function: moveRight,
                            color: Colors.white,
                            child: const Icon(
                              Icons.arrow_right,
                              size: 70,
                            ),
                            text: "right",
                            name: "jonas"),
                        Mybutton(
                            color: Colors.white,
                            child: const Icon(Icons.memory_rounded, size: 50, color: Colors.pinkAccent,),
                            text: "State",
                            name: "jonas")
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
                            name: "jonas"),
                        Mybutton(
                            function: moveDown,
                            color: Colors.white,
                            child: const Icon(
                              Icons.arrow_drop_down,
                              size: 70,
                            ),
                            text: "back",
                            name: "jonas"),
                        Mybutton(
                            color: Colors.white,
                            child: const Icon(Icons.emoji_flags_outlined,
                                size: 50, color: Colors.deepPurpleAccent),
                            text: "close",
                            name: "jonas")
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
