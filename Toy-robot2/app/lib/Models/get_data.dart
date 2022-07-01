import 'dart:convert';
import 'dart:io';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

class QuoteData with ChangeNotifier{
  // Map<String, dynamic> _map = {}; //main data
  List<dynamic> _jsonresponse = <dynamic>[];
  bool  _error = false;
  String _errorMessage = "";

  QuoteData(){
    fetchData;
  }


  List<dynamic> get jsonresponse => _jsonresponse;
  bool get error => _error;
  String get errorMessage => _errorMessage;

  Future<void> get fetchData async{ //fetch json data
    final response = await http.get(
      Uri.parse('http://localhost:7000/world/1'),

    );

    print(response.statusCode);

    if(response.statusCode == 200){
      try{
        _jsonresponse = jsonDecode("["+response.body+"]");
      }catch (e){
        _error = true;
        _errorMessage = e.toString();
        _jsonresponse = [];
      }
      notifyListeners();
    }else{
      _error = true;
      _errorMessage = "ERROR: Cannot connect to http://localhost:7000/world"; //$response.statuscode
      _jsonresponse = [];
    }
    notifyListeners();
  }

  List<dynamic> get jsonresponsep => _jsonresponse;
  bool get errorp => _error;
  String get errorMessagep => _errorMessage;
  Future<void> get fetchPosition async{ //fetch json data
    final response = await http.get(
      Uri.parse('http://localhost:7000/position'),

    );

    if(response.statusCode == 200){
      try{
        print(response.body);
        _jsonresponse = jsonDecode(response.body);
      }catch (e){
        _error = true;
        _errorMessage = e.toString();
        _jsonresponse = [];
      }
      notifyListeners();
    }else{
      _error = true;
      _errorMessage = "ERROR: Cannot connect to http://localhost:7000/position"; //$response.statuscode
      _jsonresponse = [];
    }
    notifyListeners();
  }
  void initialValues(){
    _jsonresponse = [];
    _error = false;
    _errorMessage = "";
    notifyListeners();
  }
}
