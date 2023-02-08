# Generating a Kotlin client for a web API
The main goal of this repo is to show how to generate a Kotlin client for a complex web API successfully.
API from TeamCity was chosen to play with.
You will find 3 separate branches:
1) generate-client-with-jvm-okhttp4-success
2) generate-client-with-jvm-ktor--FAIL
3) generate-client-with-multiplatform--SUCCESS

Each branch shows a case of generating with a particular setting and the result of that experiment.

## Branch generate-client-with-jvm-okhttp4--SUCCESS: Successful case of building with OkHttp library
Here you will find the appropriate set of settings that need to be set to generate a Kotlin client with an OkHttp client

## Branch generate-client-with-jvm-ktor--FAIL: OpenAPI generator produces invalid Kotlin code
The case with Ktro for JVM platform failed, the generator produced an invalid Kotlin code in this case.

## Branching generate-client-with-multiplatform--SUCCESS: Native Kotlin variant
Here you can find everything you need to generate a Kotlin client using the OpenAPI generator and the Ktor library. 

A detailed explanation is published: [TOBE FILLED].