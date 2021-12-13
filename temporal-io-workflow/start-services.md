# Start up services

## Start up temporal.io server

See [this doc](https://docs.temporal.io/docs/server/quick-install/#docker)

* The server doesn't contain any order flow logic.  Start it up and forget it.
* You can see workflows on web http://localhost:8088 

## Start temporal worker of order flow
```
./start-worker.sh
```
* This contains our flow logic.
* The command will build the project and start it as a spring boot service, along with other services
  * Restful service: http://localhost:12110
  * Mongo DB: you can see the data at http://localhost:12111
  * MailHog for emails sent out by the restful service: you can see them at http://localhost:12112 