import 'dart:convert';
import 'package:groupproject/Controller/initialize_quote.dart';
// import 'package:http/http.dart' as http;
import 'package:http/http.dart' as http;

Future<Quote> createQuotes(String name,String text) async {
  final response = await http.post(
    Uri.parse('http://localhost:7000/quotes'),
    body: jsonEncode(<String, String>{
      'text': text,
      'name': name,
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

Future<Quote> LoadWorld(String name,String text) async {
  final response = await http.post(
    Uri.parse('http://localhost:7000/myworlds'),
    body: jsonEncode(<String, String>{
      'text': text,
      'name': name,
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