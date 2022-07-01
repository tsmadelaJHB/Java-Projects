import 'package:groupproject/Controller/initialize_quote.dart';
import 'package:groupproject/Models/admin_post.dart';
import 'package:groupproject/Models/post_data.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'admin.dart';

class Obstacles extends StatelessWidget {

  final TextEditingController _worldname = TextEditingController();
  final TextEditingController _size = TextEditingController();
  final TextEditingController _obs = TextEditingController();
  Future<Quote>? _futureQuote;

  Obstacles({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ADDING OBSTACLES',
      // theme: ThemeData(
      //   primarySwatch: Colors.transparent,
      // ),
      home: Scaffold(
        appBar: AppBar(
          title: const Text('ADD OR REMOVE OBSTACLES IN THE WORLD'),
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
          controller: _obs,
          decoration: const InputDecoration(
            hintText: 'ENTER OBSTACLES COORDINATES  AS MANY AS YOU LIKE e.g (1,0 1,1 )',
          ),
        ),

        ElevatedButton(
          onPressed: () {
            if(_obs!= ""){
              _futureQuote = AddObstacles(_worldname.text,_obs.text);
              showDialog<String>(
                context: context,
                builder: (BuildContext context) => AlertDialog(
                  title: const Text('CONGRATULATIONS'),
                  content: Text('OBSTACLES ARE ADDED IN '+ _worldname.text+"\n DO YOU WANT TO ADD OTHER OBSTACLES?"),
                  actions: <Widget>[
                    TextButton(
                      onPressed: () {
                        Navigator.pop(context, "Cancel");
                      },
                      child: const Text('YES'),
                    ),
                    TextButton(
                      onPressed: () {
                        Navigator.of(context).push(MaterialPageRoute(builder: (context)=>AdminPage(),));
                      },
                      child: const Text('NO'),
                    ),
                  ],
                ),
              );
              print("created");

            }
          },
          child: const Text('ADD OBSTACLES'),
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
