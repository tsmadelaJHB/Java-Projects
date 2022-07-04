import 'package:client/views/object.dart';
import 'package:flutter/material.dart';

class BlockMake extends StatefulWidget {
  List<Object> objects;
  int index;
  BlockMake(this.objects, this.index, {Key? key}) : super(key: key);


  @override
  State<BlockMake> createState() => _BlockMakeState(objects, index);
}

class _BlockMakeState extends State<BlockMake> {
  var _value;
  List<Object> objects;
  int index;
  late Object object;
  _BlockMakeState(this.objects, this.index);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          border: Border.all(color: Colors.white12),
          color: Colors.blueGrey,
          image: _value == 'obstacle'
              ? const DecorationImage(image: AssetImage("assets/obstacle.png"))
              : _value == 'pit'
                  ? const DecorationImage(
                      image: AssetImage("assets/pit.png"))
                  : _value == 'mine'
                      ? const DecorationImage(
                          image: AssetImage("assets/mine.png"))
                      : null),
      child: DropdownButton<String>(
        iconSize: 0,
        // value: _value,
        onChanged: (String? newValue) {
          object =Object(index, newValue!);
          objects.add(object);
          setState(() {
            _value = newValue;
          });
        },
        items: <String>['obstacle', 'pit', 'mine']
            .map<DropdownMenuItem<String>>((String value) {
          return DropdownMenuItem<String>(
            value: value,
            child: Text(value),
          );
        }).toList(),
      ),
    );
  }
}
