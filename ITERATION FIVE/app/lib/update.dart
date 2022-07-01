import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'models/get_data.dart';


Future<Album> fetchAlbum() async {
  final response = await http.get(
    Uri.parse('http://localhost:5000/quotes'),
  );
  print("hello");
  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return Album.fromJson(jsonDecode(response.body));
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load album');
  }
}

Future<Album> updateAlbum(String title) async {
  final response = await http.put(
    Uri.parse('http://localhost:5000/quotes'),
    body: jsonEncode(<String, String>{
      'text': title,
    }),
  );
  print("joe");

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.

    return Album.fromJson(jsonDecode(response.body));
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to update album.');
  }
}

class Album {
  final int id;
  final String title;

  Album({required this.id, required this.title});

  factory Album.fromJson(List<dynamic> json) {
    return Album(
      id: json[0],
      title: json[1],
    );
  }
}

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  _MyAppState createState() {
    return _MyAppState();
  }
}

class _MyAppState extends State<MyApp> {
  final TextEditingController _controller = TextEditingController();
  late Future<void> _futureAlbum;

  @override
  void initState() {
    super.initState();
    // _futureAlbum = fetchAlbum();
    _futureAlbum = QuoteData().fetchData;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Update Data Example',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Update Data Example'),
        ),
        body: Container(

          alignment: Alignment.center,
          padding: const EdgeInsets.all(8.0),
          child: FutureBuilder<void>(

            future: _futureAlbum,
            builder: (context, snapshot) {
              if (snapshot.connectionState == ConnectionState.done) {
                print("hr");

                  return Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                      Text("text"),
                      // Text(snapshot.data!.title),
                      TextField(
                        controller: _controller,
                        decoration: const InputDecoration(
                          hintText: 'Enter Title',
                        ),
                      ),
                      ElevatedButton(
                        onPressed: () {
                          setState(() {
                            _futureAlbum = updateAlbum(_controller.text);
                          });
                        },
                        child: const Text('Update Data'),
                      ),
                    ],
                  );
                } else if (snapshot.hasError) {
                  return Text('${snapshot.error}');
                }

              // return Text('${snapshot.error}');
              return const CircularProgressIndicator();
            },
          ),
        ),
      ),
    );
  }
}