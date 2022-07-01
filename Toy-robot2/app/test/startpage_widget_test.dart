import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:groupproject/Views/client/client.dart';
import 'package:groupproject/Views/start.dart';

Widget createStartUpScreen() => (MaterialApp(
  home: StartPage(),
)
);



void main(){
  group("client page Widget testing", (){
    testWidgets("testing the widget visability", (tester) async{
      await tester.pumpWidget(createStartUpScreen());
      expect(find.text('Start'), findsOneWidget);
    });
  });
}