// @dart=2.9

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:groupproject/Views/client/client.dart';
import 'package:groupproject/Views/client/help.dart';
import 'package:mockito/mockito.dart';

class MockNavigatorObserver extends Mock implements NavigatorObserver {}

void main() {
  group('ClientPage navigation tests', () {
    NavigatorObserver mockObserver;

    setUp(() {
      mockObserver = MockNavigatorObserver();
    });

    Future<void> _buildClientPage(WidgetTester tester) async {
      await tester.pumpWidget(MaterialApp(
        home: ClientPage(),

        // This mocked observer will now receive all navigation events
        // that happen in our app.
        navigatorObservers: [mockObserver],
      ));

      // The tester.pumpWidget() call above just built our app widget
      // and triggered the pushObserver method on the mockObserver once.
      verify(mockObserver.didPush(any, any));
    }

    Future<void> _navigateToHelp(WidgetTester tester) async {
      // Tap the button which should navigate to the help page.

      // By calling tester.pumpAndSettle(), we ensure that all animations
      // have completed before we continue further.
      await tester.tap(find.byKey(ClientPage.navigateToHelpButtonKey));
      await tester.pumpAndSettle();
    }

    
    testWidgets(
        'when tapping "help" button, should navigate to help page',
        (WidgetTester tester) async {
      await _buildClientPage(tester);
      await _navigateToHelp(tester);

      // By tapping the button, we should've now navigated to the help
      // page. The didPush() method should've been called...
      verify(mockObserver.didPush(any, any));

      // ...and there should be a HelpPage present in the widget tree...
      expect(find.byType(HelpPage), findsOneWidget);
    });
  });
}
