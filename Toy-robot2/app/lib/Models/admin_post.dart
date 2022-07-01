import 'dart:convert';
import 'package:groupproject/Controller/initialize_quote.dart';
// import 'package:http/http.dart' as http;
import 'package:http/http.dart' as http;

Future<Quote> createWorld(String name,String size,String obstacle) async {
  final response = await http.post(
    Uri.parse('http://localhost:7000/world/1'),
    body: jsonEncode(<String, String>{
      'name': 'admin',
      'text': name+" "+size+" "+obstacle,
      // 'obstacle': obstacle,
    }),
  );

  print(response.statusCode);

  if (response.statusCode == 201) {
    // If the server did return a 201 CREATED response,
    // then parse the JSON.
    return Quote.fromJson(jsonDecode(response.body));
  } else {
    // If the server did not return a 201 CREATED response,
    // then throw an exception.
    throw Exception('Failed to create data.');
  }
}

Future<Quote> AddObstacles(String name, String obstacle) async {
  final response = await http.post(
    Uri.parse('http://localhost:7000/obstacles'),
    body: jsonEncode(<String, String>{
      'name': 'obstacles',
      'text': name+" "+obstacle,
      // 'obstacle': obstacle,
    }),
  );

  if (response.statusCode == 201) {
    // If the server did return a 201 CREATED response,
    // then parse the JSON.
    return Quote.fromJson(jsonDecode(response.body));
  } else {
    // If the server did not return a 201 CREATED response,
    // then throw an exception.
    throw Exception('Failed to create data.');
  }
}