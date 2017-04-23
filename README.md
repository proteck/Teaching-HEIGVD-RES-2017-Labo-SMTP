# Prank Mail

## Introduction
This project is based on the RES course given by Olivier Liechti in the HEIG-VD in Swiss. The goal of this project is to send prank mail to a group of person using SMTP protocol.

## How running
You can change set the parameters for the SMTP serveur in the main class `Launcher.java`.  
```java
public static final String SERVER_ADDR = "localhost";
public static final int SERVER_PORT = 25;
```

You can create and add some mail content by adding file *.txt* into the folder `content/`. On the running of the program, an random content will be use for every group.  
*Format :*
```
content/
    firstContent.txt
    secondContent.txt
    otherContent.txt
```
Modify the file `mails.txt` to add, remove or modify the email address for the prank. One address per line.  
*Format :*
```
robert@domain.com
alain@domaine.com
jon@domaine.com
```

## Testing
To test the progam, use a MockMock serveur to simulate a SMTP server. You can use our Docker to launch it.

Install [docker](https://www.docker.com/) into your machine, and launch `mock.sh` to automatize the creation of the image and launch it. 
