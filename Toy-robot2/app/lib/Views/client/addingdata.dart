import 'dart:math';

import 'package:groupproject/Controller/initialize_quote.dart';
import 'package:groupproject/Models/post_data.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:groupproject/Views/client/getpostion.dart';
import 'game_screen.dart';
import 'game_screen_p2.dart';
import 'homepage.dart';

class SetUp extends StatelessWidget {
  final TextEditingController _controller = TextEditingController();
  final TextEditingController _author = TextEditingController();
  Future<Quote>? _futureQuote;
  var worldsize;

  SetUp(this.worldsize);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(4.0),
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        home: Scaffold(
          appBar: AppBar(
            title: const Text('LAUNCHING ROBOT'),
            centerTitle: true,
            backgroundColor: Colors.grey[900],
          ),
          body: Center(
            child: Container(
              decoration: const BoxDecoration(
                image: DecorationImage(
                  image: AssetImage("assets/images/tri.jpg"),
                  fit: BoxFit.cover,
                ),
              ),
              alignment: Alignment.center,
              padding: const EdgeInsets.all(8.0),
              child: (_futureQuote == null)
                  ? buildColumn(context)
                  : buildFutureBuilder(),
            ),
          ),
        ),
      ),
    );
  }

  Column buildColumn(BuildContext context) {

    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        TextField(
          style: const TextStyle(
              color: Colors.lightBlueAccent, fontWeight: FontWeight.bold),
          controller: _author,
          decoration: const InputDecoration(
            hintText: 'ENTER NAME',
            hintStyle:
                TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
            enabledBorder: UnderlineInputBorder(
              borderSide: BorderSide(color: Colors.white),
            ),
            focusedBorder: UnderlineInputBorder(
                borderSide: BorderSide(color: Colors.white)),
          ),
        ),
        ElevatedButton(
          onPressed: () {
            if (_author.text != "") {
              _futureQuote = createQuotes(_author.text, 'launch');

              // int intWorld = int.parse(worldsize);
              // int worldPlusOne = intWorld+1;
              // int size = intWorld%2 == 0 ? worldPlusOne*worldPlusOne : intWorld*intWorld;
              // print(size);
              Navigator.of(context).push(
                MaterialPageRoute(
                  // settings: RouteSettings(name: "/Page1"),
                  // builder: (context) => getPosition(_author.text),
                  builder: (context) => GamePage(_author.text),

                  // builder: (context) => HomePage(),
                ),
              );
            } else {
              showDialog<String>(
                context: context,
                builder: (BuildContext context) => AlertDialog(
                  title: const Text('Error'),
                  content: const Text('Please enter a name for your Robot'),
                  actions: <Widget>[
                    TextButton(
                      onPressed: () => Navigator.pop(context, 'OK'),
                      child: const Text('OK',
                          style: TextStyle(color: Colors.lightBlueAccent)),
                    ),
                  ],
                ),
              );
            }
          },
          child: const Text('LAUNCH TEXTBASE GAME '),
          style: ElevatedButton.styleFrom(primary: Colors.lightBlueAccent),
        ),
        ElevatedButton(
          onPressed: () {
            if (_author.text != "") {
              _futureQuote = createQuotes(_author.text, 'launch');

              // int intWorld = int.parse(worldsize);
              // int worldPlusOne = intWorld+1;
              // int size = intWorld%2 == 0 ? worldPlusOne*worldPlusOne : intWorld*intWorld;
              List<int> xAndY = [0,0];
              //Size of the world so total number of co-ordinates in the world
              int size = 9;
              //Width of the graph so for a world with 9 co-ordinates this is 3 and for 25 its 5 and so on
              int width = sqrt(size).round();
              //index of the center of the list  as this would be 0,0 in our co-ordinates and in out list it changes depending on the size
              int center = ((size-1)/2) as int;
              //change from the center of the list to the correct x change so from 0,0 to 1,0 for example
              int changeX = center + xAndY[0];
              //change from the center of the list to the correct y change so from 1,0 to 1,1
              int changeY = changeX - xAndY[1] * width;
              print(changeX);
              Navigator.of(context).push(
                MaterialPageRoute(
                  // settings: RouteSettings(name: "/Page1"),
                  builder: (context) => GamePageG(_author.text,size,changeY),

                  // builder: (context) => HomePage(),
                ),
              );
            } else {
              showDialog<String>(
                context: context,
                builder: (BuildContext context) => AlertDialog(
                  title: const Text('Error'),
                  content: const Text('Please enter a name for your Robot'),
                  actions: <Widget>[
                    TextButton(
                      onPressed: () => Navigator.pop(context, 'OK'),
                      child: const Text('OK',
                          style: TextStyle(color: Colors.lightBlueAccent)),
                    ),
                  ],
                ),
              );
            }
          },
          child: const Text('LAUNCH GRIDBASE GAME '),
          style: ElevatedButton.styleFrom(primary: Colors.deepPurple),
        ),
      ],
    );
  }

  FutureBuilder<Quote> buildFutureBuilder() {
    return FutureBuilder<Quote>(
      future: _futureQuote,
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          return Text(snapshot.data!.title);
        } else if (snapshot.hasError) {
          return Text('${snapshot.error}');
        }
        return const CircularProgressIndicator();
      },
    );
  }
}
