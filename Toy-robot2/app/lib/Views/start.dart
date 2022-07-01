import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter/services.dart';
import 'package:groupproject/Views/login_page.dart';

class StartPage extends StatefulWidget {
  StartPage({Key? key}) : super(key: key);

  @override
  _StartPage createState() => _StartPage();
}

class _StartPage extends State<StartPage> {
  @override
  Widget build(BuildContext context) {
    SystemChrome.setEnabledSystemUIMode(SystemUiMode.manual,
        overlays: [SystemUiOverlay.bottom]);
    return Padding(
      padding: const EdgeInsets.all(4.0),
      child: Scaffold(
        backgroundColor: Colors.grey[900],
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              GestureDetector(
                onTap: () {
                  Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => LoginPage()));
                },
                child: Container(
                    width: 200,
                    height: 200,
                    decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(100),
                        color: Colors.white,
                        boxShadow: const [
                          BoxShadow(
                              color: Colors.lightBlueAccent,
                              blurRadius: 10,
                              spreadRadius: 1,
                              offset: Offset(4, 4))
                        ]),
                    child: const Align(
                      alignment: Alignment.center,
                      child: Text(
                        'Start',
                        style: TextStyle(
                            fontSize: 45, color: Colors.lightBlueAccent),
                      ),
                    )),
              )
            ],
          ),
        ),
      ),
    );
  }
}
