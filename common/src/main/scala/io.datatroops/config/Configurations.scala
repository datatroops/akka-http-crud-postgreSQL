package io.datatroops.config

case class Configurations(app: ApplicationConf,
                          akka: AkkaConfig,
                          )

//case class MongoDatabase(mongoServerHost: String,
//                         mongoServerPort: Int,
//                         defaultDatabaseName: String,
//                         mongoDbCollectionNames: Map[String, String],
//                        )

case class ApplicationConf(host: String, port: Int)

case class AkkaConfig(futureAwaitDurationMins: Int, akkaWorkersCount: Int)


