# *Unleash-demo* is a demo app for unleash feature toggles

Demo application for testing and demonstrating usage of unleash feature toggles.

## Application startup

The application is implemented using *Spring Boot*. It can be started by running the main class *UnleashDemoApplication* in your IDE.

The configuration resides within *application.yml*. Per default, it is set up to use the Unleash demo application at Heroku. 
In addition to the URL, you will need to specify an environment variable or config value, *unleash-token* holding the token granting access to the Unleash API.

The application will run on port 8080, and the APIs are available at http://localhost:8080/unleash-demo/swagger-ui.html

## Usage

The application provides a few simple APIs, one for evaluating feature toggles and one for logging in as different users by generating jwt tokens.
The APIs are available at http://localhost:8080/unleash-demo/swagger-ui.html

### Evaluating toggles

The *Feature Toggle Controller* provides a simple */feature* endpoint. The endpoint takes a list of toggle names as input and returns an array containing the toggle name and a boolean telling if the toggle is enabled.

### Logging in as different users

The *Token Generator Controller* provides various endpoints for generating access tokens. Most notably the */local/cookie* endpoint that generates an access token and puts it in a cookie which is in turn used by the application API to get the subject of the logged in user. 

Hence, logging in is done simply by calling the endpoint, giving a subject of your choice. Logging in as a different user is done by re-invoking using a different subject. Logging out can be done by deleting the cookie containing the token.

The Token Generator Controller, and the framework for handling tokens, is provided by NAV's *token-support* library, available at https://github.com/navikt/token-support

### Unleash

The app uses Unleash's demo application at Heroku.
* The user interface is available at https://unleash.herokuapp.com/ – publicly available by "logging in" with an email address.
* The application accesses the API at https://unleash.herokuapp.com/api – **see the Unleash docs on how to get hold of a token for API access** – https://docs.getunleash.io/user_guide/api-token

Toggles and activation strategies can be created and configured in the web UI. See the Unleash documentation for details.
* https://github.com/Unleash/unleash
* https://docs.getunleash.io/

### What is demonstrated

The application has a things set up for the demo:
* The `FeatureToggleService` wraps Unleash's `isEnabled` method by adding the currently logged in user to the context. This will enable using user data in the activation strategies, i.e. enabling toggles for specific users.
* The `Scheduler` class, containing a scheduled method which triggers every second. This method will log a string if the toggle named _unleash-demo.scheduler_ is active. As this not run in the context of a logged in user, user specific strategies will not work for this one.
* The classes `ByDepartmentStrategy` and `ByEnvironmentStrategy` demonstrates how to implement custom Unleash strategies. The first will be active if the currently logged in user belongs to an enabled department, while the latter will be active if the application runs in an enabled environment. For custom strategies to work, one must create and configure the corresponding strategies in the Unleash web UI.