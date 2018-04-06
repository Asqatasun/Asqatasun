# Contributing to Asqatasun

:+1: First off, thanks for taking the time to contribute! :+1:

We are really glad to have you on board ! 

## Fill in bug reports

[Fill in bug report](https://github.com/Asqatasun/Asqatasun/issues).

* Please do not assign issue to anyone.
* You may assign an issue to yourself, meaning to others "I'm actually working on this issue".
* When closing an issue, please add a comment explaining why you are closing it.

## Contribution to the code / Pull Request

### Pre-requesites

* [Docker](https://www.docker.com/)
* [Maven](https://maven.apache.org/)
* [Git](https://git-scm.com/downloads) (of course)

### Pre-requesites for Ubuntu 16.04

* [Docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
* `sudo apt install maven git`

### Choose or create an issue

Either choose an existing issue on which you'd like to work or create a new one. Identify its id. For example, the id of https://github.com/Asqatasun/Asqatasun/issues/115  is `115`, we'll use it later.

### Fork the project

From Github, fork the project. You've got your own personal Asqatasun under your Github user's environment (`https://github.com/<user>/Asqatasun`).

### Work locally

```
git clone https://github.com/<user>/Asqatasun.git
     # instead of:   git clone https://github.com/Asqatasun/Asqatasun.git
cd Asqatasun
git checkout develop
git checkout -b <id-of-issue>-fix
     # git checkout -b 115-fix
```

(Note: create a branch based on the `develop` branch. `Master` is meant to be directly usable in production, thus only contains tagged releases).

### Code

Add your code, do your commits.

### Test locally with Docker

```
./docker/build_and_run-with-docker.sh  --source-dir $(pwd) --docker-dir docker/SNAPSHOT-local
```

Check in your browser at http://localhost:8085/asqatasun/. You may compare with https://app.asqatasun.org/

### Upload to your personal repos

```
git push origin <id-of-issue>-fix
     # git push origin 115-fix
```

### Create the Pull Request / Merge Request

Create it:

* **from** your personal repos / your branch
* **to** the Asqatasun repos / `develop` branch

Validate, a maintainer will review and hopefully merge your pull request.

Thank you for contributing to Asqatasun !


## Resources

Those resources are meant to help:

* [Asqatasun Contributor doc](http://doc.asqatasun.org/en/30_Contributor_doc/),
* and especially [Asqatasun, Git, Github: getting started](http://doc.asqatasun.org/en/30_Contributor_doc/Contribute_to_Asqatasun.html)
* and anyway the [Forum](http://forum.asqatasun.org)

## Sources of inspiration for CONTRIBUTING.md

* For contributors doc [Gitlab Workflow](https://about.gitlab.com/handbook/#gitlab-workflow)
* [Atom's Contributing file](https://github.com/atom/atom/blob/master/CONTRIBUTING.md) that is really good and brightly written.