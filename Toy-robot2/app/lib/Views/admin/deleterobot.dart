import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

Future<Album> fetchAlbum() async {
  final response = await http.get(Uri.parse('http://localhost:7000/world'));

  print(response.body);
  if (response.statusCode == 200) {
    // A 200 OK response means
    // ready to parse the JSON.
    return Album.fromJson(json.decode(response.body));
  } else {
    // If not a 200 ok response
    // means throw an exception.
    throw Exception('Failed to load album');
  }
}

Future<Album> deleteAlbum(String id) async {
  final http.Response response = await http.delete(
    Uri.parse('http://localhost:7000/world'),
  );
  print(response.body);
  if (response.statusCode == 404) {
    print("deleting");
    return Album.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Item Not Deleted!');
  }
}

class Album {
  final int id;
  final String title;

  Album({required this.id, required this.title});

  factory Album.fromJson(List<dynamic> json) {
    return Album(
      id: json[0],
      title: json[0],
    );
  }
}

void main() {
  runApp(DeleteRobot());
}

class DeleteRobot extends StatefulWidget {
  @override
  _MyAppState createState() {
    return _MyAppState();
  }
}

class _MyAppState extends State<DeleteRobot> {
  late Future<Album> _futureAlbum;

  @override
  void initState() {
    super.initState();
    _futureAlbum = fetchAlbum();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Data Deletion',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('DELETE ROBOT'),
          backgroundColor: Colors.green,
        ),
        body: Center(
          child: FutureBuilder<Album>(
            future: _futureAlbum,
            builder: (context, snapshot) {
              if (snapshot.connectionState == ConnectionState.done) {
                // if (snapshot.hasData) {
                return Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Text('${snapshot.data?.title ?? 'Deleted'}'),
                    RaisedButton(
                      child: Text('Delete Data'),
                      onPressed: () {
                        deleteAlbum("bongi");

                        // setState(() {
                        //   _futureAlbum =
                        //       deleteAlbum(snapshot.data!.id.toString());
                        // });
                      },
                    ),
                  ],
                );
                // } else if (snapshot.hasError) {
                //   return Text("ERRRRRRRRRRR");
              }

              return CircularProgressIndicator();
            },
          ),
        ),
      ),
    );
  }
}