import 'dart:async';
import 'dart:convert';
import 'dart:math';

import 'package:flutter/material.dart';
import 'package:groupproject/Models/get_data.dart';
import 'package:groupproject/Models/post_data.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import 'package:provider/src/provider.dart';

import 'addingdata.dart';
import 'client.dart';
import 'game_screen.dart';

class getPosition extends StatelessWidget {
  var myname;
  getPosition(this.myname, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    context.read<QuoteData>().fetchPosition;
    return Padding(
      padding: const EdgeInsets.all(4.0),
      child: Scaffold(
        appBar: AppBar(
          title: const Text("Position"),
          centerTitle: true,
          backgroundColor: Colors.grey[900],
        ),
        body: Container(
          decoration: const BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/images/world.jpg"),
              fit: BoxFit.cover,
            ),
          ),
          child: RefreshIndicator(
            onRefresh: () async {},
            child: Center(
              child: Consumer<QuoteData>(
                builder: (context, value, child) {
                  return value.jsonresponse.length == 0 && !value.error
                      ? ElevatedButton(

                    onPressed: () {  },
                    child: const Text(
                        "NO AVAILABLE WORLD, CLICK QUICK GAME"),
                  )
                      : value.error
                      ? Text(
                    'SOMETHING WENT WRONG ${value.errorMessagep}',
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

                            onTap: () {
                              String myquote =
                              value.jsonresponsep[index]["text"];
                              // String myname =
                              // value.jsonresponsep[index]["name"];
                              List<String> worldname =
                              myquote.split(" ");
                              print(myquote);

                              // print(worldname[1]);
                              // LoadWorld("admin", myquote);
                              //Get the x and y postion and add them to this list so tha x is index 0 and y index 1
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
                                  builder: (context) => GamePageG(myname,size,changeY),

                                  // builder: (context) => HomePage(),
                                ),
                              );
                            },
                            title:
                            Text(value.jsonresponsep[index]["text"]),
                          ),
                        ),
                      );
                    },
                  );
                },
              ),
            ),
          ),
        ),
        // floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
        // floatingActionButton: Stack(
        //   fit: StackFit.expand,
        //   children: [
        //     //Admin Button
        //     Positioned(
        //       left: 10,
        //       bottom: 50,
        //
        //       child: FloatingActionButton.extended(
        //           onPressed: () {
        //             LoadWorld("quick", 'default');
        //             Navigator.of(context).push(MaterialPageRoute(
        //                 builder: (context) => SetUp("2")));
        //             // Navigator.pushNamed(context, "/home")
        //           },
        //           heroTag: 'QUICK GAME',
        //           backgroundColor: Colors.amber,
        //           icon: const Icon(Icons.bolt_rounded),
        //           label: const Text("QUICK GAME")),
        //       // floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
        //     ),
        //     //Client button
        //     Positioned(
        //       right: 10,
        //       bottom: 0,
        //
        //       child: FloatingActionButton.extended(
        //           onPressed: () {
        //             Navigator.of(context).push(
        //                 MaterialPageRoute(builder: (context) => ClientPage()));
        //           },
        //           heroTag: Text("Client"),
        //           backgroundColor: Colors.lightBlueAccent,
        //           icon: const Icon(Icons.person_rounded),
        //           label: const Text("BACK TO CLIENT PAGE")),
        //       // floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
        //     )
        //   ],
        // ),
      ),
    );
  }
}
