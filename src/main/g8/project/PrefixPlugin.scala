package com.owtelse.foo

import java.text.SimpleDateFormat
import java.util.{Date, TimeZone}

import sbt._

object PrefixPlugin extends Plugin {

  object PrefixKeys {
    lazy val date = SettingKey[Date]("current build date")

    lazy val tsyncprefixRoot = SettingKey[String]("S3 prefix root, eg 'techsync' or 'techsync/lightning'"); //
    lazy val tsyncprefix1 = SettingKey[String]("first part of the prefix, eg Year")
    lazy val tsyncprefix2 = SettingKey[String]("second part of the prefix, eg Month")
    lazy val tsyncprefix = SettingKey[String]("primary package of this project")
  }

  def prefixSettings = Seq(
  PrefixKeys.date in ThisBuild := PrefixPlugin.now
  , PrefixKeys.tsyncprefixRoot in ThisBuild := PrefixPlugin.thePrefixRoot
  , PrefixKeys.tsyncprefix1 in ThisBuild := getYear(PrefixKeys.date.value)
  , PrefixKeys.tsyncprefix2 in ThisBuild := getMonth(PrefixKeys.date.value)
  , PrefixKeys.tsyncprefix in ThisBuild := PrefixKeys.tsyncprefix1.value + "/" + PrefixKeys.tsyncprefix2.value
  )

  lazy val now = new Date

  // the giter8 template will be switched into here
  lazy val lightning = if("NO".equalsIgnoreCase("$talk_type_is_lightning_yes_no$")) false else true;

  lazy val tsyncprefixroot = "techsync"
  lazy val lightningprefixroot = "techsync/lightning"
  lazy val thePrefixRoot = if(lightning) lightningprefixroot else tsyncprefixroot;

  def getYear(d: Date) = {
    val formatter = new SimpleDateFormat("yyyy")
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
    formatter.format(d)
  }

  def getMonth(d: Date) = {
    val formatter = new SimpleDateFormat("MM")
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
    formatter.format(d)
  }

}
