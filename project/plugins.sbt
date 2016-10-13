logLevel := Level.Warn

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.5")

addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "1.3.0")

resolvers += Resolver.url("jetbrains-bintray",
  url("http://dl.bintray.com/jetbrains/sbt-plugins/"))(Resolver.ivyStylePatterns)

addSbtPlugin("org.jetbrains" % "sbt-ide-settings" % "0.1.1")

resolvers += Resolver.url("dancingrobot84-bintray",
  url("http://dl.bintray.com/dancingrobot84/sbt-plugins/"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.dancingrobot84" % "sbt-idea-plugin" % "0.3.1")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")