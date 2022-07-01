import 'package:flutter/material.dart';
import 'package:groupproject/Views/client/game_screen_p2.dart';


import 'Models/post_data.dart';
import 'Views/client/client.dart';

class Mybutton extends StatelessWidget {

  final color;
  final child;
  final text;
  final name;
  final function;

  Mybutton({this.color, this.child, this.text, this.name, this.function});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(1.0),
      child: GestureDetector(
        onTap: function,
        child: ClipRRect(
          borderRadius: BorderRadius.circular(50),
          child: Container(
            color: color,
            height: 70,
            width: 70,
            child: InkWell(
              child: child,
              onTap: () {

                if(text=='close'){
                  showDialog<String>(
                    context: context,
                    builder: (BuildContext context) => AlertDialog(
                      title: const Text('CONFIRM'),
                      content: const Text('ARE YOU SURE YOU WANT TO QUIT?'),
                      actions: <Widget>[
                        TextButton(
                          onPressed: () => Navigator.pop(context, 'Cancel'),
                          child: const Text('Cancel'),
                        ),
                        TextButton(
                          onPressed: () {
                            createQuotes(name, text);
                            Navigator.of(context).push(MaterialPageRoute(builder: (context)=>ClientPage()));
                          },
                          child: const Text('OK'),
                        ),
                      ],
                    ),
                  );
                  // 
                }
                else{
                  Navigator.of(context).push(MaterialPageRoute(builder: (context)=>GamePage(name)));
                  createQuotes(name, text);
                }
              },

            ),
          ),
        ),
      ),
    );
  }
}
class MybuttonB extends StatelessWidget {

  final color;
  final child;
  final text;
  final name;
  final function;

  MybuttonB({this.color, this.child, this.text, this.name, this.function});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(1.0),
      child: GestureDetector(
        onTap: function,
        child: ClipRRect(
          borderRadius: BorderRadius.circular(50),
          child: Container(
            color: color,
            height: 70,
            width: 70,
            child: Center(
              child: child,

            ),
          ),
        ),
      ),
    );
  }
}