# contact-dictionary

## Pre-requisite

To run this project you must have:
- Java 7
- Play 1.2.7

## Initialization
### With Docker

You can decide to initialize and run project with Docker
```bash
# Build Docker image
$ docker build -t java-play .
# Run image & run play
$ docker run -it -v $PWD:/play-app -p 9000:9000 java-play bash -c "/play-1.2.7.2/play run"
```

### Without Docker

To run this project without Docker, you simply have to install all pre-requisite and use
play binary file

## Enter the Docker container

You can execute the image and run command into it.

```bash
# Build Docker image
$ docker build -t java-play .
# Run image
$ docker run -it -v $PWD:/play-app -p 9000:9000 java-play bash
```

Now you are in docker container. You can use it to your needs.
- `/play-1.2.7.2` is play binary file
- `/play-app` is your workdir, Your Play! app is here.

## Configuration ElasticSearch

Start Elastic Search

```
elasticsearch-6.1.1/bin/elasticsearch
```

Create index

```
curl -XPUT 'localhost:9200/mashup?pretty' -H 'Content-Type: application/json' -d'
{
 "mappings" : {
  "contact" : {
   "properties" : {
    "importer" : {"type": "keyword" },
    "name" : {"type": "keyword" },
    "location" : { "type" : "keyword" },
    "locationGeoNames": {
          "type": "geo_point"
        }
    "logoUrl" : { "type" : "keyword" },
    "bannerUrl" : { "type" : "keyword" }
   }
  }
 }
}
'
```