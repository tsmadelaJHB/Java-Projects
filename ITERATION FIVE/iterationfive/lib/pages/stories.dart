import 'package:flutter/material.dart';

class ViewQuotes extends StatelessWidget {
  const ViewQuotes({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        tittle: Text ('My Quotes'),
      ),
    );
  }
}
