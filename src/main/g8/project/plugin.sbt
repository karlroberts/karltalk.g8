//resolver for sbt-s3-resolver
resolvers += "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"

resolvers += Resolver.url("owtelse-repo-oss", new URL("https://s3-ap-southeast-2.amazonaws.com/owtelse-repo-oss/"))(Resolver.ivyStylePatterns)

addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.12.0")

addSbtPlugin("net.ground5hark.sbt" % "sbt-concat" % "0.1.8")

addSbtPlugin("com.owtelse.sbt" % "sbt-web-s3" % "0.2.2-20150812074940-deac20e")
