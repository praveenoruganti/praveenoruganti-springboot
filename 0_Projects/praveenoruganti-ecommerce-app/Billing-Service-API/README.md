heroku login
heroku create praveen-billing-service-api
git init
heroku git:remote -a praveen-billing-service-api
git add .
git commit -am "make it better"
git push heroku master
heroku ps:scale web=1