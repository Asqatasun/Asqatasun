# Contribute to Asqatasun using Git and GitHub

Foreword: welcome ! Come and have fun coding with us :)

This chapter assumes that you already have a git client (see 
[Scott Chacon's Pro Git](http://git-scm.com/book/en/Getting-Started-Installing-Git)
 if you need help getting started) and a **GitHub** account by the name of "github_user"
 (but any other way of forking the original Asqatasun repository should work the same).

## Fork and clone
Using the [Fork](https://github.com/Asqatasun/Asqatasun/fork) button, create your 
own fork of the Asqatasun repository into your GitHub account. This repository will 
be accessible through the web interface of GitHub at `https://github.com/github_user/Asqatasun` 
and through SSH at `git@github.com:github_user/Asqatasun.git`

Make a local clone of that fork, using command line:

`git clone git@github.com:github_user/Asqatasun.git` (or the equivalent command 
through you favorite graphical Git client)

Don't forget to "cd" into your local repository: 

`cd Asqatasun`

Since `git clone` already set up a remote named `origin` pointing to your fork on
 GitHub and a local branch named `master` tracking the remote `origin/master`, we 
don't need to care about that... but we want to set up a remote named `upstream` 
that links to the original repository and that we will use to keep ourselves synced: 

`git remote add upstream git://github.com/Asqatasun/Asqatasun.git`

Last but not least: all new development (bar hotfixes) should be based on the 
`develop` branch rather than on the `master`, so let's get ourselves a local branch for that: 

`git checkout -b develop origin/develop`

## Starting your own development
Whether your contribution is a full-fledged feature or a mere typo fix, the process 
will be the same and can be summed up as:

1. create your work branch based on the last available state of the `develop` branch,
1. work, 
1. commit locally,
1. push your local work branch to your fork on GitHub (to prevent losing your changes in the event of data loss on your local machine),
1. rinse and repeat step 2, 3 and 4 until satisfied,
1. rebase your work on last available state of the `develop` to make sure it will merge easily and without conflicts,
1. push one last time your local branch on your GitHub fork,
1. send a pull request for that branch!

Let's see that process in details.

### Creating the work branch from an up-to-date version of `develop`

First, switch to your local `develop` branch and update it from the official Asqatasun repository:

`git checkout develop`

`git pull upstream develop`

If some actual updates come in, you may want to push them to your fork, so as to keep it up-to-date:

`git push`

Now create your local work branch from that point:

`git checkout -b my_work_branch`

### Work

Well, you should know how to do that one ;)

### Commit locally

This one should go something like:

`git add .`

`git commit -m "my commit message with useful informations in it"`

Refer to [Scott Chacon's Pro Git](http://git-scm.com/book/en/Git-Basics-Recording-Changes-to-the-Repository) 
for more information on how to work with Git.

### Push to your fork

This step should be executed almost as often as the previous one (unless you 
don't have network access at the moment) because it makes a de-facto backup of 
your current work (saving you in case anything bad happens to the computer you work on) 
_and_ enables other people to see your work, comment it, contribute to it, etc.

Also, it is really simple:

The first time you will run:

`git push -u origin my_work_branch:my_work_branch`

This will push your local branch to your fork on GitHub _and_ set your local 
branch to track that new remote branch. Then each time after that you'll just have to run:

`git push -f`

(the `-f` flag being used in case your local history changed, for example after a rebase)

### Getting ready for the Pull Request

Once satisfied of your work, and before actually sending the pull request, you
 should make sure the `develop` branch evolution still merge with your work. The 
easiest way to do that is to update your local `develop` (if some actual updates
 come in, you may want to push them to your fork) and rebase your work branch on 
that. It goes like this:

`git checkout develop`

`git pull upstream develop`

`git push`

`git checkout my_work_branch`

`git rebase develop`

At that point, you may have conflicts to solve, and you'll definitely want to
 check that your work is still correct and still applies as you intended (everything compiles, Unit Tests are passed, etc). Once that is done, push your work branch on your fork one last time:

`git push -f`

And you are ready to send your Pull Request

### The actual Pull Request

There is one last catch: your Pull Request should be applied to the 
`Asqatasun/develop` branch, and not to `Asqatasun/master` as is the default when
 using GitHub web interface (see 
[GitHub's help about creating a Pull Request, point 4](https://help.github.com/articles/creating-a-pull-request)).

That's it, you are ready to send us your Pull Request!