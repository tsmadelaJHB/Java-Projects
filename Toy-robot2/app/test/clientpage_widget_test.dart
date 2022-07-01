import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:groupproject/Views/client/client.dart';

Widget createStartUpScreen() => (MaterialApp(
  home: ClientPage(),
)
);



void main(){
  group("client page Widget testing", (){
    testWidgets("testing the widget visability", (tester) async{
      await tester.pumpWidget(createStartUpScreen());
      expect(find.text('   Help'), findsOneWidget);
      expect(find.text('   Select World'), findsOneWidget);
      expect(find.text('   Quick Game'), findsOneWidget);
      
    });
  });
}