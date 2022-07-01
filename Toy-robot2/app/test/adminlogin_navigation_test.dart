// @dart=2.9

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:groupproject/Views/admin/login.dart';
import 'package:mockito/mockito.dart';

import 'package:groupproject/Views/login_page.dart';

class MockNavigatorObserver extends Mock implements NavigatorObserver {}

void main() {
  group('LoginPage navigation tests', () {
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

    Future<void> _navigateToAdminPage(WidgetTester tester) async {
      // Tap the button which should navigate to the admin login page.
    
      // By calling tester.pumpAndSettle(), we ensure that all animations
      // have completed before we continue further.
      await tester.tap(find.byKey(LoginPage.navigateToAdminButtonKey));
      await tester.pumpAndSettle();
    }


    testWidgets(
       'when tapping "admin login" button, should navigate to login admin page',
        (WidgetTester tester) async {
      await _buildLoginPage(tester);
      await _navigateToAdminPage(tester);

      // By tapping the button, we should've now navigated to the Admin 
      // page. The didPush() method should've been called...
      verify(mockObserver.didPush(any, any));

      // ...and there should be a Login Admin Page present in the widget tree...
      expect(find.byType(LoginAdminPage), findsOneWidget);

    });
  });
}