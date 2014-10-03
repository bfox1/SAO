Sword Art Online - Developer Branch
===================================

This is the developer (or "dev") branch of the Sword Art Online mod for Minecraft.

This branch is for storing and sharing work by the SAO developers for testing for file conflicts and creating a candidate for the `master` branch. Unstable code may result.

This branch should and will only be merged after everyone says so.

Warning
-------

Like I said before, this branch could contain unstable code. To test out the latest dev copy:
* Open your Git shell and change your focused directory to the SAO directory.
* Pull this branch `git checkout -b dev` from GitHub.
* Compile and run in both the client.
* ???
* Profit!

FAQ
---

* I get merge errors when I try to pull `master` to this branch! Help!

Uh... Why are you pulling from `master`, anyway? This branch is supposed to *overwrite* `master` on each good build.

* I get merge errors when I try to push this branch to `master`! Help!

That's normal. Try pushing again with the `-f` option (for a total of `git push -f origin master`)
