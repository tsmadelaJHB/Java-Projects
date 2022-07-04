import 'dart:math';

class Robot{
  late final String name;
  late final String type;
  late Point position;

  Robot({required this.name, required this.position, required this.type});
}

// Robot robot = Robot(name: "name", position: const Point(2,4), type: "type");