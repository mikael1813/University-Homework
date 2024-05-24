import 'dart:convert';
import 'dart:math';
//import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:flutter_application_1/pinguin.dart';
import 'package:path_provider/path_provider.dart';
import 'dart:io';

abstract class Repository {
  // late File file;

  // Repository(String filePath) {
  //   this.file = File(filePath);
  // }

  void write(List<Pinguin> list) {
    // String jsonPinguini = jsonEncode(list);
    // file.writeAsStringSync(jsonPinguini);
  }

  List<Pinguin>? read() {
    // if (file.readAsStringSync() == "") {
    //   return null;
    // }
    // var pJson = jsonDecode(file.readAsStringSync().toString()) as List;
    // List<Pinguin> PinguinObjs =
    //     pJson.map((Json) => Pinguin.fromJson(Json)).toList();

    // return PinguinObjs;
  }

  void save(Pinguin p) {
    // List<Pinguin>? list = read();
    // if (list == null) {
    //   list = <Pinguin>[];
    //   list.add(p);
    // } else {
    //   list.add(p);
    // }
    // write(list);
  }

  Pinguin? getPoz(int index) {
    // int poz = 0;
    // List<Pinguin>? list = read();
    // if (list != null) {
    //   for (var pinguin in list) {
    //     if (poz == index) {
    //       return pinguin;
    //     }
    //     poz++;
    //   }
    // }
    // return null;
  }

  bool delete(int id) {
    return false;
    // List<Pinguin>? list = read();
    // if (list != null) {
    //   for (var pinguin in list) {
    //     if (pinguin.id == id) {
    //       list.remove(pinguin);
    //       write(list);
    //       return true;
    //     }
    //   }
    // }
    // return false;
  }

  Pinguin? getPinguinByID(int id) {
    // List<Pinguin>? list = read();
    // if (list != null) {
    //   for (var pinguin in list) {
    //     if (pinguin.id == id) {
    //       return pinguin;
    //     }
    //   }
    // }
    // return null;
  }

  bool update(int id, Pinguin p) {
    return false;
    // List<Pinguin>? list = read();
    // if (list != null) {
    //   for (var pinguin in list) {
    //     if (pinguin.id == id) {
    //       pinguin.dataNasterii = p.dataNasterii;
    //       pinguin.id = p.id;
    //       pinguin.nume = p.nume;
    //       pinguin.inaltime = p.inaltime;
    //       pinguin.greutate = p.greutate;
    //       pinguin.pret = p.pret;
    //       pinguin.stare = p.stare;
    //       pinguin.specie = p.specie;
    //       write(list);
    //       return true;
    //     }
    //   }
    // }
    // return false;
  }
}

// Future<String> getFilePath() async {
//   Directory appDocumentsDirectory = await getApplicationDocumentsDirectory();
//   String appDocumentsPath = appDocumentsDirectory.path;
//   String filePath = '$appDocumentsPath/demoTextFile.txt';

//   return filePath;
// }

// void saveFile() async {
//   File file = File(await getFilePath());
//   //await file.delete();
//   file.writeAsString(
//       "This is my demo text that will be saveddddddddddd to : demoTextFile.txt");
// }

// void saveFile2() async {
//   File file = File(await getFilePath());
//   //await file.delete();
//   file.writeAsString(
//       "This is my demo text that will be saved to : demoTextFile.txt");
// }
