Sword Art Online
================

This is [5chris100](https://github.com/5chris100)'s copy of the Sword Art Online mod for Minecraft. This branch is for storing and sharing work by him that is *NOT* yet ready to be pushed to the master branch. This branch should and will only be merged after given the "okay" by me. Anyone can look at my branch's code, but may not merge it into the master branch _without my explicit permission_. Thanks! :)

Warning
=======

Like I said before, this branch contains unstable and/or unfinished code.
Merging into the master branch *is not allowed* unless you verified that the code is stable and compatible with the master branch.
To verify:
* Create a new branch (`git checkout [branch-name]`) and delete the files that are in there (`git rm -r *`).
* Pull the files from this branch (`git pull origin Chris`), and then from master (`git pull origin master`).
* Compile and run in both the client *and the server* and see if everything works as expected.
* If a new bug arises, file an issue in Chris' repo.
* If no new bugs are found, commit (`git commit -m "Verification of Chris: [mm/dd/yy hh:mm]"`) and push (`git push origin master`) this branch to master.
Note: Make sure that you review all of the changed, deleted, and new code. If you notice there's a new item, test it out. See if it crashes when you hit a Creeper with it.
Just make sure that you test it *to the best of your ability*.