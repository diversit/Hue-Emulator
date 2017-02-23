name := "hue-emulator"

organization := "eu.diversit.hue"

version := "0.8-SNAPSHOT"

description := "Emulator for Philips Hue"

unmanagedBase := baseDirectory.value / "libs"

javaSource in Compile := baseDirectory.value / "src"

javaSource in Test := baseDirectory.value / "tests"

resourceDirectory in Compile := baseDirectory.value / "resources"

// Enables publishing to maven repo
publishMavenStyle := true

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false

// Disable generating javadoc with publishing artifact since javadoc is invalid (not my fault!!)
publishArtifact in (Compile, packageDoc) := false

// enable publishing the jar produced by `test:package`
publishArtifact in (Test, packageBin) := true

// enable publishing the test API jar
publishArtifact in (Test, packageDoc) := false

// enable publishing the test sources jar
publishArtifact in (Test, packageSrc) := true

// publish to maven repo just like Maven would (use `publish` task, not `publishLocal`)
publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
