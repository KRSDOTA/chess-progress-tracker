
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

## Quick game information
An interesting point raised by a friend of mine is that it would be really nice to see at glance, what openings a 
player enjoys, how frequently they ran it over a period of time, and often they win with it.

As you enter a game with an opponent in a rapid, bullet or blitz format player in question, 
we could facilitate that the aforementioned information quickly, giving the searcher a holostic view of what to expect 
in terms of playing from their opponent granting a useful edge. The idea being similar to the OpenDota tool, which informs
players of what opponents like to pick. There are some implications of this which need sorting through:

1) How would this information be available quickly? This kind of on-the-fly computation is not going to be "Quick" by 
    any means, especially if we try and do all data processing within the life of a single request, it simply won't deliver the
    service expected. The ideal situation what be if we could figure out of a way of batching/preparing computation outside of
    the live request, however this seems unreasonable and to be quite frank impossible given the size of the chess.com player pool. 

2) To make matters more complicated, the chess.com games archive API seems to only allow you to query based on "yy/mm". 
   Which means a single query is going to return a data for an entire month, which is potentially is a lot. that the chess.com API only provides archived data in monthly batches.
    
3) The game format is written in a legacy `.pgn' format 



