# MoneyBox
A mini version of the Moneybox app that will allow existing users to login, check their account and add money to their moneybox. The app is built with Rx Kotlin and mvvm architecture with android databindings. It also features dependancy injection via dagger and networking with retrofit. Room is used for the database.

## Getting Started
1. Download and install android studio

2. Clone the repo:
- `git clone https://github.com/vishant101/MoneyBox.git`

3. Snyc gradle 

4. Run the messenger app on an emulator or device

## Screenshots
| Login | Account Overview | Individual Account |
|------|---------|-----|
| <img src="https://github.com/vishant101/MoneyBox/blob/master/images/Login.png/" width="275" alt="Login" title="Login" /> | <img src="https://github.com/vishant101/MoneyBox/blob/master/images/AccountOverview.png" width="275" alt="AccountOverview" title="AccountOverview" /> | <img src="https://github.com/vishant101/MoneyBox/blob/master/images/IndividualAccount.png" width="275" alt="IndividualAccount" title="IndividualAccount" /> 

# Solution

## Part A - Bug Fixes
### Bug 1 - Layout 
This bug was fixed by adding constraints to the required elements. Padding was added where required. A change was also made to the android manifest so that the login button is not hidden when the software keyboard is opened.


### Bug 2 - Validation is incorrect
THe rule check was inverted so that it starts as true and any error changes the validation to false. In some cases the wrong variable was being checked these were amended. The error is also now cleared once the fields are correct for successive trys. Ideally i would have loved to databind the rules directly to the edittext views, however this was not part of the spec and I did not want to be penalized.

### Bug 3 - Animation is looping incorrectly
Simple fix by looping the animation from the correct frame start and end and then relooping from the correct position once the animation has finished. This was all achieved through databindings.

## Part B - Add 2 new screens
### Architecture


## API Usage
This a brief summary of the api endpoints in the moneybox sandbox environment. There a lot of other additional properties from the json responses that are not relevant, but you must use these endpoints to retrieve the information needed for this application.

#### Base URL & Test User
The base URL for the moneybox sandbox environment is `https://api-test01.moneyboxapp.com/`.
You can log into test your app using the following user:

|  Username          | Password         |
| ------------- | ------------- |
| androidtest@moneyboxapp.com  | P455word12  |

#### Headers

In order to make requests https must be used and the following headers must be included in each request.

|  Key | Value |
| ------------- | ------------- |
| AppId  | 3a97b932a9d449c981b595  |
| Content-Type  | application/json  |
| appVersion | 5.10.0 |
| apiVersion | 3.0.0 |

#### Authentication
To login with this user to retrieve a bearer token you need to call `POST /users/login`.
```
POST /users/login
{
  "Email": "androidtest@moneyboxapp.com",
  "Password": "P455word12",
  "Idfa": "ANYTHING"
}
```
Sample json response
```
"Session": {
        "BearerToken": "TsMWRkbrcu3NGrpf84gi2+pg0iOMVymyKklmkY0oI84=",
        "ExternalSessionId": "4ff0eab7-7d3f-40c5-b87b-68d4a4961983", -- not used
        "SessionExternalId": "4ff0eab7-7d3f-40c5-b87b-68d4a4961983", -- not used
        "ExpiryInSeconds": 0 -- not used
    }
```
After obtaining a bearer token an Authorization header must be provided for all other endpoints along with the headers listed above (Note: The BearerToken has a sliding expiration of 5 mins).

|  Key          | Value         |
| ------------- | ------------- |
| Authorization  | Bearer TsMWRkbrcu3NGrpf84gi2+pg0iOMVymyKklmkY0oI84=  |

#### Investor Products
Provides product and account information for a user that will be needed for the two additional screens.
```
GET /investorproducts
```
### One off payments
Adds a one off amount to the users moneybox.
```
POST /oneoffpayments
{
  "Amount": 20,
  "InvestorProductId": 3230 ------> The InvestorProductId from /investorproducts endpoint
}
```
Good luck!
