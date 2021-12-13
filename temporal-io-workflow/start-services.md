# Start up services

## Start up temporal.io server

See [this doc](https://docs.temporal.io/docs/server/quick-install/#docker)

* The server doesn't contain any order flow logic.  Start it up and forget it.
* You can see workflows on web http://localhost:8088 

You can consider this as `request broker` 


## Start temporal worker
```
./start-worker.sh
```
* This contains our flow logic.
* The command will build the project and start it as a spring boot service

You can consider this as `request handler` and `workflow orchestrator` 