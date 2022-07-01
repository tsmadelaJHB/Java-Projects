// @dart=2.9

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:groupproject/Views/admin/admin.dart';
import 'package:groupproject/Views/admin/createworld.dart';
import 'package:mockito/mockito.dart';

class MockNavigatorObserver extends Mock implements NavigatorObserver {}

void main() {
  group('AdminPage navigation tests', () {
    NavigatorObserver mockObserver;

    setUp(() {
      mockObserver = MockNavigatorObserver();
    });

    Future<void> _buildAdminPage(WidgetTester tester) async {
      await tester.pumpWidget(MaterialApp(
        home: AdminPage(),

        // This mocked observer will now receive all navigation events
        // that happen in our app.
        navigatorObservers: [mockObserver],
      ));

      // The tester.pumpWidget() call above just built our app widget
      // and triggered the pushObserver method on the mockObserver once.
      verify(mockObserver.didPush(any, any));
    }

    Future<void> _navigateToCreateWorld(WidgetTester tester) async {
      // Tap the button which should navigate to the create world page.

      // By calling tester.pumpAndSettle(), we ensure that all animations
      // have completed before we continue further.
      await tester.tap(find.byKey(AdminPage.navigateToCreateWorldButtonKey));
      await tester.pumpAndSettle();
    }

   
    testWidgets(
        'when tapping "create World" button, should navigate to create world page',
        (WidgetTester tester) async {
      await _buildAdminPage(tester);
      await _navigateToCreateWorld(tester);

      // By tapping the button, we should've now navigated to the details
      // page. The didPush() method should've been called...
      verify(mockObserver.didPush(any, any));

      // ...and there should be a DetailsPage present in the widget tree...
      expect(find.byType(CreateWorld), findsOneWidget);
    });
  });
}
