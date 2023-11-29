
# Project information 


## High level overview

This service acts as a proxy to the chess.com API, the initial conception is to have a few useful endpoints which query the chess.com public API (downstream API) 
for a subset of users starting with myself and then branching off into friends

Because the API is public, there will be no need to handle any OAuth protocols or perform any kind of authentication, as all data consumed is publically avaliable 
and not of a sensitive nature

The idea is to then use this API to power a webapp to provide nice statistics regarding puzzle and rapid progression. 

For now this will just be using the data points to populate graphs.

It is essential that before work is conducted on the API, that a good working understanding of the chess.com public API is established initially

In order to do this, I will be gradually building up a list of .sh scripts which use cURL to call the API, the results are then piped into the 'jq' library 
for response querying, processing and CLI presentation.

I will keep a daily travel log, in the form of commits discussing progress made blockers and insights. 


## Roadmap

For now I'm not too concered about picking a frontend framework. For now the project roadmap is:

1) Gain a working understanding of the chess.com API 
2) Build the service around the chess.com endpoints
3) Build a webapp which consumes the `progress-api` and presents them nicely to users



