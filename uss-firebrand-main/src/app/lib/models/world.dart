import 'package:client/models/obstacle.dart';
import 'package:client/models/pit.dart';
import 'package:client/models/robot.dart';

import 'mine.dart';

class World {
  final String name;
  late int size = 10;
  late List<Obstacle> obstacles = [];
  late List<Mine> mines = [];
  late List<Pit> pits = [];
  late List<Robot> robots = [];

  World(this.name, {required this.obstacles, required this.robots});
}
