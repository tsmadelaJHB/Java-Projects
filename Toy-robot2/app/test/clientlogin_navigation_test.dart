// @dart=2.9

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:groupproject/Views/client/client.dart';
import 'package:mockito/mockito.dart';

import 'package:groupproject/Views/login_page.dart';

class MockNavigatorObserver extends Mock implements NavigatorObserver {}

void main() {
  group('Client Login Page navigation tests', () {
     NavigatorObserver mockObserver;

    setUp(() {
      mockObserver = MockNavigatorObserver();
    });

    Future<void> _buildLoginPage(WidgetTester tester) async {
      await tester.pumpWidget(MaterialApp(
        home: LoginPage(),

        // This mocked observer will now receive all navigation events
        // that happen in our app.
        navigatorObservers: [mockObserver],
      ));

      // The tester.pumpWidget() call above just built our app widget
      // and triggered the pushObserver method on the mockObserver once.
      verify(mockObserver.didPush(any, any));
    }


    Future<void> _navigateToClientPage(WidgetTester tester) async {
      // Tap the button which should navigate to the client page.
      //
      // By calling tester.pumpAndSettle(), we ensure that all animations
      // have completed before we continue further.
      await tester.tap(find.byKey(LoginPage.navigateToClientButtonKey));
      await tester.pumpAndSettle();
    }

   testWidgets(
       'when tapping "client login" button, should navigate to client page',
        (WidgetTester tester) async {
      await _buildLoginPage(tester);
      await _navigateToClientPage(tester);

      // By tapping the button, we should've now navigated to the Clients
      // page. The didPush() method should've been called...
      verify(mockObserver.didPush(any, any));

      // ...and there should be a Clients Page present in the widget tree...
      expect(find.byType(ClientPage), findsOneWidget);

    });
  });
}