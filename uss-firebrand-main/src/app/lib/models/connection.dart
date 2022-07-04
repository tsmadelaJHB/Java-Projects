class Connector {
  final String result;

  Connector({required this.result});

  factory Connector.fromJson(Map<String, dynamic> json) {
    return Connector(
      result: json['result']
    );
  }
}