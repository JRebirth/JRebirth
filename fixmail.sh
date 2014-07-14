git filter-branch --env-filter '
 
an="$GIT_AUTHOR_NAME"
am="$GIT_AUTHOR_EMAIL"
cn="$GIT_COMMITTER_NAME"
cm="$GIT_COMMITTER_EMAIL"
 
if [ "$GIT_COMMITTER_EMAIL" = "sebboon@BONE-CORP-ONE" ]
then
cn="Sebastien Bordes"
cm="sebastien.bordes@jrebirth.org"
fi
if [ "$GIT_AUTHOR_EMAIL" = "sebboon@BONE-CORP-ONE" ]
then
an="Sebastien Bordes"
am="sebastien.bordes@jrebirth.org"
fi
 
export GIT_AUTHOR_NAME="$an"
export GIT_AUTHOR_EMAIL="$am"
export GIT_COMMITTER_NAME="$cn"
export GIT_COMMITTER_EMAIL="$cm"
'