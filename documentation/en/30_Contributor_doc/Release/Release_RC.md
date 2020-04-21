# Release a *Release Candidate* version of Asqatasun

This is the documentation for releasing a new version **Release Candidate** for Asqatasun. As an end user, you won't need it, it is just for developers.

## 1) Prepare CHANGELOG.md:

* add an entry for this RC
* add differences between this release and previous one:
    * review [closed issues](https://github.com/Asqatasun/Asqatasun/issues?q=is%3Aissue+is%3Aclosed)
    * review [closed Pull Requests](https://github.com/Asqatasun/Asqatasun/pulls?q=is%3Apr+is%3Aclosed)
    * review [last commits](https://github.com/Asqatasun/Asqatasun/commits/develop)

## 2) Update all READMEs

* Adjust top-level README.md: copy/paste changelog in section "Content of this last version"
* copy top-level README to Docker dirs (TODO define which ones)

## 2b) Update Upgrade instructions

* Adjust upgrade documentation in `documentation/en/50_Administrator_doc/Upgrading/`

## 3) Upgrade version strings in code with `bump_asqatasun.sh`:

/!\ Please be sure to be:

1. on the *release* branch **before** launching the following script (not in a feature branch)
1. inside the top directory of Asqatasun sources (eg /home/johndoe/my-sources/Asqatasun)

```sh
./engine/asqatasun-resources/src/main/resources/release/bump_asqatasun.sh \
    --from-version X.Y.Z-SNAPSHOT \
    --to-version X.Y.Z-rc.1 \
    --branch MY-RELEASE-BRANCH \
    --source-dir . \
    --commit \
    --push \
    --tag
```

## 4) Build local Docker image with locally build Asqatasun

When `--source-dir` is specified, get into that source directory and:

```sh
./docker/build_and_run-with-docker.sh -l -s "${PWD}" -d docker/SNAPSHOT-local --skip-build-test
```

else when using clone in /tmp:

```sh
cd /tmp/Asqatasun   # Directory used to clone Github repos
./docker/build_and_run-with-docker.sh -l -s /tmp/Asqatasun -d docker/SNAPSHOT-local --skip-build-test
```

## 5) Switch back release strings to "-SNAPSHOT"

From the top of Asqatasun source directory, do: 

```sh
./engine/asqatasun-resources/src/main/resources/release/bump_asqatasun.sh \
    --from-version X.Y.Z-rc.1 \
    --to-version X.Y.Z-SNAPSHOT \
    --branch MY-RELEASE-BRANCH \
    --source-dir . \
    --commit \
    --push \
    --back-to-snapshot
```

## 6) In Github:

* Define this tag as "Pre-Release" (as it is a Release Candidate)
* Copy/paste Changelog to Github Pre-Release comment field
* Upload the `.tar.gz` file

## 7) Update [Download.asqatasun.org](http://Download.asqatasun.org/)

...so that "latest" points to the last release.

## 8) In [Asqatasun Docker hub](https://hub.docker.com/r/asqatasun/asqatasun/tags/):

* Add a dedicated build for the Github tag (e.g. `4.0.0-rc.1`) with the same tag as Docker tag (`4.0.0-rc.1`)
(while waiting to have a working regexp :) ).
* Add another dedicated build for the Github tag (e.g. `4.0.0-rc.1`) with `latest` as Docker tag

## 9) Write a message in the [forum](http://forum.asqatasun.org/)

## 10) Send an email to close people to ask them if they want to play with the RC.

## 11) Run the functional tests

## 12) Prepare communication

* Tweet
* Facebook message
* LinkedIn groups + LinkedIn profiles
* LinuxFR
* TooLinux

## 13) If no blocker is found, proceed to next step, else iterate and increment RC number.


