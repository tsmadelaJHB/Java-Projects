import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:groupproject/Models/get_data.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import 'package:provider/src/provider.dart';

import 'admin.dart';




class WorldList extends StatelessWidget{
  const WorldList({Key? key}) : super(key: key);


  @override
  Widget build(BuildContext context) {
    context.read<QuoteData>().fetchData;
    return Scaffold(
      // backgroundColor: Colors.deepPurple,
      appBar: AppBar(
        title: const Text("ALL AVAILABLE WORLDS"),
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
                          print(value.jsonresponse[index]["text"]);
                          showDialog<String>(
                            context: context,
                            builder: (BuildContext context) => AlertDialog(
                              title: const Text('CONFIRM'),
                              content: Text('ARE YOU SURE YOU WANT TO DELETE '+myquote+" FROM WORLD"),
                              actions: <Widget>[
                                TextButton(
                                  onPressed: () => Navigator.pop(context, 'Cancel'),
                                  child: const Text('NO'),
                                ),
                                TextButton(
                                  onPressed: () {

                                    // Navigator.of(context).push(MaterialPageRoute(builder: (context)=>ClientPage()));
                                  },
                                  child: const Text('YES'),
                                ),
                              ],
                            ),
                          );
                        },
                        title:Text(value.jsonresponse[index]["text"]) ,
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
