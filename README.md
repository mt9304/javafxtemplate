# JavaFX Template

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
  - [Suggested Environment](#suggested-environment)
  - [Installation](#installation)
- [Extending Functions](#extending-functionality)
  - [Adding To UI](#adding-to-ui)
  - [Adding Settings](#adding-settings)
  - [Starting The Program](#starting-the-program)
- [Useful Information](#useful-information)
  - [Custom Scene Helper](#custom-scene-helper)
  - [Conventions](#conventions)

## Introduction

This is a GUI template for starting new JavaFX projects. Most of the UI parts that I most commonly use are set up already so that I can just start building the logic behind the program or idea. 

## Prerequisites

### Suggested Environment

1. Java IDE with Maven installed
2. JavaFX installed for IDE
3. [JavaFX SceneBuilder 8+](http://gluonhq.com/products/scene-builder/) for drag and drop UI builder
4. Knowledge of [JavaFX](https://www.tutorialspoint.com/javafx/index.htm) and SceneBuilder

### Installation
1. In terminal, go to directory you want to save project in and type: 
```bash
git clone https://github.com/mt9304/javafxtemplate.git
```
2. Go into project folder and type: 
```bash
mvn install
```
3. Build/Run the Main.java file in the javafxtemplate folder. 

## Extending Functionality

### Adding To UI

1. The FXML files are all in /view/fxml. The components that will always be displayed (such as title bar, menu bar) will be in the alwaysdisplayed package. 
2. To add a component, right click on the package and go to New > Empty FXML. 
3. In the menu that pops up, name the FXML file and create its associating controller/css files. Controllers go into /controllers, and css files go into /view/css. 
4. Edit the FXML file in SceneBuilder however you want. Once you are ready to add it to the application, open up the FXML file you want (for example, LeftMenuPane.fxml to add menu items, Main.fxml for root pane, Home.fxml for the home page) then go to File > Include > FXML to select the FXML you created, and add it into the scene. 


### Adding Settings

- There are usually 3 types of settings I use the most for projects: Settings specific to theapplication, API or databaseconnection settings, and general template settings. These each have a page on the default application, functions for these will be added. 

### Starting The Program

- The start button is already created in the home page. Home page FXML is in /view/fxml/contentarea. 
- You can specify twhat you want the program to actually do in /controllers/contentarea/HomePageController.java in the `startApplication(MouseEvent)` function. 
- By default, it is running the MainBotRoutine.java file found in /app/businesslogic. 
- Using threads is recommended for starting the function, but if you want to interact with JavaFX UI components in the thread properly, the program may need to use [Platform.runLater and Tasks](https://stackoverflow.com/questions/16708931/javafx-working-with-threads-and-gui). 

## Useful Information

### Custom Scene Helper

- You can use the CustomSceneHelper class in /helpers/services to help your controllers interact with other components in the scene that you can't normally reach. 


Example for changing the color of an object in the scene: 
```java
@FXML
private void highlightButton(MouseEvent event)
{
    String nodeID = sceneHelper.getSourceID(event.getSource());
    Node eventNode = sceneHelper.getNodeById(nodeID);
    eventNode.setStyle("-fx-background-color: #555764;");
}
```

- In the above, `event.getSource()` returns something like `Button[id=homeButton, styleClass=button leftPaneButton]'Home'`. We use the `getSourceID(Object)` function to find the name of the button that was clicked. Then we use the `getNodeById(String)` function to return the actual object. This should work as long as the [naming convention](#conventions) is followed. Once we have the object, we can do whatever we want to it. 

### Conventions

- So far there are 3 types of components in the default template: Button, Page, and ContentArea. 

- Ids should always start with the first letter of its name lowered plus the component name in camel case. So the "Api/Database" button would be "apiDatabaseButton" and its corresponding page Id would be "apiDatabasePage". Might look into using underscores in the future if it is easier. 

- Views and Controllers should follow this pattern: 
	- /view/fxml/yourpackage/YourPage.fxml
	- /view/css/yourpackage/YourPage.css
	- /controllers/yourpackage/YourPageController.java

- Tests follow the same structure as above, but go in the test folder instead of the main folder at the root. 

- Keep the app's main function/logic in /app/businesslogic. This template is designed for small/single purpose programs (example, run a macro, calculate some formulas, convert some files). 