import 'dart:convert';
import 'package:app/controller/initialize_quote.dart';
import 'package:http/http.dart' as http;

import 'package:app/view/add_quotes.dart';

Future<Quote> createQuotes(String name,String text) async {
  final response = await http.post(
    Uri.parse('http://localhost:5000/quotes'),
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