# Release a *stable* version of Asqatasun

This is the documentation for releasing a new **stable** version for Asqatasun. As an end user, you won't need it, it is just for developers.

## 1) Prepare CHANGELOG.md:

* Add an entry for this release
* In a cumulative way, add all the data of previous betas and RCs 
(reader should have all details between from previous stable release and this one, 
without having to dig into the previous betas and RCs). For this:
    * review [closed issues](https://github.com/Asqatasun/Asqatasun/issues?q=is%3Aissue+is%3Aclosed)
    * review [closed Pull Requests](https://github.com/Asqatasun/Asqatasun/pulls?q=is%3Apr+is%3Aclosed)
    * review [last commits](https://github.com/Asqatasun/Asqatasun/commits/develop)

## 2) Update README.md (THE readme from top directory):

* copy/paste changelog in section "Content of this last version"

## 3) Upgrade version strings in code with `bump_asqatasun.sh`

```sh
./engine/asqatasun-resources/src/main/resources/release/bump_asqatasun.sh \
    --from-version X.Y.Z-SNAPSHOT \
    --to-version X.Y.Z \
    --automerge \
    --commit \
    --tag
```

do not use the `--push` option 

(We don't push yet, do the testing stuff before :) ) 


## 4) Build local Docker image with locally build Asqatasun

When `--source` is specified, get into that source directory and:

```sh
./docker/build_and_run-with-docker.sh -l -s "${PWD}" -d docker/SNAPSHOT-local --skip-build-test
```

else when using clone in /tmp:

```sh
cd /tmp/Asqatasun   # Directory used to clone Github repos
./docker/build_and_run-with-docker.sh -l -s /tmp/Asqatasun -d docker/SNAPSHOT-local --skip-build-test
```

## 5) Push `master` branch and new `X.Y.Z` tag

```sh
git push origin master
git push origin vX.Y.Z
```

## 6) For `develop` branch, switch back release strings to "-SNAPSHOT"

From the top of Asqatasun source directory, do: 

```sh
./engine/asqatasun-resources/src/main/resources/release/bump_asqatasun.sh \
    --from-version X.Y.Z \
    --to-version A.B.C-SNAPSHOT \
    --source-dir . \
    --back-to-snapshot \
    --commit
git push origin develop
```

## 7) In Github:

* Copy/paste Changelog to Github Release comment field
* Upload the `.tar.gz` files
  * `/tmp/Asqatasun/web-app/asqatasun-web-app/target/asqatasun-X.Y.Z.i386.tar.gz`
  * `/tmp/Asqatasun/runner/asqatasun-runner/target/asqatasun-runner-X.Y.Z.i386.tar.gz`

## 8) Update [Download.asqatasun.org](http://Download.asqatasun.org/)

...so that "latest" points to the last release.

## 9) In Github, define this tag as "Release"

(as this one is the actual Release and not a pre-release)

## 10) In [Asqatasun Docker hub](https://hub.docker.com/r/asqatasun/asqatasun/tags/)

* Add a dedicated build for the Github tag with the same tag as Docker tag 
(while waiting to have a working regexp :) ).
* Add another dedicated build for the Github tag with `latest` as Docker tag

## 11) Launch prepared announces:

* [forum](http://forum.asqatasun.org/)
* Tweet
* Facebook 
* LinkedIn groups + LinkedIn profiles
* LinuxFR
* TooLinux

## 11) Update W3C ERT list

... on https://www.w3.org/WAI/ER/tools/index.html

## 11) Celebrate and have a beer !


