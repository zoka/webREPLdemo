The ringMon is a
[Ring](https://github.com/mmcgrana/ring) 
 middleware that adds remote diagnostic and REPL interaction capability to
 Clojure web applications that are based on Ring library or higher level web 
frameworks such as Noir or Compojure.

It injects single monitoring page at  /ringmon/monview.html

The page periodically displays raw JMX data of interest in a tree like structure.
It also shows derived values such as CPU load that is calculated by sampling JMX 
roperty OperatingSystem.ProcessCpuTime every 2 seconds and 
AJAX requests statistics.

Moreover, the page provides full featured 
[nREPL](https://github.com/clojure/tools.nrepl) 
front end,  with syntax coloured editor, command history and persistent sessions.
The actual nREPL server runs in the context  of this application.
The ringMon web page uses AJAX to submit Clojure forms from
input window (upon pressing Ctrl-Enter), displaying nREPL responses
in a separate output window. The script output from nREPL server is sampled at
variable rate depending of output activity.
Since nREPL session is persistent the nREPL output is buffered even when 
web client (browser) is disconnected, and will be sent out on next reconnection.
Currently only one session is kept for particular browser per each 
client computer.
Nothing horrible happens if you open multiple windows within the same browser,
only that nREPL output will randomly be sprinkled across multiple output windows. 
This restriction will be removed soon, 
but for the time being using Chrome + Firefox + Safari will give you 3 
simultaneous sessions :). 

You can submit multiple forms (scripts) without waiting
for the previous ones to finish - they will be queued by 
nREPL server. The 'Interrupt' button will try (and in most cases succeed)
to break the current script execution and possibly schedule the next one in
the queue, if any. Note that if script creates other threads they may
not be interrupted, and that their eventual output will not be captured by 
nREPL server - it will go to the stdout of the shell that started your 
Clojure application. If you are on Heroku, you can catch 
it with 'heroku logs -t' command. 

This demo blog (noirMon) is based on  Chris Granger's 
[Noir-blog](https://github.com/ibdknox/Noir-blog) 
example application for his 
[Noir](https://github.com/ibdknox/noir) 
 web framework. 

Only small amount of changes was required to add ringMon capability to
this application:

<script src="https://gist.github.com/2030301.js"> </script>

*  include   [noirmon "0.1.0-SNAPSHOT"] in project.clj<p>
<p>*  add the middleware itself before starting the server:<br>
               (server/add-middleware monitor/wrap-ring-monitor) 
<p>* put convenient link to access the monitoring page (optional)</p> 
