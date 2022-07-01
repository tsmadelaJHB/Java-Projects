

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:groupproject/Views/login_page.dart';



Widget createStartUpScreen() => (MaterialApp(
  home: LoginPage(),
)
);



void main(){
  group("login page Widget testing", (){
    testWidgets("testing the widget visability", (tester) async{
      await tester.pumpWidget(createStartUpScreen());
      expect(find.text('ADMIN LOGIN'), findsOneWidget);
      expect(find.text('CLIENT LOGIN'), findsOneWidget);
    });
  });
}