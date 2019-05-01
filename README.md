Introduction

This report was written as part of our android application project. The report describes and outlines the application that was designed and built for the module. Android studio was the chosen environment for the development of the application. The language the application was written in is Koitlin. Koitlin is designed to interoperate with Java and is estimated to cut the code down by 40%. Google recently threw their support behind koitlin and announced that koitlin will be the language of choice when developing apps for Android.
Outlined below are the decisions I made while developing the application, from design to implementation.

Description and Motivation for Project

Motivation
The motivation for the application stems from my interest in Video games. From an early stage I wanted a character focused application and with the release of Mortal Kombat 11 only around the corner, what better choice was there. The characters from the Mortal Kombat universe are steeped in lore and back stories,  which I knew would give me a wealth of information and images to create the character cards. The colour contrasts associated with the Mortal Kombat universe are also very rich and vibrant and was part of the motivation behind the selection.
Description
The application is an information application, consisting of character cards that the user creates themselves. The cards consist of the character details including a star rating option to allow the user to easily identify their favourite characters. The user can make use of the Mortal Kombat WiKi Activity page to find details and create their characters.
The idea of the initial splash screen was to imitate the classic start screen from the Sega Mega Drive game of the 90’s (Figure 1, and 2) and to bring a nostalgia feeling to the app.
A simple search option is also implemented (Figure 3) on the recycler view where the cards of the characters are displayed. This allows the user to search the characters by their title.
To maintain persistence, Googles Firebase has been implemented allowing the users creations to remain unaffected if the application is removed.
To bring the application more inline with the Mortal Kombat universe, sounds were implemented using Androids MediPlayer. There are two sounds in the app, one on the add/save buttons which plays the ‘EXCELLENT’ sound bite from the game, and another on the delete icon which plays the famous ‘FATALITY’ sound clip.
GitHub was used throughout the development of the application and can be found at this link.
https://github.com/FlyingZeus/MyAssignment 
Please note that branches were used as I did not want to break what I had working while implementing Firebase.

Figure 3: Search and rating bar
Colour Selection

The colour selection I wanted to keep to a minimum as I am a firm believer in less is more. Mortal Kombat is a violent game and displays a lot of blood. With this, the first colour I decided on was a blood red, which was used sparingly on text headings as it’s a strong colour.
The primary colour I choose was the darker grey colour from android, which was primaily used on the character cards as it was a nice contrast against the other primary colour which was black. Black was chosen as it is a good match with almost any colour and again the game used black heavily in its designs throughout the years.
The Last Colour was white, which was used as the text inputted by the user and displayed on the cards. Again, the white created a good contrast against the grey background of the card.

Analysis

During the App development the material provided for the practical sessions were very helpful as it went through the entire development of a similar application. The practical’s offered hands on time which was extremely important.
This was a very challenging module; Android Studio threw up all sorts of issues throughout the development of my app. However, most of these were solved in the practical’s with the help of classmates, the lecturer and Google (Mainly stack overflow).
Additional Information

Implementation of firebase was carried out within android following the guide that is provided under the firebase tab. After these steps were carried out I followed the guides provided by firebase at the link below.

https://firebase.google.com/docs/database/android/read-and-write
Implementation for the search feature was adapted and modified from the source located at the link below.
https://camposha.info/course/android-listview/lesson/kotlin-android-search-filter-listview-with-searchview/
The rating bar was researched and adapted from the following link.
https://www.androidly.net/351/android-ratingbar-using-kotlin
 I also needed to make the ratingsBar smaller and static on the RecyclerView, which was sourced from theses links.
https://stackoverflow.com/questions/6153587/how-can-i-decrease-the-size-of-ratingbar
https://stackoverflow.com/questions/9406166/is-it-possible-to-make-fixed-ratingbar
 
 


