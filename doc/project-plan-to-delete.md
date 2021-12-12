
# Plan

## Create a spring boot micro service with dummy implementation  -- done
* Create a spring boot application
  * Contains a db starter
  * Contains a rest template starter  
* Dockerise it with a database (docker-compose)
  * The database data should persistable
* Create the dummy controllers


## Create the workflow engine 
* A spring boot application which contains a main calls for worker
  * Contains a rest template starter  
* Create a docker compose which can 
    * Start up the temporal.io server
    * Start up the workers (a dummy one)
* Implement the workflow, activities and the worker that I can think of 
  * Accept signals
  

## Integration
* Start the workflow from order service, using "workflow endpoint" (interface + implementation) 
* Call order service to do real job from the worker 

## Advanced flow
* succeed / fail the whole flow
* Query

## Update Readme.md
* How to run
  * The servers
  * CURL to do test
  
## Delete this file
