import 'dart:collection';

import 'package:client/models/world.dart';
import 'package:client/utils/utils.dart';
import 'package:flutter/cupertino.dart';

class Worlds extends ChangeNotifier {
  final List<World> _worlds = Utils().getWorlds();


  UnmodifiableListView<World> get worlds => UnmodifiableListView(_worlds);

  void addWorld(World world) {
    _worlds.add(world);
    notifyListeners();
  }

  void removeWorld(index) {
    _worlds.removeWhere((_world) => _world.name == _worlds[index].name);
    notifyListeners();
  }
}
