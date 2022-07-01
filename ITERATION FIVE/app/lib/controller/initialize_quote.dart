class Quote {
  final int id;
  final String title;

  Quote({required this.id, required this.title});

  factory Quote.fromJson(Map<String, dynamic> json) {
    return Quote(
      id: json['id'],
      title: json['text'],
    );
  }
}