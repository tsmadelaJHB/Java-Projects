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
      Uri.parse('http://localhost:5000/quotes'),

    );

    if(response.statusCode == 200){
      try{
        _jsonresponse = jsonDecode(response.body);
      }catch (e){
        _error = true;
        _errorMessage = e.toString();
        _jsonresponse = [];
      }
      notifyListeners();
    }else{
      _error = true;
      _errorMessage = "ERROR: Cannot connect to http://localhost:5000/quotes"; //$response.statuscode
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
