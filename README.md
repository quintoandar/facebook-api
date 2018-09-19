# facebook-api

## enhancement & bugfix & codechanges
You can create a PR to master with the changes you are proposing. Prefer doing deploys on separated PR.
This way code changes and binaries 'upload' wont be mixed.

## deploy

### bumping version
:warning: :warning: :warning: :warning:

Every code change should follow a version bump. You don't need to necessarily do one bump per change,
but you cannot **EVER** deploy without bumping.

To bump the version you need to change the pom.xml

### publish
After both the new code & version bump reached master you should 'publish it'. This is done by running, locally:
```shell
mvn clean deploy
```
Then you create a new branch & add all binaries to it:
```shell
git checkout -b my-deploy
git add -A
git commit -a -m "Publishing new version"
```
Congrats! As soon as this PR is approved & merged you can use the new version
