# noirMon

This is slightly modified Chris Granger's
[Noir-blog](https://github.com/ibdknox/Noir-blog) application,
used as a demo for his
[Noir](https://github.com/ibdknox/noir) web framework.

Its main purpose is to provide showcase for
[ringMon](https://github.com/zoka/ringMon)
- middleware for
[Ring](https://github.com/mmcgrana/ring) web library which is de-facto standard
for Clojure web applications. It also demonstrates how to incorporate ringMon
into typical Noir based application.

Once injected into web aplication's Ring chain,
ringMon drives a single monitoring web page that displays important application
statistics (such as CPU load and raw JMX property values) and more importantly, it provides full
featured
[nREPL](https://github.com/clojure/tools.nrepl)
front end with syntax colored editor and peristent sessions.

## Demo

The code is ready made to be deployed on Heroku - you can see it see it in action
[here](http://noirmon.herokuapp.com/).

## Usage

To run locally:

```bash
lein deps
lein run
```
Point your browser at localhost:8080.

If you want to do equivalnet
of `lein repl` using ringMons's REPL then do

```bash
lein
run -m ringmon.server "{:local-repl true :local-port 0}"
```
This will automatically start a separate Jetty instance on autoselcted server
port just to serve the REPL page. Now you can start the application by
entering this in nREPL input window:

```clojure
(use 'noirmon.server)
(-main)
```

Note that now the monitoring ringMon page wihin noirMon itself will
not be operational.

## License

Copyright (C) 2012 Zoran Tomicic

Distributed under the Eclipse Public License, the same as Clojure.

