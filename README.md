[![Build Status](https://travis-ci.org/navicore/akka-persistence-cassandra.g8.svg?branch=master)](https://travis-ci.org/navicore/akka-persistence-cassandra.g8)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/cb845fd62c794295a9a5893f34ca9a7e)](https://www.codacy.com/app/navicore/akka-persistence-cassandra.g8?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=navicore/akka-persistence-cassandra.g8&amp;utm_campaign=Badge_Grade)

A [g8] Template for an Akka HTTP API Server persisting objects with an Akka Persistence [plugin] for [Cassandra]
---

## PREREQ

  * sbt >= 13.16

## USAGE

G8 will prompt you for details like your project name and package name

In a terminal shell, enter:

```console
sbt new navicore/akka-persistence-cassandra.g8 
```

`cd` into the resulting directory and `sbt run`

See generated the README.md for how to build, configure, and connect to Cassandra

[plugin]: https://github.com/akka/akka-persistence-cassandra
[Cassandra]: http://cassandra.apache.org/
[g8]: http://www.foundweekends.org/giter8/
[g8 setup]: http://www.foundweekends.org/giter8/setup.html 


## DEVELOPING

While changing the template, test using something like:

```console
sbt new file:///Users/navicore/git/navicore/akka-persistence-cassandra.g8
```

