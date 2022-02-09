# WordleCheater
Helper for solving the Worlde puzzle

This project works as a web resource to query a list of possible words that match a partial progress in a Wordle puzzle, using a set of dictionaries for English and Spanish.

## Usage

Clone the source code and package it with maven using the command `mvn clean package`. This will generate an uber jar in the /target folder. Start the internal Jetty server with the command `java -jar wordle-cheater-{version}.jar`. By default, the server starts listening HTTP GET requests on port **8888** but you can change it with the parameter `-Dport={portNumber}`. Once started, you can call the service using the following URL format: 

```
http://{host}:{port}/query/{language}/{partialWord}
```

The partial word is a five letter word that can be composed as follows:

- **Uppercase words:** Means an exact match of a letter in the position that is placed. Ex: `ANG__` will match with `ANGER`, `ANGST` and `ANGEL`
- **Lowercase words:** Means a letter that is part of the word but not in the current position. Ex: `aLE__` will match with `CLEAR`, `CLEAN` and `PLEAD` but not with `ALERT`
- **Underscores:** Means any word in this position. Ex. `__EA_` will match with `GREAT`, `BREAD` and `CLEAR`

You can also exclude letters from the search after a pipe in the partialWord. This will remove from the match any word that contains these words in any position. Ex: `QUEE_|N` will match with `QUEER` and `QUEEF` but not with `QUEEN` 

Current supported languages are English(en) and Spanish(es). The word list for these dictionaries are loaded from the following S3 AWS bucket URLs:

- [https://robertux-words.s3.amazonaws.com/es.txt](https://robertux-words.s3.amazonaws.com/es.txt)

- [https://robertux-words.s3.amazonaws.com/en.txt](https://robertux-words.s3.amazonaws.com/en.txt)

## Technologies

- [SparkJava](http://sparkjava.com)
- [GSON](https://github.com/google/gson)

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.
