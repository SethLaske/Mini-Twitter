# Mini-Twitter
CS 3560 Project to create a mini twitter, with users able to post and receive posts from the users they follow

GUI created using Scene builder and javafx with fxml

Design Patterns
  Composite
    Basic interface of Entity
    Entities could either be a Group or a User
    Groups contained lists of Entites

  Singleton
    Created a basic singleton implementation for the Root for easy visitor access

  Visitor
    Visitor methods to count groups, users, messages, and positive messages
    Groups (Starting with the root) accept the visitor and then pass it to following groups
    All the information is contained by the leaf users, except for number of groups

  Observer
    Two cases of the observer pattern were used
    1. The users directly follow each other
      When one posts, they add their post to a new post variable which the other users get and add to their feed
    2. The UserView Controller also implements the patter
      The controllers add themselves to a list so when the followed controller posts, it first triggers the user observer pattern
      which allows the users to all have updated feeds. It then updates the following UI controllers, which refresh their feeds to
      show almost instant communication. Currently this needs to be refined by having the UI controllers unfollow when they are closed,
      but even when both users are closed and reopened the connection is immediately reestablished. This implementation bypasses requiring
      threading to have simultaneous connections and refresh
    
Additions beyond the assignment
  Follow alerts- When a user follows another user they see the message in their feed, without affecting the number of posts. It is also shown to their followers. In the future I might show that message to the user who has just been followed
  Unique ID verification- IDs are verified to make sure they are creating a unique ID. This applys to both users and groups together.
  UI- Shout out to Josh for helping me improve my UIs. I am not a designer by any means
