import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:groupproject/Views/admin/admin.dart';

Widget createStartUpScreen() => (MaterialApp(
  home: AdminPage(),
)
);



void main(){
  group("Admin page Widget testing", (){
    testWidgets("testing the widget visability", (tester) async{
      await tester.pumpWidget(createStartUpScreen());
      expect(find.text('Create World'), findsOneWidget);
      expect(find.text('Obstacles'), findsOneWidget);
      expect(find.text('Load World'), findsOneWidget);
      expect(find.text('Get List of Robots'), findsOneWidget);
       expect(find.text('Remove a Robot'), findsOneWidget);
    });
  });
}