# Release a *Relase Candidate* version of Asqatasun

This is the documentation for releasing a new version for Asqatasun. As an end user, you won't need it, it is just for developers.


## 1) Prepare CHANGELOG.txt:

* add an entry for this RC
* add differences between this release and previous one:
    * review [closed issues](https://github.com/Asqatasun/Asqatasun/issues?q=is%3Aissue+is%3Aclosed)
    * review [closed Pull Requests](https://github.com/Asqatasun/Asqatasun/pulls?q=is%3Apr+is%3Aclosed)
    * review [last commits](https://github.com/Asqatasun/Asqatasun/commits/develop)

## 2) Update README.md (THE readme from top directory):

* copy/paste changelog in section "Content of this last version"

## 3) Upgrade version strings in code with `bump_asqatasun.sh`:

```sh
cd engine/asqatasun-resources/src/main/resources/release/
./bump_asqatasun.sh --from-version X.Y.Z-SNAPSHOT --to-version X.Y.Z-rc.1 --automerge --commit --tag --push
```

## 4) Build local Docker image with locally build Asqatasun

...and check release is the good one + run some manual tests

## 5) For develop branch, switch back release strings to "-SNAPSHOT"

```sh
cd /tmp/Asqatasun   # Directory used to clone Github repos
git checkout develop
git rebase master
engine/asqatasun-resources/src/main/resources/release/bump_asqatasun.sh --from-version X.Y.Z-rc.1 --to-version X.Y.Z-SNAPSHOT --source-dir /tmp/Asqatasun
find . -name "pom.xml" | xargs git add -u :/
find . -name "Dockerfile" | xargs git add -u :/
git add **/install.sh 
git add **/asqatasun.conf
git add ansible/asqatasun/defaults/main.yml
git commit -m "Switch release to X.Y.Z-SNAPSHOT"
git push origin develop
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


