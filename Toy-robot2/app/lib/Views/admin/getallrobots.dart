import 'package:flutter/material.dart';
import 'package:groupproject/Models/get_data.dart';
import 'package:groupproject/Views/admin/admin.dart';
import 'package:provider/provider.dart';
import 'package:provider/src/provider.dart';

import 'deleterobot.dart';

class GetAllRobots extends StatefulWidget {
  GetAllRobots({Key? key}) : super(key: key);

  @override
  _State createState() => _State();
}

class _State extends State<GetAllRobots> {


  // TextEditingController nameController = TextEditingController();

  // TextEditingController authornameController = TextEditingController();



  @override
  Widget build(BuildContext context) {
    context.read<QuoteData>().fetchData;
    return Scaffold(
      // backgroundColor: Colors.deepPurple,
      appBar: AppBar(
        title: const Text("ALL AVAILABLE ROBOTS"),
        centerTitle: true,
        backgroundColor: Colors.transparent,
      ),
      body: RefreshIndicator(

        onRefresh: () async {},
        child: Center(

          child: Consumer<QuoteData>(
            builder: (context, value, child){
              return value.jsonresponse.length == 0 && !value.error
                  ? CircularProgressIndicator()
                  : value.error ? Text('SOMETHING WENT WRONG ${value.errorMessage}'
                , textAlign: TextAlign.center,)
                  : ListView.builder(
                itemCount: value.jsonresponse.length,
                itemBuilder:(context, index) {
                  return Padding(
                    padding: const EdgeInsets.all(8.0),


                    child: Card(
                      elevation: 10,

                      child: ListTile(
                        trailing: Icon(Icons.person),
                        // tileColor: Colors.deepPurple,
                        hoverColor: Colors.grey,

                        onTap: (){
                          print(context);
                          String myquote=value.jsonresponse[index]["text"];
                          String myname=value.jsonresponse[index]["name"];
                          // Navigator.of(context).push(MaterialPageRoute(builder: (context)=>OneQuote(quote: myquote,name: myname,)));
                          Text('Delete'+myname);
                          Navigator.of(context).push(MaterialPageRoute(builder: (context)=>DeleteRobot()));
                        },
                        title:Text(value.jsonresponse[index]["name"]) ,
                      ),
                    ),
                  );

                },
              );
            },
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton.extended(
        label:Text("BACK TO ADMIN MAIN") ,
        icon:Icon(Icons.login),
        onPressed: (){
          Navigator.of(context).push(MaterialPageRoute(builder: (context)=>AdminPage()));
        },
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    );

    }

}