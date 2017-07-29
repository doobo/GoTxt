echo .settings/ >> .gitignore
echo classes/ >> .gitignore
echo .classpath >> .gitignore
echo .project >> .gitignore
echo build/ >> .gitignore
git rm -r --cached .settings/
git rm -r --cached classes
git rm -r --cached .classpath
git rm -r --cached .project
git rm -r --cached build/
git add .
git commit -m "ignore files and dir"
git add .
git commit -m "ignore files adn dir"
