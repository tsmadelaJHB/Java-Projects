import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:groupproject/Models/get_data.dart';
import 'package:groupproject/Views/admin/allworlds.dart';
import 'package:groupproject/Views/start.dart';
import 'package:groupproject/Views/login_page.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider<QuoteData>(
            create: (context) => QuoteData()),
      ],
      child: MaterialApp(
      debugShowCheckedModeBanner: false,
      home: StartPage(),
    )
    );
  }
}
