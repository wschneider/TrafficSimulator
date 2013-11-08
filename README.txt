MELIATOR:

Purpose: To create an effective, open source, modular framework to simulate vehicle traffic and provide insights to changes that can be made to improve driving conditions

Methods: This program will utilize classes representing Roads, the lanes and objects within them, and the cars that drive on them. A manager class will handle each iteration in unit time, and cars will maneuver on roads according to a set of "behaviors" recorded in a profile class. The state at the end of each iteration will be forwarded to a renderer which displays the road conditions. 


Project Schedule:

Week 0 (11-8::11-11)
( ) Complete basic vehicle navigation functionality
   ( ) Breaking
   ( ) Passing
   ( ) Maintaining Speed

( ) Implement basic State-Saving functionality
   ( ) Write to a file
      - Don't worry about SERDE for now
   ( ) Read from a file

( ) Implement basic State-rendering functionality 
   ( ) Single lane design
   ( ) Multi-Lane design


Week 1 (11-12::11-18)
( ) Implement intersections 
   ( ) Stop-sign
   ( ) Stop-light

( ) Implement road features
   ( ) Lane-end
   ( ) Lane-transition
   ( ) Road-end
   ( ) Multi-speed limit

( ) Adjust State-rendering functionality
   ( ) Multiple roads
   ( ) Road features



