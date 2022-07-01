import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:groupproject/Views/admin/admin.dart';

class LoginAdminPage extends StatelessWidget {
  LoginAdminPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    const appTitle = 'Admin Login';

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: appTitle,
      home: Padding(
        padding: const EdgeInsets.all(4.0),
        child: Scaffold(
          appBar: AppBar(
            title: const Text(appTitle),
            backgroundColor: Colors.grey[900],
            centerTitle: true,
          ),
          body: const MyCustomForm(),
        ),
      ),
    );
  }
}

// Create a Form widget.
class MyCustomForm extends StatefulWidget {
  const MyCustomForm({Key? key}) : super(key: key);

  @override
  MyCustomFormState createState() {
    return MyCustomFormState();
  }
}

// Create a corresponding State class.
// This class holds data related to the form.
class MyCustomFormState extends State<MyCustomForm> {
  // Create a global key that uniquely identifies the Form widget
  // and allows validation of the form.
  //
  // Note: This is a GlobalKey<FormState>,
  // not a GlobalKey<MyCustomFormState>.
  final _formKey = GlobalKey<FormState>();
  TextEditingController messageController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    // Build a Form widget using the _formKey created above.
    return Form(
        key: _formKey,
        child: Container(
          decoration: const BoxDecoration(
            image: DecorationImage(
              image: AssetImage('assets/images/hex.jpg'),
              fit: BoxFit.fitHeight,
            ),
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              TextFormField(
                style: const TextStyle(
                    color: Colors.lightBlueAccent, fontWeight: FontWeight.bold),
                autofillHints: const [AutofillHints.name],
                decoration: const InputDecoration(
                  hintText: 'Enter Password',
                  hintStyle: TextStyle(
                      color: Colors.white, fontWeight: FontWeight.bold),
                  enabledBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: Colors.white),
                  ),
                  focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: Colors.white)),
                ),
                controller: messageController,
                // The validator receives the text that the user has entered.
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter Password';
                  }
                  return null;
                },
              ),
              Padding(
                padding: const EdgeInsets.symmetric(vertical: 16.0),
                child: ElevatedButton.icon(
                  onPressed: () {
                    if (_formKey.currentState!.validate()) {
                      if (messageController.text == "uss") {
                        Navigator.of(context).push(MaterialPageRoute(
                            builder: (context) => AdminPage()));
                      } else {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text('Wrong PassWord')),
                        );
                      }
                    }
                  },
                  style: ElevatedButton.styleFrom(primary: Colors.grey[900]),
                  label: const Text('LOGIN'),
                  icon: const Icon(
                    Icons.lens_blur_rounded,
                    color: Colors.lightBlueAccent,
                  ),
                  // child: const Text('Submit'),
                ),
              ),
            ],
          ),
        ),
      );
  }
}
