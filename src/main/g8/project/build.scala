import sbt._
import Keys._

import com.owtelse.sbt.S3WebsitePlugin
import com.owtelse.sbt.S3WebsitePlugin.S3WS._
import com.owtelse.foo.PrefixPlugin
import com.owtelse.foo.PrefixPlugin.PrefixKeys._
import com.typesafe.sbt.web.SbtWeb

import ohnosequences.sbt.SbtS3Resolver.{s3 => ss33, _}
import com.amazonaws.services.s3.model.Region

object build extends Build {

  type Sett = sbt.Def.Setting[_]

  lazy val theVersion = "1.0.0"

  lazy val standardSettings = Defaults.defaultSettings ++
    PrefixPlugin.prefixSettings ++ Seq[Sett](
      name := "CHANGE_MY_NAME"
    , scalaVersion := "2.10.4"
    , s3wsBucket := "talks.aws.owtelse.com"
    , s3wsAssetDir := baseDirectory.value / "assets"
    , progressBar in s3wsUpload := true
    , credentials += Credentials(Path.userHome / ".s3TechtalksCreds")
    , s3wsIncremental := true
    , s3wsPrefix <<= (name, tsyncprefixRoot, tsyncprefix).apply((n, prefixroot, prefix) =>
      if(n == "CHANGE_MY_NAME" | n == "You_should_Name_me" | n == "")
        throw new Exception("You must name your Project")
      else  prefixroot + "/" + prefix + "/" + n + "/")
//    , s3wsPrefix := "techsync/" + tsyncprefix.value + "/" + name.value + "/"
//    , s3wsPrefix in s3wsUpload := s3wsPrefix.value
//    , s3wsPrefix in s3wsDeleteAll := s3wsPrefix.value );
  )

  lazy val root: Project = Project(
      id = "tech-talk"
    , base = file(".")
    , settings = standardSettings ++ Seq[Sett](version := theVersion)
  ).enablePlugins(S3WebsitePlugin, SbtWeb)



}
