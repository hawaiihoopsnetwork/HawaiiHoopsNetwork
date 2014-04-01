name := "HawaiiHoopsNetwork"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.typesafe" %% "play-plugins-mailer" % "2.2.0"
)     

play.Project.playJavaSettings
