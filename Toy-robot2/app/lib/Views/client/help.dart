import 'package:flutter/material.dart';

class HelpPage extends StatelessWidget {
  bool typing = false;
  HelpPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(4.0),
      child: Scaffold(
        backgroundColor: Colors.black38,
        appBar: AppBar(
          title: const Text(
            'Game Controls',
            style: TextStyle(fontFamily: 'RobotoMono', color: Colors.white),
          ),
          backgroundColor: Colors.grey[900],
          centerTitle: true,
        ),
        body: ListView(
          children: <Widget>[
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              Icon(Icons.arrow_drop_up, color: Colors.grey[800], size: 40),
              // onTap: ,
              title: const Text('Move Forward',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              Icon(Icons.arrow_drop_down, color: Colors.grey[800], size: 40),
              // onTap: ,
              title: const Text('Move Backward',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              Icon(Icons.arrow_left, color: Colors.grey[800], size: 40),
              // onTap: ,
              title: const Text('Turn Left',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              Icon(Icons.arrow_right, color: Colors.grey[800], size: 40),
              // onTap: ,
              title: const Text('Turn Right',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              const Icon(Icons.control_camera_outlined, color: Colors.red, size: 40),
              // onTap: ,
              title: const Text('Fire weapon',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              const Icon(Icons.change_circle_outlined, color: Colors.cyanAccent, size: 40),
              // onTap: ,
              title: const Text('Reload weapons',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              const Icon(Icons.build_circle_outlined, color: Colors.yellow, size: 40),
              // onTap: ,
              title: const Text('Repair Shields',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              const Icon(Icons.coronavirus_outlined, color: Colors.lightGreenAccent, size: 40),
              // onTap: ,
              title: const Text('Drop a Mine',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              const Icon(Icons.emoji_flags_outlined, color: Colors.deepPurpleAccent, size: 40),
              // onTap: ,
              title: const Text('Surrender and Quit the Game',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              const Icon(Icons.visibility, color: Colors.lightBlueAccent, size: 40),
              // onTap: ,
              title: const Text('Reveals Nearby Obstacles And Other Players',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
            ListTile(
              tileColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
              leading:
              const Icon(Icons.memory_rounded, color: Colors.pinkAccent, size: 40),
              // onTap: ,
              title: const Text('Shows The State Of Your Robot',
                  style: TextStyle(fontWeight: FontWeight.bold)),
            ),
          ],
        ),
      ),
    );
  }
}