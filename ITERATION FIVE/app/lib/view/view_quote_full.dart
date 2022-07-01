import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import '../main.dart';

class OneQuote extends StatelessWidget{
  String quote;
  String name;
  OneQuote( {Key? key,required this.quote,required this.name,}) : super(key: key);

  @override
  Widget build(BuildContext context){

    return MaterialApp(
        title: 'ONE QUOTE PAGE',
        theme: ThemeData(
        primarySwatch: Colors.indigo,
    ),
    // home: Container(
    // decoration: const BoxDecoration(
    // image: DecorationImage(
    // image: AssetImage('assets/images/image.jpg',
    // ), fit: BoxFit.cover)),
    home: Scaffold(
      backgroundColor: Colors.deepPurple,
        appBar: AppBar(
          title: const Text("ONE QUOTE"),
          centerTitle: true,
          backgroundColor: Colors.transparent,
        ),
      body: Column(

          children: [
          // Container(
          //     // height: double.infinity,
          //     // width: double.infinity,
          //     child: FittedBox(
          //     fit: BoxFit.fill,
          //     child: Image.network(
          //       'https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260',
          //     ),
          //   )),
            Container(
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius:  BorderRadius.circular(5),
              ),
              width: 200,
              child: (Text(name)),
            ),
            Container(
              decoration: BoxDecoration(
                shape:BoxShape.rectangle,
                color: Colors.tealAccent,
                borderRadius:  BorderRadius.circular(5),
              ),
              margin: const EdgeInsets.all(12),
              height: 5 * 10.0,
              width: 400,
              child: (Text("=> "+quote)),
              ),
          ]
      ),
      floatingActionButton: FloatingActionButton.extended(
        label:const Text("CANCEL",) ,
        icon:const Icon(Icons.cancel),
        onPressed: (){
          Navigator.of(context).push(MaterialPageRoute(builder: (context)=>MyApp()));
        },
        backgroundColor: Colors.red,
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
          )
        );
    // );
  }



}