# Progetto Ingegneria del Software

Progetto finale per l'esame di Elementi di Ingegneria del Software 2023/24 (Ingegneria Informatica, Canale A) del professore Mauro Migliardi.

Realizzato da Matteo Cuzzolin (2066701)

### Compilazione

```bash
javac -d bin -cp "Matcher/*" myTest/* myAdapter/*
```

### Esecuzione di tutte le Test Suite

```bash
java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.TestRunner
```

### Esecuzione singola Test Suite

```bash
java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.EnumerationAdapterTester
java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.IteratorAdapterTester
java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.ListIteratorAdapterTester
java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.StackAdapterTester
java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.SubListAdapterTester
java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.VectorAdapterTester
```

### Compilazione JavaDOC

```bash
javadoc -d docs -cp "Matcher/*" myTest/* myAdapter/*
```
