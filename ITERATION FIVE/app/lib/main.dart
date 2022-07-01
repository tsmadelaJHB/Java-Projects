import 'package:flutter/material.dart';
import 'package:app/models/get_data.dart';
import 'package:app/view/allquotes.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: ChangeNotifierProvider(
        create: (context) => QuoteData(),
        builder: (context, child){
          return Quotes();
        },
      ),
    );
  }
}
