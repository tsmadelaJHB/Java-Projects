import 'package:flutter/material.dart';
import 'package:groupproject/Views/client/client.dart';

import 'package:flutter/services.dart';

import 'admin/login.dart';

class LoginPage extends StatelessWidget {
  bool typing = false;

  static const navigateToAdminButtonKey = Key('navigateToAdmin');
  static const navigateToClientButtonKey = Key('navigateToClient');

  LoginPage({Key? key}) : super(key: key);
  TextEditingController messageController = TextEditingController();
  TextEditingController author = TextEditingController();

  @override
  Widget build(BuildContext context) {
    SystemChrome.setEnabledSystemUIMode(SystemUiMode.manual,
        overlays: [SystemUiOverlay.bottom]);
    return Padding(
      padding: const EdgeInsets.all(4.0),
      child: Scaffold(
        appBar: AppBar(
          title: const Text(
            'Please Login',
            style: TextStyle(fontFamily: 'RobotoMono', color: Colors.white),
          ),
          backgroundColor: Colors.grey[900],
          centerTitle: true,
        ),
        body: Container(
          decoration: const BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/images/eye.jpg"),
              fit: BoxFit.fitHeight,
            ),
          ),
        ),
        floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
        floatingActionButton: Stack(
          fit: StackFit.expand,
          children: [
            //Admin Button
            Positioned(
              left: 10,
              bottom: 50,

              child: FloatingActionButton.extended(
                 key: navigateToAdminButtonKey,
                  onPressed: () {
                    Navigator.of(context).push(
                        MaterialPageRoute(builder: (context) => LoginAdminPage()));
                    // Navigator.pushNamed(context, "/home")
                  },
                  heroTag: 'Admin',
                  backgroundColor: Colors.black,
                  icon: const Icon(Icons.sports_volleyball),
                  label: const Text("ADMIN LOGIN")),
              // floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
            ),
            //Client button
            Positioned(
              right: 10,
              bottom: 0,

              child: FloatingActionButton.extended(
                  key: navigateToClientButtonKey,
                  onPressed: () {
                    Navigator.of(context).push(
                        MaterialPageRoute(builder: (context) => ClientPage()));
                  },
                  heroTag: Text("Client"),
                  backgroundColor: Colors.lightBlueAccent,
                  icon: const Icon(Icons.person_rounded),
                  label: const Text("CLIENT LOGIN")),
              // floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
            )
          ],
        ),
      ),
    );
  }
}
