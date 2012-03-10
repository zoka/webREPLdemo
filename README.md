# noirMon

This is slightly modified Chris Granger's 
[Noir-blog](https://github.com/ibdknox/Noir-blog) application that 
he used as a demo for his
[Noir](https://github.com/ibdknox/noir) web framework.

It's main purpose is to provide demo for 
[ringMon](https://github.com/zoka/ringMon)
- middleware for 
[Ring](https://github.com/mmcgrana/ring) web library which is de-facto standard 
for Clojure web applications. It also demonstartes how to incorporate ringMon
into typical Noir based application.

Once injected into web aplication's Ring chain,
ringMon drives a single monitoring web page that displays important application
statistics (JMX and derived data) and more importantly, it provides full
featured 
[nREPL](https://github.com/clojure/tools.nrepl)
front end with syntax colored editor and peristent sessions.

## Demo

The code is ready made to be deployed on Heroku - you can see it see it in action 
[here](http://noirmon.herokuapp.com/).

## Usage

```bash
lein deps
lein run
```

## License

Copyright (C) 2012 Zoran Tomicic

Distributed under the Eclipse Public License, the same as Clojure.

