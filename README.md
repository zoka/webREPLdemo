# webREPLdemo

This is slightly modified Chris Granger's
[Noir-blog](https://github.com/ibdknox/Noir-blog) application,
a demo for his
[Noir](https://github.com/ibdknox/noir) web framework.

Its main purpose is to provide a showcase for [ringMon](https://github.com/zoka/ringMon), a middleware for
[Ring](https://github.com/mmcgrana/ring) web library. This library (Ring) is
de-facto standard for Clojure web applications.

Once injected into web aplication's Ring chain,
ringMon drives a single monitoring web page that displays important application
statistics (such as CPU load and raw JMX property values) and more importantly,
it provides full featured
[nREPL](https://github.com/clojure/tools.nrepl)
front end with syntax colored editor, command history and peristent sessions.

The noirMon also demonstrates how to incorporate ringMon
into a typical Noir based application that is deployed on
[Heroku](http://www.heroku.com/).
cloud platform.

## Demo

The addition of a sigle file in project folder (`Procfile`)
enables this  application to be deployed on Heroku. You can see it see it
in action
[here](http://webrepl.herokuapp.com/).

Since this is a blog app anway, it was convenient to add couple of
of blog entries that describe ringMon features in more detail.

The blog contents is kept `sdb.db. New enttries are added using
the following procedure:

 * Start application locally (see bellow for instructions)
 * The initial username is admin , password clojars, can be removed it once
 you add yourself as a another user.
 * Log in and add/modify blog entry.
 * Shut down the application and redeploy it with updated `sdb.db` on Heroku
 with:

 ```bash
 git commit -a -m "Added new blog article."
 git push origin # Update GitHub repository
 git push heroku master # rebuild and redeploy on Heroku
 ```
## Local Usage

To run locally:

```bash
lein deps
lein run
```
Point your browser to `localhost:8080`.

If you want to do equivalent
of `lein repl` but now using ringMon's nREPL web interface then enter:

```clojure
lein run -m ringmon.server :local-repl true :port 7777
```
This will start a separate Jetty instance on port 7777
just to serve the nREPL page. Your default browser window
will automatically be opened pointing to the page itself.
If `:port` is set to zero at command line then its value
will be autoselected. The default port value is `8888`.

If you already
have the browser window listening at port 7777 from the previous run,
then you should ommit :local-repl option, or set it to false.

```clojure
lein run -m ringmon.server :port 7777
```
Note: For the time being (hopefully not for long) only one nREPL session
is supported per browser/per client computer, so if you have 2 or more windows
within the web browser connected to the same Jetty server, nREPL output will
be randomly sprinkled accros all of them. The workaround is to use
another browser type side by side (tested on Chrome, Firefox and Safari).

Since both browser and application run locally,
the page refresh rate will be adjusted accordingly.
At this point noirMon is not runnning yet.
You can start it by entering this in nREPL input window:

```clojure
(use 'noirmon.server)
(-main)
```
Note that now there are 2 Jetty instances running, one at port 8080
is serving the application, while the ather (at port 7777) is just serving
the ringMon's nREPL interface page. This scenario is only possible in local
environment since Heroku does not allow more than one server socket per
web application process.

The ringMon monitoring page within noirMon itself will
not be accessible in this case - you will get
`ringMon already wrapped into separate web server at port 7777` error 
message if you try to follow the link from the main blog  application page.

## License

Copyright (C) 2012 Zoran Tomicic

Distributed under the Eclipse Public License, the same as Clojure.

