# Release a version of Asqatasun

This is the documentation for releasing a new version for Asqatasun. As an end user, you won't need it, it is just for developers.

## Steps to follow

### Prepare Release Candidate

1) Prepare CHANGELOG.txt:

* add an entry for this RC
* add differences between this release and previous one

2) Upgrade version strings in code with `engine/asqatasun-resources/src/main/resources/release/bump_asqatasun.sh`:

```sh
cd engine/asqatasun-resources/src/main/resources/release/
./bump_asqatasun.sh --from-version X.Y.Z-SNAPSHOT --to-version X.Y.Z-rc.1 --automerge --commit --tag --push
```

x) Build local Docker image with locally build Asqatasun, and check release is the good one + run some manual tests

3) In Github, define this tag as "Pre-Release" (as it is a Release Candidate)

4) Copy/paste Changelog to Github Pre-Release

5) Verify Docker image from [Asqatasun Docker hub](https://hub.docker.com/r/asqatasun/asqatasun/tags/) is functionnal.

6) Warn testers

* by direct email to well-known people
* by a message in the [forum](http://forum.asqatasun.org/)

n) Run the functional tests

n) Prepare communication

* Tweet
* Facebook message
* LinkedIn groups + LinkedIn profiles
* LinuxFR
* TooLinux

n) If no blocker is found, proceed to next step, else iterate and increment RC number.

## Prepare actual release

1) Prepare CHANGELOG.txt:

* add an entry for this release
* in a cumulative way, add all the data of previous betas and RCs 
(reader should have all details between from previous stable release and this one, 
without having to dig into the previous betas and RCs)

2) Upgrade version strings in code with `engine/asqatasun-resources/src/main/resources/release/bump_asqatasun.sh`:

```sh
cd engine/asqatasun-resources/src/main/resources/release/
./bump_asqatasun.sh --from-version X.Y.Z-RC.1 --to-version X.Y.Z --automerge --commit --tag --push
```

3) In Github, define this tag as "Release" (as this one is the actual Release)

4) Copy/paste Changelog to Github Release

5) Verify Docker image from [Asqatasun Docker hub](https://hub.docker.com/r/asqatasun/asqatasun/tags/) is functionnal.

n) Launch announces prepared.

n) Celebrate and [have a beer](http://www.aufutetamesure.fr/) !

## Versioning

We tend to follow the [semantic versioning](http://semver.org/) recommendations.

## Sources of inspiration

* [Gitlab Monthly release](http://doc.gitlab.com/ce/release/monthly.html)
