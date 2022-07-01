import 'package:flutter/material.dart';
import 'package:groupproject/Models/post_data.dart';
import 'package:groupproject/Views/client/help.dart';
import 'package:groupproject/Views/client/select_world.dart';
import '../login_page.dart';
import 'addingdata.dart';

class ClientPage extends StatelessWidget {

  static const navigateToHelpButtonKey = Key('navigateToHelp');
  static const navigateToSelectWorldButtonKey = Key('navigateToSelectWorld');
  static const navigateToQuickGameButtonKey = Key('navigateToQuickGame');

  bool typing = false;
  ClientPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
        decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage('assets/images/bot.jpg'),
            fit: BoxFit.fitHeight,
          ),
        ),
        child: Scaffold(
          backgroundColor: Colors.transparent,
          body: ListView(
            children: <Widget>[
              ListTile(
                key: navigateToHelpButtonKey,
                leading:
                    const Icon(Icons.view_list_rounded,
                        color: Colors.lightBlueAccent, size: 40),
                onTap: () {
                  Navigator.of(context)
                      .push(MaterialPageRoute(builder: (context) => HelpPage()));
                },
                title: const Text('   Help',
                    style: TextStyle(fontWeight: FontWeight.bold)),
              ),
              ListTile(
                leading: const Icon(Icons.vpn_lock_rounded,
                    color: Colors.lightBlueAccent, size: 40),
                onTap: () {
                  Navigator.of(context)
                      .push(MaterialPageRoute(builder: (context) => ClientWorldList()));
                },
                title: const Text('   Select World',
                    style: TextStyle(fontWeight: FontWeight.bold)),
              ),
              ListTile(
                leading:
                    const Icon(Icons.bolt_rounded,
                        color: Colors.lightBlueAccent, size: 40),
                onTap: () {
                  LoadWorld("quick", 'default');
                  Navigator.of(context)
                      .push(MaterialPageRoute(builder: (context) => SetUp("2")));
                },
                // onTap: ,
                title: const Text('   Quick Game',
                    style: TextStyle(fontWeight: FontWeight.bold)),
              ),
              ListTile(
                leading: const Icon(Icons.arrow_back,
                    color: Colors.lightBlueAccent, size: 40),
                onTap: () {
                  Navigator.of(context)
                      .push(MaterialPageRoute(builder: (context) => LoginPage()));
                },
                title: const Text('   Back To Main Page',
                    style: TextStyle(fontWeight: FontWeight.bold)),
              ),
            ],
          ),
        ));
  }
}
