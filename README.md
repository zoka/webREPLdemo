# noirMon

This is slightly modified Chris Granger's
[Noir-blog](https://github.com/ibdknox/Noir-blog) application,
used as a demo for his
[Noir](https://github.com/ibdknox/noir) web framework.

Its main purpose is to provide a showcase for
[ringMon](https://github.com/zoka/ringMon)
- middleware for
[Ring](https://github.com/mmcgrana/ring) web library. This library is
de-facto standard for Clojure web applications.

Once injected into web aplication's Ring chain,
ringMon drives a single monitoring web page that displays important application
statistics (such as CPU load and raw JMX property values) and more importantly,
it provides full featured
[nREPL](https://github.com/clojure/tools.nrepl)
front end with syntax colored editor, command history and peristent sessions.

The noirMon also demonstrates how to incorporate ringMon
into typical Noir based application.

## Demo

The code is ready made to be deployed on Heroku - you can see it see it in action
[here](http://noirmon.herokuapp.com/).

## Local Usage

To run locally:

```bash
lein deps
lein run
```
Point your browser to `localhost:8080`.

If you want to do equivalent
of `lein repl` but using ringMon nREPL then do

```clojure
lein run -m ringmon.server "{:local-repl true :local-port 0}"
```
This will start a separate Jetty instance on autoselected server
port just to serve the nREPL page. Your default browser will automatically
start and load the monitoring page. If `:local-port` has
non-zero value then there will be no port autoselection. Default
value is `8081`.

Since both browser and application run locally,
the page refresh rate will be adjusted accordingly.
At this point noirMon is not runnning yet.
You can start it entering this in nREPL input window:

```clojure
(use 'noirmon.server)
(-main)
```
Note that in this  case the monitoring page within noirMon itself will
not be accessible - you will get
`ringMon already wrapped into separate web server at port xxxx` error
message where the port is the one the other Jetty instance is using.

## License

Copyright (C) 2012 Zoran Tomicic

Distributed under the Eclipse Public License, the same as Clojure.

