import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:groupproject/Models/get_data.dart';
import 'package:groupproject/Models/post_data.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import 'package:provider/src/provider.dart';

import 'addingdata.dart';
import 'client.dart';

class ClientWorldList extends StatelessWidget {
  const ClientWorldList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    context.read<QuoteData>().fetchData;
    return Padding(
      padding: const EdgeInsets.all(4.0),
      child: Scaffold(
        appBar: AppBar(
          title: const Text("Available Worlds"),
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
                              'SOMETHING WENT WRONG ${value.errorMessage}',
                              textAlign: TextAlign.center,
                            )
                          : ListView.builder(
                              itemCount: value.jsonresponse.length,
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
                                            value.jsonresponse[index]["text"];
                                        String myname =
                                            value.jsonresponse[index]["name"];
                                        List<String> worldname =
                                            myquote.split(" ");
                                        print(worldname[1]);
                                        Navigator.of(context).push(
                                            MaterialPageRoute(
                                                builder: (context) =>
                                                    SetUp(worldname[1])));
                                        print(worldname[1]);
                                        LoadWorld("admin", myquote);
                                      },
                                      title:
                                          Text(value.jsonresponse[index]["text"]),
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
        floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
        floatingActionButton: Stack(
          fit: StackFit.expand,
          children: [
            //Admin Button
            Positioned(
              left: 10,
              bottom: 50,

              child: FloatingActionButton.extended(
                  onPressed: () {
                    LoadWorld("quick", 'default');
                    Navigator.of(context).push(MaterialPageRoute(
                        builder: (context) => SetUp("2")));
                    // Navigator.pushNamed(context, "/home")
                  },
                  heroTag: 'QUICK GAME',
                  backgroundColor: Colors.amber,
                  icon: const Icon(Icons.bolt_rounded),
                  label: const Text("QUICK GAME")),
              // floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
            ),
            //Client button
            Positioned(
              right: 10,
              bottom: 0,

              child: FloatingActionButton.extended(
                  onPressed: () {
                    Navigator.of(context).push(
                        MaterialPageRoute(builder: (context) => ClientPage()));
                  },
                  heroTag: Text("Client"),
                  backgroundColor: Colors.lightBlueAccent,
                  icon: const Icon(Icons.person_rounded),
                  label: const Text("BACK TO CLIENT PAGE")),
              // floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
            )
          ],
        ),
      ),
    );
  }
}
