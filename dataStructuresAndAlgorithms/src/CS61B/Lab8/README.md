# Lab 8 Results

Comparison of `MyHashMap.java` vs `ULLMap` vs Java' built-in `HashMap`.

Dictionary keys (10, 100) are number of strings inserted. Values of inner
dictionaries are seconds taken.

**Major finding**: Performance of own implementation of hashmap is in the rough
ballpark of that of Java’s built in implementation. This is in contrast to doing
the same for [Binary Search Trees](../Lab7/README.md). That is, state-of-the-art HashMaps are
relatively easy to implement compared to state-of-the-art TreeMaps.
 

## Insertion of Random Strings

```json
{
    "10": {
        "class CS61B.Lab8.ULLMap": "0,00",
        "class CS61B.Lab8.MyHashMap": "0,00",
        "Java's Built-in HashMap": "0,00"
    },
    "100": {
        "class CS61B.Lab8.ULLMap": "0,00",
        "class CS61B.Lab8.MyHashMap": "0,00",
        "Java's Built-in HashMap": "0,00"
    },
    "1000": {
        "class CS61B.Lab8.ULLMap": "0,01",
        "class CS61B.Lab8.MyHashMap": "0,00",
        "Java's Built-in HashMap": "0,00"
    },
    "10000": {
        "class CS61B.Lab8.ULLMap": "0,35",
        "class CS61B.Lab8.MyHashMap": "0,02",
        "Java's Built-in HashMap": "0,00"
    },
    "100000": {
        "class CS61B.Lab8.MyHashMap": "0,09",
        "Java's Built-in HashMap": "0,08"
    },
    "20000": {
        "class CS61B.Lab8.MyHashMap": "0,03",
        "Java's Built-in HashMap": "0,01"
    },
    "30000": {
        "class CS61B.Lab8.MyHashMap": "0,02",
        "Java's Built-in HashMap": "0,01"
    },
    "40000": {
        "class CS61B.Lab8.MyHashMap": "0,03",
        "Java's Built-in HashMap": "0,01"
    },
    "50000": {
        "class CS61B.Lab8.MyHashMap": "0,03",
        "Java's Built-in HashMap": "0,02"
    },
    "1000000": {
        "class CS61B.Lab8.MyHashMap": "1,38",
        "Java's Built-in HashMap": "0,53"
    }
}
```

## Insertion of Lexicographically Increasing Strings

```json
{
    "100": {
        "class CS61B.Lab8.ULLMap": "0,00",
        "class CS61B.Lab8.MyHashMap": "0,00",
        "Java's Built-in HashMap": "0,00"
    },
    "1000": {
        "class CS61B.Lab8.ULLMap": "0,01",
        "class CS61B.Lab8.MyHashMap": "0,01",
        "Java's Built-in HashMap": "0,00"
    },
    "10000": {
        "class CS61B.Lab8.ULLMap": "0,95",
        "class CS61B.Lab8.MyHashMap": "0,02",
        "Java's Built-in HashMap": "0,01"
    },
    "20000": {
        "class CS61B.Lab8.ULLMap": "4,25",
        "class CS61B.Lab8.MyHashMap": "0,03",
        "Java's Built-in HashMap": "0,01"
    },
    "4000": {
        "class CS61B.Lab8.ULLMap": "0,14"
    },
    "40000": {
        "class CS61B.Lab8.MyHashMap": "0,05",
        "Java's Built-in HashMap": "0,05"
    },
    "50000": {
        "class CS61B.Lab8.MyHashMap": "0,03",
        "Java's Built-in HashMap": "0,04"
    },
    "80000": {
        "class CS61B.Lab8.MyHashMap": "0,04",
        "Java's Built-in HashMap": "0,06"
    },
    "100000": {
        "class CS61B.Lab8.MyHashMap": "0,09",
        "Java's Built-in HashMap": "0,09"
    },
    "200000": {
        "class CS61B.Lab8.MyHashMap": "0,28",
        "Java's Built-in HashMap": "0,10"
    },
    "300000": {
        "class CS61B.Lab8.MyHashMap": "0,37",
        "Java's Built-in HashMap": "0,19"
    },
    "400000": {
        "class CS61B.Lab8.MyHashMap": "0,61",
        "Java's Built-in HashMap": "0,40"
    }
}
```
