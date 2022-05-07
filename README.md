# Conference Rest Api</br>
Api to handle It conference
## Conference assumptions:

1. The conference lasts for one day: June 1, 2021.
2. It starts at 10:00 am and ends at 3:45 pm.
3. Each lecture lasts 1h 45m (15 minutes is a coffee break):
- the first lecture starts at 10:00 and lasts until 11:45.
- the second one starts at 12:00 and ends at 13:45
- the third one starts at 2 p.m. and ends at 3:45 p.m.
4. As part of the conference, 3 different thematic paths run in parallel are supported. If the user subscribes to a given path for a given time, then he can no longer attend a different path in that period, and at a different time he can choose a different path.
5. Each lecture can accommodate a maximum of 5 listeners.

## Functions:
1. The user can view the conference plan.
2. After entering his login, the user can view the lectures for which he has signed up.
3. If the lecture is still free, the user can make a reservation. When making a reservation, he should provide his login and e-mail address.
4. If there is already a user in the system with a given login, but with a different e-mail address, the system should present the message "The given login is already taken".
5. Correct booking results in sending a notification to the user to the address provided by him (sending a message - see instructions).
6. The user may cancel the booking.
7. The user can update his e-mail address.
8. The system enables displaying a list of registered users along with their e-mail addresses.
9. Generating a statement for the organizer:
- list of lectures by interest (percentage of participants in a given lecture)
- a list of thematic paths by interest (percentage share)
## Technologies:
*Java</br>
*Spring boot</br>
*Spring mvc</br>
*Maven</br>
*Lombok</br>
*H2</br>
*Swagger(Open api)

Author: Mirosław Karwowski</br>
Gmail: mirek15207@gmail.com</br>
