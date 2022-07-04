import 'dart:math';

import 'package:client/models/obstacle.dart';
import 'package:client/models/robot.dart';
import 'package:client/models/world.dart';

class Utils{
  List<World> getWorlds (){
    return[
      World("World 1",
          obstacles: [Obstacle(const Point(2,1)), Obstacle(const Point(1,0))],
          robots: [Robot(
              type: 'Sniper',
              position: const Point(0,0), name: '"Hal"')],
      ),
      World("World 2",
        obstacles: [Obstacle(const Point(2,1)), Obstacle(const Point(1,0))],
        robots: [Robot(
            type: 'Sniper',
            position: const Point(0,0), name: '"Hal"')],
      ),
      World("World 3",
        obstacles: [Obstacle(const Point(2,1)), Obstacle(const Point(1,0))],
        robots: [Robot(
            type: 'Sniper',
            position: const Point(0,0), name: '"Hal"')],
      ),
      World("World 4",
        obstacles: [Obstacle(const Point(2,1)), Obstacle(const Point(1,0))],
        robots: [Robot(
            type: 'Sniper',
            position: const Point(0,0), name: '"Hal"')],
      )
    ];
  }
}