# Twitter Search Engine

This model takes multiple inputs like username initials, language, date and retrieves relevant users and their tweets. Score is assigned to each username search result on the basis of relevance and the result is displayed in order of the same. This system uses Inverted Index to generate postings list of users. It also uses ranked search to rank users according to order of relevance. 
The dataset is obtanined from mutiple sources: Kaggle and KDNuggets.

## Components in Retrieval Model
* Relevant username and tweets retrieval
* Ranking according to score relevance 
* Rank Search
* Inverted Index
* Vector Space Model

## Features of the Model:
1. Retrieving the data from dataset.
2. Showing the rows of dataset in which the data is present.
3. Using Ranking Operations to retrieve relevant user queries.

## Navigation of the Project:

> To run the code 
* Download and unzip the zip folder
* Navigate to the src folder and run Result.java file.
> For UI Interaction
* Navigate to the Text box in which the user gives in Username initial 3 letters, date and language.
* Output of the username position, tweets and scoring of username displayed in tabular format.
* Note - UI is still in the implementation phase and not deployed.

## Working of the project:

We create an inverted index function, in which username is read, cleaned, tokenized, and stemmed explicitly. 
Each word is used as a key in the inverted index dictionary, and the value of the key is the row number. Then the key is passed to Dateweight class where weight assigned, is executed.
 Ranking search is executed using vector space model to output based on highest to lowest ranking. 

## Structure and design of the project:

The information retreival model is built and implemented on the Inverted Index algorithm and the Vector Space model to filter our records based on their rankings. It helps us to display the top documents/index records for an user query.

The design focuses on taking in 3 parameters into a text field which would directly interact with the Java Algorithm. The parameters are :
-3 characters of the User name
-Date
-Language 

The output results in positional index of the User name, tweets made by the User at the particular date followed by total score. This output design will be displayed in an interactive window.

This gives an initial overview structure of our project model, the results will be deployed on the local server developed using Java Swing UI.

We have executed our complete project on the IntelliJ which can be downloaded from :
https://www.jetbrains.com/idea/download/#section=windows

## Dependencies
1. Java SDK
2. Java Swing UI


## Usability 
Our model highlights the basic structure of a mini search engine for twitter. We use twitter dataset in csv format and run Inverted Index and Vector Space Model to retreive username position and rank the search results. This model can be used on a larger scale almost as similar to twitter search bar, which also implements a rank search algorithm. 



## Tools:
* IntelliJ
* Flask (Backend)
* Java Swing UI
* Django (Frontend)

## Collaborators:
* Tejas Parvathappa tp6359@rit.edu
* Shravan Kanchan sk4906@rit.edu
* Yash Mahajan  ym5528@rit.edu
* Arish Bhayani ab5449@rit.edu
