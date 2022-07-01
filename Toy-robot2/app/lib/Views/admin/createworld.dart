import 'package:groupproject/Controller/initialize_quote.dart';
import 'package:groupproject/Models/admin_post.dart';
import 'package:groupproject/Models/post_data.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'admin.dart';

class CreateWorld extends StatelessWidget {

  final TextEditingController _worldname = TextEditingController();
  final TextEditingController _size = TextEditingController();
  final TextEditingController _obs = TextEditingController();
  Future<Quote>? _futureQuote;

  CreateWorld({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'PAGE FOR CREATING QUOTES',
      // theme: ThemeData(
      //   primarySwatch: Colors.transparent,
      // ),
      home: Scaffold(
        appBar: AppBar(
          title: const Text('DESIGN YOUR WONDERFUL WORLD'),
          centerTitle: true,
          backgroundColor: Colors.transparent,
        ),
        body: Center(
          child: Container(
            // decoration: const BoxDecoration(
            //   image: DecorationImage(
            //     image: AssetImage("assets/images/robot.jpg"),
            //     fit: BoxFit.cover,
            //   ),
            // ),
            alignment: Alignment.center,
            padding: const EdgeInsets.all(8.0),
            child: (_futureQuote == null)
                ? buildColumn(context)
                : buildFutureBuilder(),
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
          // style: TextStyle(color: Colors.white,),
          controller: _worldname,
          decoration: const InputDecoration(
            hintText: 'ENTER WORLD NAME',
          ),
        ),
        TextField(
          // style: TextStyle(color: Colors.white,),
          controller: _size,
          decoration: const InputDecoration(
            hintText: 'ENTER WORLD SIZE',
          ),
        ),
        TextField(
          // style: TextStyle(color: Colors.white,),
          controller: _obs,
          decoration: const InputDecoration(
            hintText: 'ENTER OBSTACLES COORDINATES e.g (1,0)',
          ),
        ),

        ElevatedButton(
          onPressed: () {
            if(_worldname.text != "" && _size.text !=""){
              _futureQuote = createWorld(_worldname.text, _size.text,_obs.text);
              showDialog<String>(
                context: context,
                builder: (BuildContext context) => AlertDialog(
                  title: const Text('CONGRATULATIONS'),
                  content: Text('WORLD '+_worldname.text+' IS CREATED'),
                  actions: <Widget>[
                    TextButton(
                      onPressed: () {
                        Navigator.of(context).push(MaterialPageRoute(builder: (context)=>AdminPage(),));
                      },
                      child: const Text('OK'),
                    ),
                  ],
                ),
              );
              print("WORLD IS CREATED");
            }
            else if(_worldname.text =="")
              {
                showDialog<String>(
                  context: context,
                  builder: (BuildContext context) => AlertDialog(
                    title: const Text('ERROR'),
                    content: const Text('PLEASE ENTER WORLD NAME'),
                    actions: <Widget>[
                      TextButton(
                        onPressed: () => Navigator.pop(context, 'OK'),
                        child: const Text('OK'),
                      ),
                    ],
                  ),
                );
              }
            else{
              showDialog<String>(
                context: context,
                builder: (BuildContext context) => AlertDialog(
                  title: const Text('ERROR'),
                  content: const Text('ENTER WORLD SIZE'),
                  actions: <Widget>[
                    TextButton(
                      onPressed: () => Navigator.pop(context, 'OK'),
                      child: const Text('OK'),
                    ),
                  ],
                ),
              );
            }
          },
          child: const Text('CREATE WORLD'),
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
