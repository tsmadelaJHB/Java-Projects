import 'package:client/controllers/worlds.dart';
import 'package:client/models/world.dart';
import 'package:client/views/admin_game_mode.dart';
import 'package:client/views/create_world.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';


class ListWorlds extends StatelessWidget{
  const ListWorlds({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Worlds worldsNotifier = Provider.of<Worlds>(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text("Toy Worlds"),
      ),
      body: Center(
        child: ListView.builder(
          itemCount: worldsNotifier.worlds.length,
            itemBuilder: (context, index) {
              return GestureDetector(
                onTap: () => _onTap(context, worldsNotifier.worlds[index]),
                child: Card(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(20.8),
                        child: Consumer<Worlds>(
                            builder: (_, worldsNotifier, __) => Text(
                                worldsNotifier.worlds[index].name)),
                      ),
                      IconButton(onPressed: () {},
                          icon: const Icon(Icons.delete),
                      color: Colors.redAccent,
                      splashColor: Colors.red,)
                    ],
                  ),

                ),
              );
            }
        )
      ),
      floatingActionButton: FloatingActionButton.extended(heroTag: null,onPressed: () {
        Navigator.of(context).push(MaterialPageRoute(builder: (context)=> const CreateWorld()));
      },
          label: const Text("Add world")),
    );
  }

  Future<void> _onTap(BuildContext context, World world) {
    return showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text(world.name),
            content: Text(
                "Robots: " + world.robots.length.toString() + "\n"
            + "Obstacles: " + world.obstacles.length.toString()),
            actions: [
              TextButton(onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(builder: (context)=> AdminGameMode(world: world)));
              },
                  child: const Center(child: Text("Launch World"))),
            ],
          );
        });
  }

}