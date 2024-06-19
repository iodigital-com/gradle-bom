# gradleBom
This gradle plugin allows you to generate a CycloneDX software Bill-of-Materials (SBOM). It is primarily
intended for use in Android projects, but may work well with any gradle project. 

Compared to SBOMs generated using the `cdxgen` tool, this plugin gives you finer control over what is included in the SBOM. The SBOM is based 
on the output of gradle's `dependencies` task and only represents what is really used in the delivered app. 
`cdxgen` tends to include too many things, e.g. plugins of your gradle build or multiple versions of dependencies, 
although only one version that was selected through gradle's dependency conflict resolution strategy will end up in the app. 

## Setup
Apply the plugin in your root `build.gradle` file and any subproject's `build.gradle` you want to
generate a SBOM for (e.g. `app` module):

```kotlin
plugins {
    id("com.iodigital.gradlebom")
}
```

## Generating a BOM
You can generate two types of BOM:
- `./gradlew :app:generateBom --configuration=releaseRuntimeClasspath` will create a BOM for the app module in the `releaseRuntimeClasspath` configuration. Run e.g. `./gradlew :app:dependencies` to get a list of all available configurations
- `./gradlew :app:generateReleaseBom` is identical to the above command. The plugin will create tasks for all automatically detected configurations
- `./gradlew generateBuildEnvironmentBom` will create a BOM for your build environment

After the invocation is complete, you will find the SBOM file at `build/outputs/bom.json` in the module's build folder. 
This BOM file can e.g. be uploaded to DependencyTrack using the following curl:

```shell
curl -v -X "POST" "https://yourdependencytrack.com/api/v1/bom" \
     -H 'Content-Type: multipart/form-data' \
     -H 'X-Api-Key: {{DP API key}}' \
     -F "project={{PROJECT ID FROM DP URL}}" \
     -F "bom=@app/build/outputs/bom.json"
```