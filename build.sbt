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

