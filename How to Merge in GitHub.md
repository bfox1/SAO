How to Merge Code using Git and GitHub - Written by dragothor
=============================================================

There are two ways to do this. It depends on whether you're using a branch or a fork for storing your code away from the master repository.

Branches - Part 1a
------------------

1. For a branch, you can use the GitHub GUI program. Open it up and open your repository.
2. Find and click the dropdown menu next to your repositories, on the top.
3. Click "Manage," then select your branches and click "Merge." This brings up the auto-merging tool.
4. There should be two branches here, yours and the master branch. To merge them, drag them by the names into the slots below.
5. Your branch should be in the bottom left slot, while the master branch should go into the one to the right.
6. If you did it right, the end result next to the merge button should say that it will be the master branch.
7. Click "Merge" and hope all goes well. Continue to part 2 if it doesn't.

Forks - Part 1b
---------------

1. If you're using a forked repository, you can't use the GitHub GUI program.
2. The reason is because a branch of a repository is the best way to submit changes when working together on a project.
3. Otherwise, a pull request could be used on the website when you're using a forked repo, but that just makes it more difficult.
4. In order to merge code, find your repository folder in your browser and open a git bash window there.
5. It should say you're using the master branch of your repo, pretty normal, otherwise you found someone else's.
6. Execute the following command: *git pull https://github.com/bfox1/SAO.git*
7. If all goes well, then the automatic merge will complete and you can continue by executing *git push https://github.com/bfox1/SAO.git*. 
8. You can also use the Github GUI to sync your change. If not, continue to part 2.

If All Else Fails... - Part 2
-----------------------------

1. If the merge failed, you and your team members were working on the same file.
2. That's fine, it's pretty common actually. Git has some automatic work to do when it detects you have the same lines in a modified file.
3. It will put the modified code from each branch of the code in brackets and label them by the branch ID or by HEAD.
4. HEAD will usually be the master repo. You will need to go in and determine whether one, the other, or both sets of code need to be in each Java file, place them correctly, and get rid of all of the <<<< and >>>> in the code.
5. Then, you can either through git bash or the Github GUI make a new commit detailing the merge and sync the code.