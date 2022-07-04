class Response {
  final String result;

  Response({required this.result});

  factory Response.fromJson(Map<String, dynamic> json) {
    return Response(
        result: json['result']
    );
  }

  String getResponse(){
    return result;
  }
}