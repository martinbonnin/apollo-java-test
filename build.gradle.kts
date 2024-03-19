plugins {
  id("java")
  id("com.apollographql.apollo3").version("4.0.0-beta.5")
}

dependencies {
  implementation("com.apollographql.apollo3:apollo-runtime-java:4.0.0-beta.5")
  testImplementation("com.apollographql.apollo3:apollo-mockserver:4.0.0-beta.5")
  testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")
  testImplementation("junit:junit:4.13.2")
}

apollo {
  service("service") {
    packageName.set("com.example")
  }
}