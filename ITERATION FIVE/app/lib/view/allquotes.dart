import 'dart:convert';
import 'dart:ui';

import 'package:app/view/view_quote_full.dart';
import 'package:flutter/material.dart';
import 'package:app/models/get_data.dart';
import 'package:provider/provider.dart';

import 'add_quotes.dart';

class Quotes extends StatelessWidget {
  const Quotes({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    context.read<QuoteData>().fetchData;
    return Scaffold(
      backgroundColor: Colors.deepPurple,
      appBar: AppBar(
        title: const Text("QuteQuotes"),
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
                          String myquote=value.jsonresponse[index]["text"];
                          String myname=value.jsonresponse[index]["name"];
                          Navigator.of(context).push(MaterialPageRoute(builder: (context)=>OneQuote(quote: myquote,name: myname,)));
                          print(value.jsonresponse[index]["text"]);
                                                },
                        title:Text(value.jsonresponse[index]["text"]+" =>"+value.jsonresponse[index]["name"]) ,
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
        label:Text("ADD QUOTE") ,
        icon:Icon(Icons.add),
        onPressed: (){
          Navigator.of(context).push(MaterialPageRoute(builder: (context)=>AddQuotes( )));
        },
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    );
  }
}

