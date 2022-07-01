import 'dart:convert';
import 'dart:js';

import 'package:app/controller/initialize_quote.dart';
import 'package:app/models/post_data.dart';
import 'package:app/view/allquotes.dart';
import 'package:app/view/view_quote_full.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';


import '../main.dart';



class AddQuotes extends StatelessWidget {
  final TextEditingController _controller = TextEditingController();
  final TextEditingController _author = TextEditingController();
  Future<Quote>? _futureQuote;

  AddQuotes({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'PAGE FOR CREATING QUOTES',
      theme: ThemeData(
        primarySwatch: Colors.indigo,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: const Text('ADD QUOTE'),
        ),
        body: Center(
          child:Container(

          decoration: const BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/images/image.jpg"),
            ),
          ),
          alignment: Alignment.center,
          padding: const EdgeInsets.all(8.0),
          child: (_futureQuote == null) ? buildColumn(context) : buildFutureBuilder(),

        ),
      ),
      ),
    );
  }

  Column buildColumn(BuildContext context) {
    return Column(
      // mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        TextField(
          style: TextStyle(color: Colors.white),
          controller: _author,
          decoration: const InputDecoration(hintText: 'ENTER AUTHOR',),

        ),
        TextField(
          style: TextStyle(color: Colors.white),
          controller: _controller,
          decoration: const InputDecoration(hintText: 'ENTER QUOTE'),
        ),

        ElevatedButton(
          onPressed: () {
            _futureQuote = createQuotes(_author.text,_controller.text) as Future<Quote>?;
            print("Going to view one");
            Navigator.of(context).push(
              MaterialPageRoute(
                // settings: RouteSettings(name: "/Page1"),
                builder: (context) => OneQuote(quote: _author.text, name: _controller.text,),
                // builder: (context) => MyApp(),
              ),
            );
          },
          child: const Text('CREATE DATA'),
        ),

        ElevatedButton(
          onPressed: () {
            print("Going to MAIN PAGE");
            Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => MyApp(),
              ),
            );
          },
          child: const Text('CANCEL'),
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
