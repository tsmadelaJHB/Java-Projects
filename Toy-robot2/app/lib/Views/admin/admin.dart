import 'package:flutter/material.dart';
import 'package:groupproject/Views/admin/allworlds.dart';
import 'package:groupproject/Views/admin/deleterobot.dart';
import 'package:groupproject/Views/admin/getallrobots.dart';
import 'package:groupproject/Views/admin/obstacles.dart';

import '../login_page.dart';
import 'createworld.dart';

class AdminPage extends StatelessWidget {
  static const navigateToCreateWorldButtonKey = Key('navigateToCreateWorld');
  static const navigateToWorldListButtonKey = Key('navigateToWorldLIst');
  static const navigateToGetAllRobotsButtonKey = Key('navigateToGetALlRobots');
  static const navigateToDeleteRobotsButtonKey = Key('navigateToDeleteRobots');

  bool typing = false;
  AdminPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: const BoxDecoration(
        image: DecorationImage(
          image: AssetImage('assets/images/black.jpg'),
          fit: BoxFit.fitHeight,
        ),
      ),
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          title: const Text("Welcome Commander"),
          backgroundColor: Colors.grey[900],
          centerTitle: true,
        ),
        body: Padding(
          padding: const EdgeInsets.all(4.0),
          child: ListView(
            children: <Widget>[
              ListTile(
                key: navigateToCreateWorldButtonKey,
                leading:
                    const Icon(Icons.vpn_lock, color: Colors.lightBlueAccent),
                onTap: () {
                  Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => CreateWorld()));
                },
                title: const Text('Create World',
                    style: TextStyle(
                        fontWeight: FontWeight.bold, color: Colors.white)),
              ),
              ListTile(
                leading: const Icon(Icons.nearby_error,
                    color: Colors.lightBlueAccent),
                onTap: () {
                  Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => Obstacles()));
                },
                title: const Text('Obstacles',
                    style: TextStyle(
                        fontWeight: FontWeight.bold, color: Colors.white)),
              ),
              ListTile(
                key: navigateToWorldListButtonKey,
                leading: const Icon(Icons.wifi_protected_setup,
                    color: Colors.lightBlueAccent),
                onTap: () {
                  Navigator.of(context).push(MaterialPageRoute(
                      builder: (context) => const WorldList()));
                },
                title: const Text('Load World',
                    style: TextStyle(
                        fontWeight: FontWeight.bold, color: Colors.white)),
              ),
              ListTile(
                key: navigateToGetAllRobotsButtonKey,
                leading: const Icon(Icons.supervised_user_circle,
                    color: Colors.lightBlueAccent),
                title: const Text('Get List of Robots',
                    style: TextStyle(
                        fontWeight: FontWeight.bold, color: Colors.white)),
                onTap: () {
                  Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => GetAllRobots()));
                },
              ),
              ListTile(
                key: navigateToDeleteRobotsButtonKey,
                leading: const Icon(Icons.person_remove,
                color: Colors.lightBlueAccent),
                onTap: () {
                  Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => DeleteRobot()));
                },
                // onTap: ,
                title: const Text('Remove a Robot',
                   style: TextStyle(
                        fontWeight: FontWeight.bold, color: Colors.white)),
              ),
              ListTile(
                key: navigateToDeleteRobotsButtonKey,
                leading: const Icon(Icons.arrow_back,
                    color: Colors.lightBlueAccent),
                onTap: () {
                  Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => LoginPage()));
                },
                // onTap: ,
                title: const Text('Back to Main Page',
                    style: TextStyle(
                        fontWeight: FontWeight.bold, color: Colors.white)),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
